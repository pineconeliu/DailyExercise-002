package com.lss.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 在 try{} 外获取锁主要考虑两个方面：
 * 1. 如果没有获取到锁就抛出异常，最终释放锁肯定是有问题的，因为还未曾拥有锁谈何释放锁呢
 * 2. 如果在获取锁时抛出了异常，也就是当前线程并未获取到锁，但执行到 finally代码时，如果恰巧别的线程获取到了锁，则会被释放掉（无故释放）
 */
public class TestTry {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        //lock
      reentrantLock.lock();
     /*   throwException();*/
        try{
            throwException();
            int i=0;
        }catch (Exception e){
            throw  new RuntimeException("未进入try代码块，直接抛出错误");
        }finally {
            reentrantLock.unlock();
            System.out.println("finally，会触发吗？");
        }
    }

    public  static void throwException(){
        throw  new RuntimeException("未进入try代码块，直接抛出错误");
    }
}
