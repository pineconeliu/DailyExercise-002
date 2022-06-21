package com.lss.lock.model;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.lss.model.Container;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 工作线程达到最大的时候，没有多余的线程去操作其他任务，如果这些工作的线程统一卡死，一直在等待执行完成，通常其他任务是不会执行，除非用拒绝策略
 * 让调用当前的代码的线程去执行代码，不用线程池的线程
 * @author liusongsheng
 * @date 2022/6/18 17:18
 * @return null
 */
public class Test {
    public static void main(String[] args){

        Container1 container = new Container1();

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(2);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 20,
                TimeUnit.MILLISECONDS, arrayBlockingQueue,new ThreadFactoryBuilder().setNamePrefix("测试-thread-").build()
            , new ThreadPoolExecutor.DiscardPolicy());

        threadPoolExecutor.execute(new Producer(container));
        threadPoolExecutor.execute(new Producer(container));
        threadPoolExecutor.execute(new Producer(container));
        threadPoolExecutor.execute(new Producer(container));

        //消费者
        threadPoolExecutor.execute(new Consumer(container));
        threadPoolExecutor.execute(new Consumer(container));

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+":1测试线程池中工作线程都达到最大了，但是又有一个新任务进来看看会不会执行，是哪位幸运儿来执行呢？");
            }
        });

        threadPoolExecutor.shutdownNow();
        //本意是想让线程池中工作的线程都停止工作，但是shutdownNow 方法会报错，而且程序还没死亡,这是为何？
      /*   threadPoolExecutor.shutdownNow();
        */


    }
}

