package com.lss.partTow.service.logicTree.impl;

import com.lss.partTow.service.logicTree.BaseLogic;

import java.util.Map;

public class UseAgeHandle extends BaseLogic {
    @Override
    public String getMatterValue(Long treeId, String userId, Map<String, String> decisionMatter) {
        return decisionMatter.get("userAge");
    }
}
