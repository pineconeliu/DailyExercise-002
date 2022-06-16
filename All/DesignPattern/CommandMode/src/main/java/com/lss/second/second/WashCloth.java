package com.lss.second.second;

//家用类的电器，洗衣机
public class WashCloth implements AbstractLifeGoods {
    @Override
    public void onLight() {
        System.out.println("洗衣机开机");
    }

    @Override
    public void downLight() {
        System.out.println("洗衣机关机");
    }
}
