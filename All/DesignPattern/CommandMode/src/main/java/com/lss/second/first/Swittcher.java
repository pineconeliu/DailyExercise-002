package com.lss.second.first;

//开关
public class Swittcher {

    private Bulb bulb;

    public Swittcher(Bulb bulb) {
        this.bulb = bulb;
    }

    public void up(){
        System.out.println("开关按钮向上");
        bulb.onLight();
    }

    public void down(){
        System.out.println("开关按钮向下");
        bulb.downLight();
    }
}
