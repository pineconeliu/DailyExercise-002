package com.lss.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1、Spring 框架提供的
 * private ThreadFactory springThreadFactory = new CustomizableThreadFactory("springThread-pool-");
 *
 * 2、Google guava 工具类
 * private ThreadFactory guavaThreadFactory = new ThreadFactoryBuilder().setNameFormat("retryClient-pool-").build();
 *
 * 3、自定义线程池工厂，定义线程名称
 *
 * 4、 Apache commons-lang3 提供的 BasicThreadFactory
 * private ThreadFactory basicThreadFactory = new BasicThreadFactory.Builder().namingPattern("basicThreadFactory-").build();
 */
public class MyThreadFactory implements ThreadFactory {


    /**
     * thread Number
     */
    private final String prefix;

    /**
     * thread Number
     */
    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    public MyThreadFactory(String prefix) {
        this.prefix = prefix;
    }


    @Override
    public Thread newThread(Runnable r) {
        return new Thread( r,prefix+atomicInteger.getAndIncrement());
    }
}
