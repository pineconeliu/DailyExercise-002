package com.lss.one.impl;

import com.lss.AbstrExpressCompany;
import com.lss.IExpressTransportation;

public  class YuantongExpressCompany extends AbstrExpressCompany {


    public YuantongExpressCompany(IExpressTransportation expressTransportation) {
        super(expressTransportation);
    }

    @Override
    public  String delivery(){
        return  "圆通公司使用"+expressTransportation.transport();
    }

}
