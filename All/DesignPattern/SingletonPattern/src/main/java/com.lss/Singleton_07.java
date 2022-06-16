package com.lss;

//枚举
public enum  Singleton_07 {


   INSTANCE;

   public void test(){
       System.out.println("获取成功实例");
   }


    public static void main(String[] args) {
        Singleton_07.INSTANCE.test();
    }
}
