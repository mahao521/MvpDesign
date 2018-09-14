package com.moudle.mvpcore.rx.rxbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/8/24.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) //一直保存
public @interface RegisterBus {

}
