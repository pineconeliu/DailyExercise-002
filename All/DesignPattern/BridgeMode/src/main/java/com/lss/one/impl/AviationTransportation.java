package com.lss.one.impl;

import com.lss.IExpressTransportation;

public class AviationTransportation implements IExpressTransportation {
    @Override
    public String transport() {
        return "航空运输";
    }
}
