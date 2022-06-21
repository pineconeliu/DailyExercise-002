package com.lss.lock.retry;

import java.util.concurrent.Callable;

//测试回调函数
public class RequestTest   {

    //
    int retry = 3;


    public void request(){
        ConsumerTest consumerTest = new ConsumerTest();
        consumerTest.consumer(new ConsumerTest.CallBackInterface() {
            @Override
            public void call(boolean flag) {
                if(retry ==0){
                    System.out.println("count is out");
                    return;
                }
                retry();
            }
        });
    }

    public void retry(){
        retry = retry-1;
        System.out.println("retry something");
        request();
    }

    public static void main(String[] args) {
        RequestTest requestTest = new RequestTest();
        requestTest.request();

        ConsumerTest.CallBackInterface callBackInterface = new ConsumerTest.CallBackInterface() {

            @Override
            public void call(boolean flag) {

            }
        };
    }

}
