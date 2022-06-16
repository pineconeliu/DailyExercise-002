package com.lss.second.second;

///家用类的电器 灯泡
public class Bulb implements AbstractLifeGoods {

    @Override
    public void onLight(){
        System.out.println("灯泡亮起来了");
    }
    @Override
    public void downLight(){
        System.out.println("灯泡暗淡了");
    }
}


