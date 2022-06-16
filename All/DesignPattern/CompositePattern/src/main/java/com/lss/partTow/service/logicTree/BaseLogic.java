package com.lss.partTow.service.logicTree;

import com.lss.partTow.vo.TreeNodeLinkVo;

import java.util.List;

//共用的一些逻辑
public abstract class BaseLogic implements LogicHandle{

    @Override
    public Long filter(String value, List<TreeNodeLinkVo> treeNodeLinkVos) {
        for(TreeNodeLinkVo treeNodeLinkVo :treeNodeLinkVos){
           if(decisionLogic(value,treeNodeLinkVo)){
               return treeNodeLinkVo.getToNodeId();
           }
        }
        return null;
    }

    //筛选 走具体的规则处理逻辑

    public boolean decisionLogic(String matterValue,TreeNodeLinkVo nodeLink){
        switch (nodeLink.getRuleWay()){
            case "1":
                return matterValue.equals(nodeLink.getRuleValue());
            case "2":
                return Double.parseDouble(matterValue) > Double.parseDouble(nodeLink.getRuleValue());
            case "3":
                return Double.parseDouble(matterValue) < Double.parseDouble(nodeLink.getRuleValue());
            case "4":
                return Double.parseDouble(matterValue) <= Double.parseDouble(nodeLink.getRuleValue());
            case "5":
                return Double.parseDouble(matterValue) >= Double.parseDouble(nodeLink.getRuleValue());
            default:
                return false;

        }
    }
}
