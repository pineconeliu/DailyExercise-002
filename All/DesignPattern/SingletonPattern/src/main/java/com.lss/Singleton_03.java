package com.lss;

//懒汉模式
public class Singleton_03 {

    private static Singleton_03 singleton_03 ;

   private Singleton_03(){

   }

   public static synchronized Singleton_03 getSingleton(){
       if(singleton_03!=null)
       return  singleton_03;
      return new Singleton_03();
   }

}
