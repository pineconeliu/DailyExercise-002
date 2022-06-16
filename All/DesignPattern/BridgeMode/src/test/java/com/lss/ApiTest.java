package com.lss;
import com.lss.one.impl.AviationTransportation;
import com.lss.one.impl.LandTransportation;
import com.lss.one.impl.ShentongExpressCompany;
import com.lss.one.impl.YuantongExpressCompany;
import org.junit.Test;

public class ApiTest {

    @Test
    public void deliverInfo(){
        AbstrExpressCompany company = new ShentongExpressCompany(new AviationTransportation());
        System.out.println(company.delivery());

        AbstrExpressCompany company1 = new ShentongExpressCompany(new LandTransportation());
        System.out.println(company1.delivery());

        AbstrExpressCompany company2 = new YuantongExpressCompany(new AviationTransportation());
        System.out.println(company2.delivery());

        AbstrExpressCompany company3 = new YuantongExpressCompany(new AviationTransportation());
        System.out.println(company3.delivery());
    }
    
}
