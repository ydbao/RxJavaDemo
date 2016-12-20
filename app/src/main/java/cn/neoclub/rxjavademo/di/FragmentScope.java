package cn.neoclub.rxjavademo.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by renjialiang on 16/12/5.
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
