package com.lss.one;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Customer {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        Map<Integer, Cuisine> cuisineMap = waiter.giveMenu();
        List<Cuisine> list = new ArrayList<>();
        cuisineMap.forEach((Integer, cuisine) -> {
            list.add(cuisine);
        });
        waiter.order(list);

        //如果自由组合菜系和厨师呢？


    }
}
