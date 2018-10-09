package com.moudle.dragger2s.bean;

/**
 * Created by Administrator on 2018/10/9.
 */

public class Database {

    int versionCode;
    String label;

    public Database(int versionCode, String label) {
        this.versionCode = versionCode;
        this.label = label;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
