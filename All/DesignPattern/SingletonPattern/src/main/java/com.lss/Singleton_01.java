package com.lss;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//饿汉模式
public class Singleton_01 {

    private static Singleton_01 singleton_01 = new Singleton_01();

   private Singleton_01(){

   }

   public static Singleton_01 getSingleton_01(){
       return  singleton_01;
   }

}
