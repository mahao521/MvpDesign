package com.moudle.dragger2s.bean;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/10/9.
 */

public class Person {

    private static final String TAG = "Person";
    public Context mContext;
    public User mUser;
    private String name;

    public Person(){
        Log.d(TAG,"new person ()");
    }

    public Person(Context context){
        this.mContext = context;
    }

    public Person(Context context,User user){
        Log.d(TAG, "Person:  user");
        this.mContext = context;
        this.mUser = user;
    }

    public Person(Context context,String name){
        Log.d(TAG, "Person: ");
        this.mContext = context;
        this.name = name;
    }


}
