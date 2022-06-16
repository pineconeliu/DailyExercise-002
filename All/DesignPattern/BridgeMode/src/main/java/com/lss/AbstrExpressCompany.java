package com.lss;

public abstract class AbstrExpressCompany {

    protected IExpressTransportation expressTransportation;

    public AbstrExpressCompany(IExpressTransportation expressTransportation) {
        this.expressTransportation = expressTransportation;
    }

    public abstract String delivery();

}
