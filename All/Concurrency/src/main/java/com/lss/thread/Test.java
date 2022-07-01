package com.lss.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 测试同一个实例中多线程操作不同的锁方法。用CountDownLatch模拟并发
 * @author 10380
 */
public class Test  {

    private int count = 0;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        Test test = new Test();
        CountDownLatch createLatch  = new CountDownLatch(1000);
        CountDownLatch countMainLatch = new CountDownLatch(2000);
        for (int i=0;i<1000;i++){
           new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        createLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.test1();
                    countMainLatch.countDown();
                    //打印的顺序会不一样，但是最大的结果值没有变化
                   /* System.out.println(test.count);*/
                }
            }).start();
          new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        createLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.test2();
                    countMainLatch.countDown();
                }
            }).start();
            createLatch.countDown();
        }
        countMainLatch.await();
        System.out.println(test.count);
    }


    //同步代码块1
    public  synchronized void test1(){
        count++;
    }

    //同步代码块2
    public  synchronized void test2(){
        count++;
    }
}
