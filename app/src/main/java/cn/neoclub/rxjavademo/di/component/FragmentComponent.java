package cn.neoclub.rxjavademo.di.component;

import android.app.Activity;

import cn.neoclub.rxjavademo.di.FragmentScope;
import cn.neoclub.rxjavademo.di.module.AppModule;
import cn.neoclub.rxjavademo.di.module.FragmentModule;
import cn.neoclub.rxjavademo.ui.fragment.FirstFragment;
import cn.neoclub.rxjavademo.ui.fragment.SecondFragment;
import dagger.Component;

/**
 * Created by renjialiang on 16/12/5.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(FirstFragment firstFragment);

    void inject(SecondFragment secondFragment);
}
