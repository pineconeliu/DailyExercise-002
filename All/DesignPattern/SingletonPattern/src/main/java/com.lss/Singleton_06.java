package com.lss;

import java.util.concurrent.atomic.AtomicReference;

//CAS
public class Singleton_06 {

    private static final AtomicReference<Singleton_06> INSTENCE = new AtomicReference<Singleton_06>();

   private Singleton_06(){

   }

   public static final Singleton_06 getSingleton() {
      for(; ;){
          Singleton_06 singleton_06 = INSTENCE.get();
          if(null != singleton_06) return singleton_06;
          INSTENCE.compareAndSet(null,new Singleton_06());
          INSTENCE.get();
      }
   }


}
