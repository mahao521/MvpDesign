package com.moudle.dragger2s.component;

import com.moudle.dragger2s.MainActivity;
import com.moudle.dragger2s.SecondActivity;
import com.moudle.dragger2s.moudle.AppMoudle;
import com.moudle.dragger2s.moudle.MainMoudle;
import com.moudle.dragger2s.moudle.OtherMoudle;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2018/10/9.
 */

// 可以依赖于moudle，也可以依赖于 component;
@Singleton
/*@Component(dependencies = {Appmoudle.class},modules = {MainMoudle.class})*/
/*@Component(dependencies = {AppComponent.class,OtherComponent.class},modules = {MainMoudle.class})*/
@Component(dependencies = {AppComponent.class,OtherComponent.class},modules = {MainMoudle.class})
public interface MainComponent {

    /**
     *   这里activity不能使用泛型，必须使用具体的activity;
     * @param activity
     */
    void inject(MainActivity activity);
    void inject(SecondActivity activity);
}
