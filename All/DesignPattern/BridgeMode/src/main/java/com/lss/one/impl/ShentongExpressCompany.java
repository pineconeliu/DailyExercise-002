package com.lss.one.impl;

import com.lss.AbstrExpressCompany;
import com.lss.IExpressTransportation;

public  class ShentongExpressCompany extends AbstrExpressCompany {


    public ShentongExpressCompany(IExpressTransportation expressTransportation) {
        super(expressTransportation);
    }

    @Override
    public  String delivery(){
        return  "申通公司使用"+expressTransportation.transport();
    }

}
