package com.lss.strategymode.first;

import com.lss.strategymode.first.enu.enumTest;
import com.lss.strategymode.first.impl.impl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class choose {

    @Bean
    public Map<enumTest, impl> Integration(DiscountF discount, ManjianF manjian, NPurchaseF NPurchase, ZhijianF zhijian) {
        Map<enumTest, impl> map = new ConcurrentHashMap(8);
        map.put(enumTest.discount, discount);
        map.put(enumTest.manjian, manjian);
        map.put(enumTest.nPurchase, NPurchase);
        map.put(enumTest.zhijian, zhijian);
        return map;
    }

}
