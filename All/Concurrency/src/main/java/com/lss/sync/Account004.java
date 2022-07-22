package com.lss.sync;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Account004 {

    private Integer balance;

    public Account004(int balance) {
        this.balance = balance;
    }
    private HashMap obj = new HashMap();
    private  Object obj1 = new Object();

    //
    private StringBuffer stringBuffer = new StringBuffer("121");
    private StringBuilder stringBuilder = new StringBuilder();

    //基本上所有的基础类型的包装类都不适合做锁，因为他们内部用到了亨元模式。亨元模式避免创建重复对象
    Long al=Long.valueOf(1);

    //方法中有一个入参是另外一个实例，方法内部的操作也涉及到对另外一个实例的变量操作
    //而锁在方法上锁的是this，只针对当前实例。所以，是无法保证整个的数据正确。
    //我们需要把锁的粒度和范围在扩大一点，锁这个类就能得到很好的控制。 查询支持在阿萨
    public void transfer(Account004 target, int money){
       synchronized (stringBuilder){
        if(this.balance >= money){
            try {
                Thread.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            target.balance += money;
            balance -= money;
        }
       }
    }

    public static void main(String[] args) throws Exception{
        Account004 account002A = new Account004(5000);
        Account004 account002B = new Account004(5000);
        Account004 account002C = new Account004(5000);
        CountDownLatch countDownLatch = new CountDownLatch(5000);
        //这是主线程的计数器，当一千个线程都执行完自己的业务逻辑后，就会归0
        CountDownLatch countDownLatchMain = new CountDownLatch(5000);
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        for(int i=0 ; i<5000 ; i++){
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
/*
        Thread.sleep(50000L);*/

        System.out.println("A:"+account002A.balance);
        System.out.println("B:"+account002B.balance);
        System.out.println("C:"+account002C.balance);
    }



}
