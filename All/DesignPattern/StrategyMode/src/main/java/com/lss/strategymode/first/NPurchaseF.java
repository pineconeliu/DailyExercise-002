package com.lss.strategymode.first;

import com.lss.strategymode.first.impl.impl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class NPurchaseF implements impl {

    //n元购
    @Override
    public BigDecimal operator(BigDecimal goodsPrice, BigDecimal nPrice){
        return nPrice;
    }



}
