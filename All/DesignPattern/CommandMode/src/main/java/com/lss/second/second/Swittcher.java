package com.lss.second.second;

//开关
public class Swittcher {

    private command command;

    public Swittcher(command command) {
        this.command = command;
    }

    public void up(){
        System.out.println("开关按钮向上");
        command.exec();
    }

    public void down(){
        System.out.println("开关按钮向下");
        command.reExec();
    }
}
