package com.lss.strategymode.first;

import com.lss.strategymode.first.impl.impl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ZhijianF implements impl {
    //直减
    @Override
    public BigDecimal operator(BigDecimal goodsPrice, BigDecimal discountPrice) {
        return goodsPrice.subtract(discountPrice).compareTo(BigDecimal.ZERO) == 1 ? goodsPrice.subtract(discountPrice) : BigDecimal.ZERO;
    }
}
