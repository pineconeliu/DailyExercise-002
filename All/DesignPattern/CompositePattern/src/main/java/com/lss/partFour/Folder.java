package com.lss.partFour;

import java.util.ArrayList;
import java.util.List;

//文件夹
public class Folder  implements Node{
   private String name;
   public   List<Node> nodeList = new ArrayList<>();

    @Override
    public void add(Node node) {
        nodeList.add(node);
    }

    public Folder(String name) {
        this.name = name;
    }
}
