package com.lss.partTow.vo;

import lombok.Data;

import java.util.List;

@Data
public class TreeNodeVo {
    private Integer nodeType;//树的结点类型，1-子叶 2-果实

    private Long treeId;

    private Long nodeId;

    private String nodeApplication;//结点的用途

    private String nodeApplicationKey;//结点的用途key

    private String nodeValue; //果实结点的值-最终符合条件的用户编号

    private List<TreeNodeLinkVo> treeNodeLinkList;

}
