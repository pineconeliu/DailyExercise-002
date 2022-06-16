package com.lss;

import com.Visitor;

import java.util.Random;

/**
 * @author 10380
 */
public class Teacher extends User {

    public Teacher(String roleName, String className) {
        super(roleName, className);
    }

    //paiming
    public Integer shengxuelv() {
        int i = new Random().nextInt(20);
        return i / 2;
    }

    @Override
    public void accpect(Visitor visitor) {
        visitor.visitor(this);
    }
}
