package com.lss.self.inter;

//默认抽象类
public abstract class AbstractRetryLitener implements RetryListener {

    @Override
    public abstract void notifyObserver();

}