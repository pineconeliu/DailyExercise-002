package com.lss.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Account002 {

    private int balance;

    public Account002(int balance) {
        this.balance = balance;
    }

    //方法中有一个入参是另外一个实例，方法内部的操作也涉及到对另外一个实例的变量操作
    //而锁在方法上锁的是this，只针对当前实例。所以，是无法保证整个的数据正确。
    //我们需要把锁的粒度和范围在扩大一点，锁这个类就能得到很好的控制。
    synchronized public void transfer(Account002 target, int money){
         /*synchronized (Account002.class){*/ //线程安全
        if(this.balance >= money){
            target.balance += money;
            balance -= money;
       /* }*/
        }
    }

    public static void main(String[] args) throws Exception{
        Account002 account002A = new Account002(30000);
        Account002 account002B = new Account002(30000);
        Account002 account002C = new Account002(30000);
        CountDownLatch countDownLatch = new CountDownLatch(30000);
        //这是主线程的计数器，当一千个线程都执行完自己的业务逻辑后，就会归0
        CountDownLatch countDownLatchMain = new CountDownLatch(30000);
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        for(int i=0 ; i<30000 ; i++){
           new Thread(new Runnable() {
                @Override
                public void run() {
                  try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    account002A.transfer(account002B,1);
                    account002B.transfer(account002C,1);
                    //数量减一次，就表示任务执行一次
                    countDownLatchMain.countDown();
                }
            }).start();

            countDownLatch.countDown();

        }
        //让主线程等待，为了得到最后的结果
        countDownLatchMain.await();

        System.out.println("A:"+account002A.balance);
        System.out.println("B:"+account002B.balance);
        System.out.println("C:"+account002C.balance);
    }



}
