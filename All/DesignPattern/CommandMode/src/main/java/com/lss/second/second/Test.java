package com.lss.second.second;

public class Test {
    public static void main(String[] args) {
        //拿到一个灯泡
//        AbstractLifeGoods bulb = new Bulb();
        AbstractLifeGoods lifeGoods = new WashCloth();
        //找到使用灯泡的命令类
        SwitherSignal electricalSignal = new SwitherSignal(lifeGoods);
        Swittcher swittcher = new Swittcher(electricalSignal);
        swittcher.up();
        System.out.println("-------------------------------");
        swittcher.down();
    }
}
