package com.lss.self.aop;

import com.lss.self.annotation.Retry;
import com.lss.self.inter.RetryStrategy;
import com.lss.self.inter.RetryTask;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Aspect
@Component
public class RetryAop {
    @Resource
    private ApplicationContext applicationContext;

    private RetryStrategy retryStrategy ;

    private Retry retry1 ;

    @Around("@annotation(retry)")
    public Object doBiz(ProceedingJoinPoint proceedingJoinPoint,Retry retry){
        retryStrategy = applicationContext.getBean(retry.strategy());
        RetryTask retryTask = new RetryTaskImpl(proceedingJoinPoint);
        retryStrategy.initArgs(retry,retryTask);
        retry1 = retry;
        try {
            //执行有注解的方法
            final Object proceed = proceedingJoinPoint.proceed();
            return proceed;
        } catch (Throwable throwable) {
          /*TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();*/
            //发现失败后进入重试该方法
            retryStrategy.retryTask();
        }
        return null;
    }

    @AfterReturning(value = "",pointcut = "@annotation(com.lss.self.annotation.Retry)",returning = "result")
    public Object recordOperationLog(JoinPoint joinPoint, Object result) throws Throwable {

        // 获取注解
        MethodSignature signature =
                (MethodSignature)joinPoint.getSignature();
// 获取当前方法被Hh注解注释的注解对象
        /*  Retry annotation =
                signature.getMethod().getAnnotation(Retry.class);
      retryStrategy = applicationContext.getBean(annotation.strategy());
        RetryTask retryTask = new RetryTaskImpl(joinPoint);
        retryStrategy.initArgs(annotation,retryTask);*/
        String retryIfResult = retry1.retryIfResult();

        if(retryIfResult.equals(result)){
            //如果等于指定的值就做
            retryStrategy.retryTask();
            System.out.println("===="+result);
        }


/*
        try {
            //执行有注解的方法
            final Object proceed = joinPoint.proceed();
            return proceed;
        } catch (Throwable throwable) {
            *//*TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();*//*
            //发现失败后进入重试该方法
            retryStrategy.retryTask();
        }*/
        return null;
      /*

        System.out.println(result);
        return result;*/
    }




    private class RetryTaskImpl implements RetryTask {
        private  ProceedingJoinPoint proceedingJoinPoint;
        private Object result;
        private volatile Boolean asyncRetryState = null;

        public RetryTaskImpl(ProceedingJoinPoint proceedingJoinPoint) {
            this.proceedingJoinPoint = proceedingJoinPoint;
        }

        public ProceedingJoinPoint getProceedingJoinPoint() {
            return proceedingJoinPoint;
        }

        public void setProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint) {
            this.proceedingJoinPoint = proceedingJoinPoint;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }

        public Boolean getAsyncRetryState() {
            return asyncRetryState;
        }

        public void setAsyncRetryState(Boolean asyncRetryState) {
            this.asyncRetryState = asyncRetryState;
        }

        @Override
        public Object getRetryResult() {
            return result;
        }

        @Override
        public Boolean getRetryStatus() {
            return asyncRetryState;
        }

        @Override
        public void setRetrySuccess() {
            this.setAsyncRetryState (true);
        }

        @Override
        public void doTask() throws Throwable {
            this.result = proceedingJoinPoint.proceed();
        }
    }
}
