package com.lss.partTow.aggregates;

import com.lss.partTow.vo.TreeNodeVo;
import com.lss.partTow.vo.TreeRootVO;
import lombok.Data;

import java.util.Map;

/**
 * guiz
 */

@Data
public class TreeRichVo {

    private TreeRootVO treeRoot;                          //树根信息
    private Map<Long, TreeNodeVo> treeNodeMap;        //树节点ID -> 子节点

}
