package com.lss.personal;

public class Person extends  BuilderPerson{

    public Person(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", faceShape='" + faceShape + '\'' +
                ", body='" + body + '\'' +
                ", cloth='" + cloth + '\'' +
                '}';
    }
}
