package com.lss.partTow.service.logicTree;

import com.lss.partTow.vo.TreeNodeLinkVo;

import java.util.List;
import java.util.Map;

// 逻辑处理，也就是用树的根茎规则key 和规则value进行逻辑处理
public interface  LogicHandle {

    // 获取最终的果实，也就是得到符合条件用户的编码
//    Long getFruit ();

    // 根据前端入参值获取下一个结点
    Long filter (String value, List<TreeNodeLinkVo> treeNodeLinkVos) ;

    //获取前端传来的值，表示走哪一个规则
    String getMatterValue(Long treeId, String userId, Map<String,String> decisionMatter);


}
