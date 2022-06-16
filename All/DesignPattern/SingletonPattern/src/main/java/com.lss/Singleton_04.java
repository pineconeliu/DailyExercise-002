package com.lss;

//懒汉模式
public class Singleton_04 {

    private static volatile Singleton_04 singleton_04 ;

   private Singleton_04(){

   }

   public static  synchronized  Singleton_04 getSingleton(){
       if(singleton_04 !=null) return singleton_04;
        synchronized (Singleton_04.class){
            singleton_04 =  new Singleton_04();
       }
       return  singleton_04;
   }

}
