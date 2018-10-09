package com.moudle.dragger2s.component;

import com.moudle.dragger2s.bean.User;
import com.moudle.dragger2s.moudle.AppMoudle;

import dagger.Component;

/**
 * Created by Administrator on 2018/10/9.
 */

@Component(modules = AppMoudle.class)
public interface AppComponent {

     /**
      *    这个component提供一个user对象给MainMoudler；  需要写这样一个抽象方法。
      * @return
      */
     User getUser();
}
