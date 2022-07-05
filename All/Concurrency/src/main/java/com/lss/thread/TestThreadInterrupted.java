package com.lss.thread;

public class TestThreadInterrupted {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
       //中断
       thread.interrupt();
      /*   //判断当前的中断状态
        boolean interrupted = thread.isInterrupted();
        System.out.println(interrupted);
        //判断当前的中断状态, 并且重置这个线程中断状态
        System.out.println( thread.interrupted());
        boolean interrupted1 = thread.isInterrupted();
        //因为上个方法重置了中断状态，导致现在是false
        System.out.println(interrupted1);
        */

      while (true){
            if(thread.isInterrupted()){
                break;
            }
        }

        System.out.println("测试睡眠的异常会不会重置中断状态");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            //因为上面调用了interrupt方法，下面调用sleep睡眠阻塞时，会触发抛出异常
            //同时interrupt方法和sleep方法都会重置中断状态 变为false
            boolean interrupted = thread.isInterrupted();
            System.out.println("中断状态已被重置:"+interrupted);
            e.printStackTrace();
        }

    }

}
