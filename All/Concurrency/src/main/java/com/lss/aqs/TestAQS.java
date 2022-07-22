package com.lss.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class TestAQS extends AbstractQueuedSynchronizer {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
      int i=0;
      i++;
        System.out.println(i);
    }


}
