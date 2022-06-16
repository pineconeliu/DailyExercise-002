package com.lss;

import com.Visitor;

public class ParentVisitor implements Visitor {
    @Override
    public void visitor(Teacher teacher) {
        System.out.println("家长访问" + teacher.shengxuelv());
    }

    @Override
    public void visitor(Student student) {
        System.out.println("家长访问" + student.paiming());
    }
}
