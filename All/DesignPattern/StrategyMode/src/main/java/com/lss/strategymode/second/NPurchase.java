package com.lss.strategymode.second;

import com.lss.strategymode.second.impl.impl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class NPurchase implements impl<BigDecimal> {

    //n元购
    @Override
    public BigDecimal operator(BigDecimal goodsPrice, BigDecimal nPrice){
        return nPrice;
    }



}
