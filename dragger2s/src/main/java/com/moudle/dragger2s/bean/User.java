package com.moudle.dragger2s.bean;

/**
 * Created by Administrator on 2018/10/9.
 */

public class User {

    String name;
    int age;

    public User(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
