package com.lss.sync;

import java.util.concurrent.CountDownLatch;

public class Account001 {

    private int balance;

    public Account001(int balance) {
        this.balance = balance;
    }

    //
     public void transfer(Account001 target, int money){
         synchronized(Account001.class){
        if(this.balance > money){
            target.balance += money;
            balance -= money;
        }
         }

    }

    public static void main(String[] args) throws Exception{
        Account001 account001A = new Account001(300);
        Account001 account001B = new Account001(300);
        Account001 account001C = new Account001(300);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for(int i=0 ; i<100 ; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    account001A.transfer(account001B,1);
                  /*  account001B.transfer(account001C,1);*/
                }
            }).start();
          countDownLatch.countDown();
        }

        System.out.println(account001A.balance);
        System.out.println(account001B.balance);
        System.out.println(account001C.balance);
    }

}
