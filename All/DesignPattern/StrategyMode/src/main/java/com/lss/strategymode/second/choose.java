package com.lss.strategymode.second;


import com.lss.strategymode.second.impl.impl;

import java.math.BigDecimal;

public class choose<T> {

    private impl<T> impl;

   public choose(impl<T> impl){
        this.impl = impl;
    }

    public BigDecimal operator(BigDecimal goodsPrice, T couponInfo ){
        return impl.operator(goodsPrice,couponInfo);
    }

}
