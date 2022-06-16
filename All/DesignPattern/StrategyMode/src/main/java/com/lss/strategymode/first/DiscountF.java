package com.lss.strategymode.first;

import com.lss.strategymode.first.impl.impl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DiscountF implements impl {


    //折扣
    @Override
    public BigDecimal operator(BigDecimal goodsPrice, BigDecimal discountPrice) {

        return goodsPrice.multiply(discountPrice).divide(new BigDecimal(100), RoundingMode.HALF_UP);
    }


}
