package com.lss.strategymode.second;

import com.lss.strategymode.second.impl.impl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Zhijian implements impl<BigDecimal> {
    //直减
    @Override
    public BigDecimal operator(BigDecimal goodsPrice, BigDecimal discountPrice){
        return goodsPrice.subtract(discountPrice).compareTo(BigDecimal.ZERO) ==1 ? goodsPrice.subtract(discountPrice) : BigDecimal.ZERO;
    }
}
