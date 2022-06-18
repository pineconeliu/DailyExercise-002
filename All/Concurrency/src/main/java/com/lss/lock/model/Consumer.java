package com.lss.lock.model;

import com.lss.model.Container;

public class Consumer implements Runnable {

    private Container1 container;

    public Consumer(Container1 container) {
        this.container = container;
    }

    @Override
    public void run() {
        while (true){
            container.take();
        }
    }
}

