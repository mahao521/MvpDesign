package com.moudle.dragger2s.component;

import android.content.Context;

import com.moudle.dragger2s.MainActivity;
import com.moudle.dragger2s.SecondActivity;
import com.moudle.dragger2s.annotation.ActivityScope;
import com.moudle.dragger2s.bean.Database;
import com.moudle.dragger2s.bean.Person;
import com.moudle.dragger2s.bean.User;
import com.moudle.dragger2s.moudle.MainMoudle;
import com.moudle.dragger2s.moudle.OtherMoudle;

import dagger.Component;
import dagger.Module;

/**
 * Created by Administrator on 2018/10/9.
 */

/**
 *   因为同一个activity只能注入一次，因此使用依赖；
 *
 *   没有scope的component不能依赖于有scope的component;
 */

@Component(modules = OtherMoudle.class)
public interface OtherComponent {

   Database getDataBase();
}
