package com.lss.model;

public class Consumer implements Runnable {

    private Container container;

    public Consumer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        while (true){
            Integer val = container.take();
        }
    }
}

