package com.lss.second.second;

//工作类电器中的手提电脑
public class Laptop implements AbstractJobGoods {
    @Override
    public void onLight() {
        System.out.println("电脑开机");
    }

    @Override
    public void downLight() {
        System.out.println("电脑关机");
    }
}
