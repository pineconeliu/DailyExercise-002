package com.lss;

import com.Visitor;

public class PrimaryVisitor implements Visitor {
    @Override
    public void visitor(Teacher teacher) {
        System.out.println("校长访问" + teacher.shengxuelv());
    }

    @Override
    public void visitor(Student student) {
        System.out.println("校长访问" + student.paiming());
    }
}
