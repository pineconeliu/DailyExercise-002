package com.lss.partTow.service.engine;

import com.lss.partTow.aggregates.TreeRichVo;
import com.lss.partOne.vo.EngineResult;

import java.util.Map;

//决策引擎 给足动力去遍历这个树，并通过结合规则得到果实
public interface IEngine {
    EngineResult process(final Long treeId, final String userId, TreeRichVo treeRich, Map<String, String> decisionMatter);
}