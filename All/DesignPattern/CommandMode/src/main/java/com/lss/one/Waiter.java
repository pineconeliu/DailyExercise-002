package com.lss.one;

import com.lss.one.impl.ShandongCooker;
import com.lss.one.impl.ShandongCuisine;
import com.lss.one.impl.SichuanCooker;
import com.lss.one.impl.SichuanCuisine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//服务员
public class Waiter {

    public Map<Integer, Cuisine> giveMenu() {
        //提供菜单
        HashMap<Integer, Cuisine> map = new HashMap<>();
        map.put(1, new ShandongCuisine(new ShandongCooker()));
        map.put(2, new SichuanCuisine(new SichuanCooker()));
        return map;
    }


    //下单
    public void order(List<Cuisine> list) {
        //提供菜单
        for (Cuisine cuisine : list) {
            cuisine.orderCuisine();
            cuisine.cooker();
        }
    }


}
