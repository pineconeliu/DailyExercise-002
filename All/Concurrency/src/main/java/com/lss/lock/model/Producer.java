package com.lss.lock.model;

import com.lss.model.Container;

import java.util.Random;

public class Producer implements Runnable {

    private Container1 container;

    public Producer(Container1 container) {
        this.container = container;
    }

    @Override
    public void run() {
        while (true){
            container.add(new Random().nextInt(100));
        }
    }
}

