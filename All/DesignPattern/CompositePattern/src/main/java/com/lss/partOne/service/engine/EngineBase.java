package com.lss.partOne.service.engine;

import com.lss.partOne.aggregates.TreeRich;
import com.lss.partOne.service.logic.LogicFilter;
import com.lss.partOne.vo.TreeNode;
import com.lss.partOne.vo.TreeRoot;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class EngineBase extends EngineConfig implements IEngine{

    private Logger logger = LoggerFactory.getLogger(EngineBase.class);


    protected TreeNode engineDecisionMaker(TreeRich treeRich, Long treeId , String userID, Map<String,String> decisionMatter){
        TreeRoot treeRoot = treeRich.getTreeRoot();
        Map<Long, TreeNode> treeNodeMap = treeRich.getTreeNodeMap();
        Long treeRootNodeId = treeRoot.getTreeRootNodeId();
        TreeNode treeNode = treeNodeMap.get(treeRootNodeId);
        while (treeNode.getNodeType().equals(1)){
            String ruleKey = treeNode.getRuleKey();
            LogicFilter logicFilter = logicFilterMap.get(ruleKey);
            //
            String matterValue = logicFilter.matterValue(treeId, userID, decisionMatter);
            Long nextNode = logicFilter.filter(matterValue, treeNode.getTreeNodeLinkList());
            treeNode = treeNodeMap.get(nextNode);

            logger.info("决策树引擎=>{} userId：{} treeId：{} treeNode：{} ruleKey：{} matterValue：{}", treeRoot.getTreeName(), userID, treeId, treeNode.getTreeNodeId(), ruleKey, matterValue);
        }
        return treeNode;
    }


}
