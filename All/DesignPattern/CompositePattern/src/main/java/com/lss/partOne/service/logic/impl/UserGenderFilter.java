package com.lss.partOne.service.logic.impl;


import com.lss.partOne.service.logic.BeseLogic;

import java.util.Map;

public class UserGenderFilter extends BeseLogic {

    @Override
    public String matterValue(Long treeId, String userId, Map<String, String> decisionMatter) {
        return decisionMatter.get("gender");
    }

}
