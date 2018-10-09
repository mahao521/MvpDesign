package com.moudle.dragger2s.annotation;

/**
 * Created by Administrator on 2018/10/9.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Inject;
import javax.inject.Qualifier;

@Qualifier  //自定义标签
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonWithUser {
}
