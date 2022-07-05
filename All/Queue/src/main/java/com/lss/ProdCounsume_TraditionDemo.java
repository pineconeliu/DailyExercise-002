package com.lss;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProdCounsume_TraditionDemo {

    public static void main(String[] args) {

        ShareData shareData = new ShareData();
        CountDownLatch countDownLatch = new CountDownLatch(5000);
        //这是主线程的计数器，当一千个线程都执行完自己的业务逻辑后，就会归0
        CountDownLatch countDownLatchMain = new CountDownLatch(5000);
        new Thread(() -> {
            for (int i = 0; i <= 5000; i++) {
                shareData.increment();
                try {
                    countDownLatch.await();
                    countDownLatchMain.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T1").start();
        countDownLatch.countDown();

        new Thread(() -> {
            for (int i = 0; i <= 5000; i++) {
                shareData.decrement();
                try {
                    countDownLatch.await();
                    countDownLatchMain.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T2").start();
        countDownLatch.countDown();
    }
}

//线程操作资源类
class ShareData {
    private AtomicInteger  num = new AtomicInteger(0);
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    //
    final Condition notFull =  lock.newCondition();

    //
    final Condition notEmpty =  lock.newCondition();


    public void increment() {

        lock.lock();
        try {
            while (num.get() == 5000 ) {

                //等待，不能生产
                notFull.await();
            }
                //干活
               num.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + "\t" + num);
                //唤醒
            notEmpty.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while (num.get() == 0) {
                //等待，不能生产
                notEmpty.await();
            }
                //干活
               num.decrementAndGet();
                System.out.println(Thread.currentThread().getName() + "\t" + num);

                //唤醒
            notFull.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}