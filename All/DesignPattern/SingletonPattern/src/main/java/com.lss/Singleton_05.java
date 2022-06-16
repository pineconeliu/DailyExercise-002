package com.lss;

//内部类
public class Singleton_05 {

   private Singleton_05(){

   }

   public static   Singleton_05 getSingleton() {
       return innerClass.singleton_05;
   }
   public static class innerClass{
       private static Singleton_05  singleton_05 = new Singleton_05();
   }

}
