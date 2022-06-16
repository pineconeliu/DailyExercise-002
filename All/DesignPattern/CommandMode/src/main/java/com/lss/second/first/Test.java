package com.lss.second.first;

public class Test {
    public static void main(String[] args) {
        Bulb bulb = new Bulb();
        Swittcher swittcher = new Swittcher(bulb);
        swittcher.up();
        System.out.println("-------------------------------");
        swittcher.down();
    }
}
