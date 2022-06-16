package com.lss.partOne.service.logic;

import com.lss.partOne.vo.TreeNodeLink;

import java.util.List;
import java.util.Map;

public interface LogicFilter {

    Long filter(String matterValue, List<TreeNodeLink> treeNodeLinkList);

    String matterValue(Long treeId, String userId, Map<String,String> decisionMatter);
}
