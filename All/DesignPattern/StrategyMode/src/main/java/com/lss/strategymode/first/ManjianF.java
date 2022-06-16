package com.lss.strategymode.first;

import com.lss.strategymode.first.impl.impl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ManjianF implements impl {


    //满减
    @Override
    public BigDecimal operator(BigDecimal goodsPrice, BigDecimal discountPrice) {
        return goodsPrice.compareTo(discountPrice) == 1 ? goodsPrice.subtract(discountPrice) : goodsPrice;
    }


}
