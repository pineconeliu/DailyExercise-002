package com.lss.self.config;

import com.lss.self.inter.RetryListener;
import com.lss.self.inter.impl.DefaultRetryListener;
import com.lss.self.inter.impl.FastRetryStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class RetryConfig {

    @Bean
    public FastRetryStrategy fastRetryStrategy(){
        return new FastRetryStrategy();
    }



    @Bean
    public RetryListener defaultRetryListener(){
        return new DefaultRetryListener();
    }
    @Bean
    public ExecutorService retryThreadPool(){
        ExecutorService executorService = new ThreadPoolExecutor(2,4,0L,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        return executorService;
    }
}
