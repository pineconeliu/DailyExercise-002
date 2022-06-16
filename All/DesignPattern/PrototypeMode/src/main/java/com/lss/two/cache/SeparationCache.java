package com.lss.two.cache;

import com.lss.two.AbstractSeparation;
import com.lss.two.MonkeyKing;
import com.lss.two.Naruto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定义一个可以获取所有实现分身功能的集合
 */
public class SeparationCache {


    private static Map<String, AbstractSeparation> hashMap = new ConcurrentHashMap<String, AbstractSeparation>();

    public static AbstractSeparation getHashMap(String type ) {
        AbstractSeparation abstractSeparation = hashMap.get(type);
        System.out.println(type+"开始获取分身能力，开始制造分身");
        return (AbstractSeparation)abstractSeparation.clone();
    }

    public static void setHashMap(String type , AbstractSeparation abstractSeparation) {
        hashMap.put(type,abstractSeparation);
    }

    //定义2中类型
    public static void loadCache(){
        AbstractSeparation monkeyKing = new MonkeyKing();
        monkeyKing.setType("MonkeyKing");

        AbstractSeparation naruto = new Naruto();
        naruto.setType("Naruto");

        setHashMap("Naruto",naruto);
        setHashMap("MonkeyKing",monkeyKing);
    }



}
