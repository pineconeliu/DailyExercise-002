package com.lss.inter;

//重试监听器
public interface  RetryListener {

    //将回调结果 通知观察者
    void notifyObserver();
}
