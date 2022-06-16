package com.lss.one.impl;

import com.lss.one.Cooker;
import com.lss.one.Cuisine;

//山东菜系
public class ShandongCuisine implements Cuisine {

    private Cooker cooker;

    public ShandongCuisine(Cooker cooker) {
        this.cooker = cooker;
    }

    @Override
    public void orderCuisine() {
        System.out.println("客人点了山东菜");
    }

    @Override
    public void cooker() {
        cooker.cooker();
    }
}
