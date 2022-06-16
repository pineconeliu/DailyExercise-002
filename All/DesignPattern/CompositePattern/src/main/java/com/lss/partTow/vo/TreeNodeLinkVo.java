package com.lss.partTow.vo;

import lombok.Data;

@Data
public class TreeNodeLinkVo {

    private Long formNodeId; // 从某某结点开始的id

    private Long toNodeId;// 到某某结点结束的id

    private String ruleWay; // 树根茎的规则判断方式

    private String ruleValue; // 树根茎的规则判断值

    private Long treeId;

}
