package com.lss.strategymode.second;

import com.lss.strategymode.second.impl.impl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Manjian implements impl<BigDecimal> {


    //满减
    @Override
    public BigDecimal operator(BigDecimal goodsPrice,BigDecimal discountPrice){
        return goodsPrice.compareTo(discountPrice) ==1 ? goodsPrice.subtract(discountPrice) : goodsPrice;
    }


}
