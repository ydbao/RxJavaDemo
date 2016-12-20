package cn.neoclub.rxjavademo.di.component;

import android.app.Activity;

import cn.neoclub.rxjavademo.di.ActivityScope;
import cn.neoclub.rxjavademo.di.module.ActivityModule;
import cn.neoclub.rxjavademo.ui.MainActivity;
import dagger.Component;

/**
 * Created by renjialiang on 16/11/30.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);
}
