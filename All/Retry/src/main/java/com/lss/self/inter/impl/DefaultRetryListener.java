package com.lss.self.inter.impl;

import com.lss.self.inter.RetryListener;

public class DefaultRetryListener implements RetryListener {
    @Override
    public void notifyObserver() {
        System.out.println("this is a DefaultRetryListener 这是一个默认的");
    }
}
