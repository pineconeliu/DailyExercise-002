package com.lss.model;

public class Test {
    public static void main(String[] args){

        Container container = new Container(5);

        Thread producer1 = new Thread(new Producer(container));
        Thread producer2 = new Thread(new Producer(container));
        Thread producer3 = new Thread(new Producer(container));
        Thread producer4 = new Thread(new Producer(container));
        Thread producer5 = new Thread(new Producer(container));

        Thread consumer1 = new Thread(new Consumer(container));
        Thread consumer2 = new Thread(new Consumer(container));


        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();

        consumer1.start();
        consumer2.start();
    }
}

