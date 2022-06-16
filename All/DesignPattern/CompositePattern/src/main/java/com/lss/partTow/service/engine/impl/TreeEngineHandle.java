package com.lss.partTow.service.engine.impl;

import com.lss.partTow.aggregates.TreeRichVo;
import com.lss.partTow.service.engine.EnginBase;
import com.lss.partTow.vo.TreeNodeVo;
import com.lss.partOne.vo.EngineResult;

import java.util.Map;

public class TreeEngineHandle extends EnginBase  {


    @Override
    public EngineResult process(Long treeId, String userId, TreeRichVo treeRich, Map<String, String> decisionMatter) {
            // 决策流程
            TreeNodeVo treeNode = decisionProcess(treeRich,  decisionMatter);
            // 决策结果
            return new EngineResult(userId, treeId, treeNode.getNodeId(), treeNode.getNodeValue());
        }

    }
