package com.lss;

import com.Visitor;

import java.util.Random;

/**
 * @author 10380
 */
public class Student extends User {


    public Student(String roleName, String className) {
        super(roleName, className);
    }

    //paiming
    public Integer paiming() {
        int i = new Random().nextInt(20);
        return i;
    }

    @Override
    public void accpect(Visitor visitor) {
        visitor.visitor(this);
    }

}
