package com.lss.partTow.service.engine;



import com.lss.partTow.service.logicTree.LogicHandle;
import com.lss.partTow.service.logicTree.impl.UseAgeHandle;
import com.lss.partTow.service.logicTree.impl.UseSexHandle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 微信公众号：bugstack虫洞栈 | 专注原创技术专题案例
 * 论坛：http://bugstack.cn
 * Create by 小傅哥 on @2020
 */
public class EngineConfig {

    static Map<String, LogicHandle> logicFilterMap;

    static {
        logicFilterMap = new ConcurrentHashMap ();
        logicFilterMap.put("userAge", new UseAgeHandle());
        logicFilterMap.put("userSex", new UseSexHandle());
    }

    public static Map<String, LogicHandle> getLogicFilterMap() {
        return logicFilterMap;
    }

    public static void setLogicFilterMap(Map<String, LogicHandle> logicFilterMap) {
        EngineConfig.logicFilterMap = logicFilterMap;
    }
}
