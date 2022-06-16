package com.lss.two;

/**
 * 抽象分身类
 */
public abstract class AbstractSeparation implements  Cloneable {

    private  String type;

    //定义一个分身可以用来干活的方法
    abstract void doJob();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Object clone(){
        Object clone = null;
        try {
            clone = super.clone();
        }catch (CloneNotSupportedException cloneNotSupportedException){
            cloneNotSupportedException.printStackTrace();
        }
        return  clone;
    }

}
