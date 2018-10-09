package com.moudle.dragger2s.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * Created by Administrator on 2018/10/9.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonWithOther {
}
