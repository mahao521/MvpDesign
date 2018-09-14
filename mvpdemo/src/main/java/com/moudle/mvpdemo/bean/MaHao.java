package com.moudle.mvpdemo.bean;

/**
 * Created by Administrator on 2018/8/21.
 */

public class MaHao {

    private String name;
    private String sex;

    public MaHao(){

    }

    public MaHao(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
