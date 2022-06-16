package com.lss.two;

import com.lss.two.cache.SeparationCache;

public class Demo {
    public static void main(String[] args) {
        SeparationCache.loadCache();

        AbstractSeparation monkeyKing = SeparationCache.getHashMap("MonkeyKing");
        monkeyKing.doJob();


        AbstractSeparation naruto = SeparationCache.getHashMap("Naruto");
        naruto.doJob();
    }
}
