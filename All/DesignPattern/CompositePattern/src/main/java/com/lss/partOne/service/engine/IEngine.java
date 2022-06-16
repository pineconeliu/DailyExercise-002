package com.lss.partOne.service.engine;

import com.lss.partOne.aggregates.TreeRich;
import com.lss.partOne.vo.EngineResult;

import java.util.Map;

public interface IEngine {
    EngineResult process(final Long treeId, final String userId, TreeRich treeRich, Map<String, String> decisionMatter);
}
