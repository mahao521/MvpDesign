package com.moudle.dragger2s.moudle;

import com.moudle.dragger2s.bean.Person;
import com.moudle.dragger2s.bean.User;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/10/9.
 */

@Module
public class AppMoudle {

    @Provides
    public User getAppUser(){
        return new User("马豪",28);
    }
}
