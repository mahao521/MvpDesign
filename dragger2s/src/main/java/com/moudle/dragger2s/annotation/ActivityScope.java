package com.moudle.dragger2s.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Administrator on 2018/10/9.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME) //运行时，class会被加载到虚拟机
public @interface ActivityScope {
}
