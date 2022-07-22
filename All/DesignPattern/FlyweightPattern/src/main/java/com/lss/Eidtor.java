package com.lss;

import java.util.ArrayList;
import java.util.List;

//编辑器
public class Eidtor {



    public static void main(String[] args) {
        // 定义一个list集合存放文字表示这个编辑器写了多少个字
        List<Object> arrayList = new ArrayList<>();

        for(int i=0;i<100;i++){
            Charst charst = new Charst('A', "蓝色"+i, true);
            arrayList.add(charst);
        }
        arrayList.forEach(System.out::println);

    }

}
