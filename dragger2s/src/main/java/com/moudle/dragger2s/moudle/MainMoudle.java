package com.moudle.dragger2s.moudle;

import android.content.Context;
import android.util.Log;

import com.moudle.dragger2s.annotation.PersonWithUser;
import com.moudle.dragger2s.bean.Person;
import com.moudle.dragger2s.bean.User;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/10/9.
 */

@Module
public class MainMoudle {

    private static final String TAG = "MainMoudle";
    private Context mContext;
    public MainMoudle(Context context){
        mContext = context;
    }

    @Provides
    public Context getContext(){
        return this.mContext;
    }

    @Named("empty")
    @Singleton
    @Provides
    Person providerPerson(){
        Log.d(TAG, "a person create from MainMoudle");
        return new Person();
    }

    @Named("context")
    @Singleton
    @Provides
    Person providerConPerson(Context context){
        Log.d(TAG, "providerConPerson: ");
        return new Person(context);
    }

    @PersonWithUser
    @Singleton
    @Provides
    Person providerUserPerson(Context context,User user){
        Log.d(TAG, "providerUserPerson:  + user");
        return new Person(context,user);
    }

}
