package com.lss.strategymode.second.impl;

import java.math.BigDecimal;

public interface impl<T> {
     BigDecimal operator(BigDecimal goodsPrice, T couponInfo);
}
