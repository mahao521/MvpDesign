package com.moudle.dragger2s.moudle;

import android.content.Context;

import com.moudle.dragger2s.annotation.ActivityScope;
import com.moudle.dragger2s.annotation.PersonWithOther;
import com.moudle.dragger2s.annotation.PersonWithUser;
import com.moudle.dragger2s.bean.Database;
import com.moudle.dragger2s.bean.Person;
import com.moudle.dragger2s.bean.User;
import com.moudle.dragger2s.component.OtherComponent;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/10/9.
 */

@Module
public class OtherMoudle {

    private Context mContext;

    public OtherMoudle(Context context){
        this.mContext = context;
    }

    @Provides
     Context getContext(){
        return this.mContext;
    }

    @Provides
    public Database getOtherData(Context context) {
        return new Database(1,"insert");
    }
}
