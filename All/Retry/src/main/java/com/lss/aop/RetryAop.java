package com.lss.aop;

import com.lss.annotation.Retry;
import com.lss.inter.RetryStrategy;
import com.lss.inter.RetryTask;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Aspect
@Component
public class RetryAop {
    @Resource
    private ApplicationContext applicationContext;



    @Around("@annotation(retry)")
    public Object doBiz(ProceedingJoinPoint proceedingJoinPoint,Retry retry){
        RetryStrategy retryStrategy = applicationContext.getBean(retry.strategy());
        RetryTask retryTask = new RetryTaskImpl(proceedingJoinPoint);
        retryStrategy.initArgs(retry,retryTask);

        try {
            //执行有注解的方法
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
          TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            //发现失败后进入重试该方法
            retryStrategy.retryTask();
        }
        return null;
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
