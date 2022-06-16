package com.lss.strategymode.first.impl;

import java.math.BigDecimal;

public interface impl {
    BigDecimal operator(BigDecimal goodsPrice, BigDecimal discountPrice);
}
