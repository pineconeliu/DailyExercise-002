package com.lss.inter.impl;

import com.lss.annotation.Retry;
import com.lss.inter.RetryListener;
import com.lss.inter.RetryStrategy;
import com.lss.inter.RetryTask;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.ExecutorService;

//快速重试策略

public class FastRetryStrategy implements RetryStrategy, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Retry retry;
    //重试任务的有哪些
    private RetryTask retryTask;
    //重试策略
    //线程池-将重试的任务放倒在这里面
    private ExecutorService retryThreadPool;

    public ExecutorService getRetryThreadPool() {
        return retryThreadPool;
    }

    public void setRetryThreadPool(ExecutorService retryThreadPool) {
        this.retryThreadPool = retryThreadPool;
    }




    /**
     * 初始化一些参数配置
     *
     * @param retry
     * @param retryTask
     */
    @Override
    public void initArgs(Retry retry, RetryTask retryTask) {
        this.retry = retry;
        this.retryTask = retryTask;
    }

    /**
     *AOP内的 重试策略
     */
    @Override
    public void retryTask() {
        if(!FastRetryStrategy.class.equals(retry.strategy())){
            System.err.println("error retry strategy");
            return;
        }
        //获取注入到spring中的bean 监听器进行监听
        String[] beanNamesForType = applicationContext.getBeanNamesForType(retry.listener());
        RetryListener retryListener = null;
        //安全查找bean
        if(beanNamesForType !=null && beanNamesForType.length >0 ){
            retryListener = applicationContext.getBean(retry.listener());
        }
        //这个异常是要和什么去对比的吗？如果直接抛出异常，不应该先检查当前抛出的异常是否指定的一样吗？
        Class<? extends Throwable>[] exceptionClasses = retry.value();

        RetryListener finalRetryListener = retryListener;
        retryThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i <= retry.maxAttempts();i++){
                    int finalI = i;
                    try{
                        //开始重试任务--不知道设计到事务的时候会不会出现问题
                        retryTask.doTask();
                        //成功就设置true
                        retryTask.setRetrySuccess();
                        return;
                    }catch (Throwable e){
                        for(Class<? extends  Throwable> clazz :exceptionClasses){
                            if(e.getClass().equals(clazz) || e.getClass().isInstance(clazz) ){
                                if(finalRetryListener !=null){
                                    //监听执行结果，将重试结果记录下来，没成功的话，让程序继续for循环执行
                                    finalRetryListener.notifyObserver();
                                }
                                System.err.println("[FastRetryStrategy] retry again,attempt's time is " + finalI + ",tims is " + System.currentTimeMillis());
                                try {
                                    // 延迟重试，防止短时间内请求过多
                                    Thread.sleep(retry.delay());
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        ExecutorService retryThreadPool =(ExecutorService) applicationContext.getBean("retryThreadPool");
        this.setRetryThreadPool(retryThreadPool);
    }
}
