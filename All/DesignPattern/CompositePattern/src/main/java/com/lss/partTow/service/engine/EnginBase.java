package com.lss.partTow.service.engine;

import com.lss.partTow.aggregates.TreeRichVo;
import com.lss.partTow.service.logicTree.LogicHandle;
import com.lss.partTow.vo.TreeNodeVo;
import com.lss.partTow.vo.TreeRootVO;

import java.util.Map;

public abstract class EnginBase extends EngineConfig implements IEngine{

    //决策流程
    public TreeNodeVo decisionProcess(TreeRichVo treeRich, Map<String, String> decisionMatter){
        TreeRootVO treeRoot = treeRich.getTreeRoot();
        Long treeId = treeRoot.getTreeId();
        Map<Long, TreeNodeVo> treeNodeMap = treeRich.getTreeNodeMap();
        TreeNodeVo treeNodeVo = treeNodeMap.get(treeId);
        while (treeNodeVo.getNodeId().equals(1)){
            String applicationKey = treeNodeVo.getNodeApplicationKey();
            LogicHandle logicHandle = logicFilterMap.get(applicationKey);
            String matterValue = logicHandle.getMatterValue(treeId, "", decisionMatter);
            Long aLong = logicHandle.filter(matterValue, treeNodeVo.getTreeNodeLinkList());
            treeNodeVo = treeNodeMap.get(aLong);
            System.out.println("决策树引擎=>  treeId：{}"+treeId+" treeNode："+treeNodeVo.getNodeId()+" ruleKey："+applicationKey+" matterValue："+ matterValue);
        }
        return treeNodeVo;
    }
}
