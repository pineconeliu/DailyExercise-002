package com.lss.model;

import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.locks.Condition;

public class Producer implements Runnable {

    private Container container;

    public Producer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        while (true){

            container.put(new Random().nextInt(100));
        }
    }
}

