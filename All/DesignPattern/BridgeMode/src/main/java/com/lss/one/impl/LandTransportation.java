package com.lss.one.impl;

import com.lss.IExpressTransportation;

public class LandTransportation implements IExpressTransportation {
    @Override
    public String transport() {
        return "陆地运输";
    }
}
