package com;

import com.lss.Student;
import com.lss.Teacher;

public interface Visitor {

    public void visitor(Teacher teacher);

    public void visitor(Student student);
}
