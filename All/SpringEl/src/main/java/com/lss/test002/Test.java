package com.lss.test002;

import com.google.common.base.MoreObjects;
import com.google.common.eventbus.EventBus;

import java.util.HashMap;

public class Test {

    public static void main(String[] args) {

        //创建EventBus对象，并给予identifier
        //identifier：此总线的简短名称，用于日志记录。应该是一个有效的java标识符。
        EventBus eventBus = new EventBus("choxsu");

        //注册所有的订阅
        eventBus.register(new HelloEventListener());
        eventBus.register(new Hello1EventListener());
        //eventBus.register(new Hello2EventListener());

        //发布事件
        eventBus.post(new OrderEvent("hello"));
        eventBus.post(new OrderEvent("world"));

        eventBus.post("hello world");
        eventBus.post("hello world2");

        eventBus.post(1);
        eventBus.post(12);

        Integer integer = MoreObjects.firstNonNull(null, null);
        System.out.println(integer);

        ThreadLocal.withInitial(() -> new Integer(54));
    }
}
