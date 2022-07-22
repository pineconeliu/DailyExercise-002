package com.lss.test001;

public class User {

    private String userName;
    private Integer credits;
    private Place place;
    public String [] arr;

    /**
     * 添加三个方法 使用spel调用
     * 共有方法
     * 静态方法
     * 私有方法
     */

    public  Boolean  method1(String value){
        return  value.equalsIgnoreCase("admin") ;
    }

    public  static  Boolean method2(String value){
        return  value.equalsIgnoreCase("admin");
    }

    private  Boolean method3(String value){
        return  value.equalsIgnoreCase("admin");
    }
  
    //setter getter  

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String[] getArr() {
        return arr;
    }

    public void setArr(String[] arr) {
        this.arr = arr;
    }
}
