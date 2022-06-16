package com.lss.partThree;

import java.util.ArrayList;
import java.util.List;

public class Bag implements Articles{

    private List<Articles> goodsList = new ArrayList<>();

    //装东西的一个方法
    void putStuff(Articles goods){
        goodsList.add(goods);
    };

    //展示有哪些东西的一个方法
    @Override
    public void show() {
        for (Articles obj : goodsList) {
            obj.show();
        }
    }

    @Override
    public Double totalPrice(){
        Double totalPrice = 0.0;
        for (Articles obj : goodsList) {
           totalPrice =totalPrice+ obj.totalPrice();
        }
        return totalPrice;
    }

}
