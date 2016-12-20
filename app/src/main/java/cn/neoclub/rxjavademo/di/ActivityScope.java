package cn.neoclub.rxjavademo.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by renjialiang on 16/11/30.
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
