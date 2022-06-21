package com.lss.lock.retry;

import java.util.List;
import java.util.concurrent.Callable;

public class ConsumerTest   {


    public  void consumer(CallBackInterface callBackInterface){
        System.out.println("consumer XXXXX");
        boolean flag = false;
        if(!flag){
          callBackInterface.call(flag);
        }
    }


    public interface CallBackInterface {
        void call(boolean flag);
    }

}
