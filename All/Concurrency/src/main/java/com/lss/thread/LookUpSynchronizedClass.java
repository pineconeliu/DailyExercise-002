package com.lss.thread;

/**
 * 通过反编译查看synchronized修饰代码块和修饰方法的区别
 * @author liusongsheng
 * @date 2022/7/20 11:38
 * @return null
 */
public class LookUpSynchronizedClass {

    synchronized void method(){
        System.out.println("synchronized 修改方法");
    }

     void method1(){
         synchronized(this){
             System.out.println("synchronized 修饰代码块");
         }

    }
}
