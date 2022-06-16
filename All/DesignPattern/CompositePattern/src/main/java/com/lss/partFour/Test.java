package com.lss.partFour;

public class Test {
    public static void main(String[] args) {
        Folder folder = new Folder("父类文件夹");
        Node node = new Folder("二级文件夹");
        folder.add(node);
        Node node1 = new File("具体文件");
        folder.add(node1);
        System.out.println(folder.nodeList);
    }
}
