package com.lss.partThree;

import lombok.Data;

@Data
public class Goods implements Articles{

    private String name;

    private Double price;

    public Goods(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void show() {
        System.out.println("袋子中有" + name + "装入袋子");
    }

    @Override
    public Double totalPrice() {
        return price;
    }
}
