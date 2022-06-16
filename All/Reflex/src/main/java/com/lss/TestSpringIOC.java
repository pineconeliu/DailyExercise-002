package com.lss;

import com.lss.factory.TestSpringFactory;
import com.lss.pojo.User;

/**
 * IOC 依赖控制反转，容器会自动帮你把类给实例化，用户可以直接拿来使用。同时，容器管理着整个bean的生命周期，用户不必去考虑创建和销毁的事
 */
public class TestSpringIOC {

    public static void main(String[] args) {
        TestSpringFactory testSpringFactory = new TestSpringFactory();
        testSpringFactory.create();
        User user = (User) testSpringFactory.map.get("user");
        System.out.println(user.toString());
        // todo需要去考虑非string类型的字段如何塞值
    }


}
