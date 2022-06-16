package com.lss.strategymode.second;

import com.lss.strategymode.second.impl.impl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class Discount implements impl<String> {


    //折扣
    @Override
    public BigDecimal operator(BigDecimal goodsPrice, String discount){

        return goodsPrice.multiply(new BigDecimal(discount)).divide(new BigDecimal(100), RoundingMode.HALF_UP);
    }


}
