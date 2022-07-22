package com.lss.aqs;

public class Test {
   public static void main(String[] args) {
      String str1 = new StringBuilder("reent1").append("Sb").toString();
      System.out.println(str1+"----"+System.identityHashCode(str1));
      System.out.println(str1.intern()+"----"+System.identityHashCode(str1.intern()));
      System.out.println("equals方法："+str1.equals(str1.intern()));
      System.out.println(str1 == str1.intern() );
      String str2 = new StringBuilder().append("reent1").toString();
      System.out.println(str2+"----"+System.identityHashCode(str2));
      System.out.println(str2.intern()+"----"+System.identityHashCode(str2.intern()));
      System.out.println(str2 ==str2.intern());
   }
}
