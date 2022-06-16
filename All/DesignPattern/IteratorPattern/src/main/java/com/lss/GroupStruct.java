package com.lss;


import com.lss.util.Collection;
import com.lss.util.Iterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//组织结构
public class GroupStruct implements Collection<Employee,Link> {

    private String groupId;      //组织ID，遍历的头部id

    private String groupName;      //组织名称

    private Map<String ,Employee> employeeMap =  new ConcurrentHashMap<String ,Employee>();//雇员信息


    private Map<String , List<Link>> linkMap =  new ConcurrentHashMap(); // 存储每层的树形节点信息 id->list


    private Map<String , String> revertMap =  new ConcurrentHashMap(); // 用于向上查找节点的数据

    public GroupStruct(String groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    @Override
    public boolean add(Employee employee) {
        employeeMap.put(employee.getUid(),employee);
        return true;
    }

    @Override
    public boolean remove(Employee employee) {
        return employeeMap.remove(employee.getUid())!=null;
    }

    @Override
    public boolean addLink(String key, Link link) {
        revertMap.put(link.getToId(),link.getFromId());
        if(linkMap.get(key)!=null){
           return linkMap.get(key).add(link);
        }else {
            List<Link> links = new ArrayList<>();
            links.add(link);
            linkMap.put(key,links);
            return true;
        }
    }

    @Override
    public boolean removeLink(String key) {
        return linkMap.remove(key)!=null;
    }

    @Override
    public Iterator<Employee> iterator() {
        return new Iterator<Employee>() {
            HashMap<String, Integer> map = new HashMap<>();

            int totalIdx = 0;
            private String formId = groupId;

            private String toId = groupId;

            @Override
            public boolean hashNext() {
                return totalIdx < employeeMap.size();
            }

            @Override
            public Employee next() {
                List<Link> links = linkMap.get(toId);
                int cursorIdx = getCursorIdx(toId);

                //同级节点扫描=没有下一个层的时候
                if(null == links){
                    cursorIdx = getCursorIdx(formId);
                    links = linkMap.get(formId);
                }

                // 上级节点扫描--互换 formId 和toId的值
                while (cursorIdx >links.size() -1){
                    formId = revertMap.get(formId);
                    cursorIdx = getCursorIdx(formId);
                    links = linkMap.get(formId);
                }

                //获取当前节点信息
                Link link = links.get(cursorIdx);
                toId = link.getToId();
                formId = link.getFromId();
                totalIdx++;

                return employeeMap.get(link.getToId());
            }
            //获取当前光标的位置
            public int getCursorIdx(String key){
                int idx = 0;
                if(map.containsKey(key)){
                    idx = map.get(key);
                    map.put(key,++idx);
                }else{
                    map.put(key,idx);
                }
                return idx;
            }



        };
    }
}
