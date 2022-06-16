package com.lss;

//懒汉模式
public class Singleton_02 {

    private  static Singleton_02 singleton_02 ;

   private Singleton_02(){

   }

   public static Singleton_02 getSingleton_01(){
       if(singleton_02!=null)
       return  singleton_02;
      return new Singleton_02();
   }

}
