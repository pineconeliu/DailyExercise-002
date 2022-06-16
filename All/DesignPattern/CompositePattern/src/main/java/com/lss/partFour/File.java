package com.lss.partFour;

public class File implements Node{
    private String name;
    @Override
    public void add(Node node) {
        System.out.println("不允许添加子节点");
    }

    public File(String name) {
        this.name = name;
    }
}
