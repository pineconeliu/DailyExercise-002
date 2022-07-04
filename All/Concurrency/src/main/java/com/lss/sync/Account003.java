package com.lss.sync;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Account003 {

    private int balance;

    private HashMap obj = new HashMap();

    public Account003(int balance) {
        this.balance = balance;
    }


     public void test(){
         synchronized(obj){
             obj.put("sda","sadsa");
             balance++;
         }
    }

    public static void main(String[] args) throws Exception{
        Account003 account002A = new Account003(30000);
        CountDownLatch countDownLatch = new CountDownLatch(30000);
        //这是主线程的计数器，当一千个线程都执行完自己的业务逻辑后，就会归0
        CountDownLatch countDownLatchMain = new CountDownLatch(30000);

        for(int i=0 ; i<30000 ; i++){
           new Thread(new Runnable() {
                @Override
                public void run() {
                  try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    account002A.test();
                    //数量减一次，就表示任务执行一次
                    countDownLatchMain.countDown();
                }
            }).start();

            countDownLatch.countDown();

        }
        //让主线程等待，为了得到最后的结果
        countDownLatchMain.await();

        System.out.println("A:"+account002A.balance);
    }



}
