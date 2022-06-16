package com.lss;

/**
 * 雇员
 */
public class Employee  {

    private  String uid; //ID

    private String userName;//名字

    private String desc;//备注

    public Employee(String uid, String userName, String desc) {
        this.uid = uid;
        this.userName = userName;
        this.desc = desc;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
