package cn.neoclub.rxjavademo.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import cn.neoclub.rxjavademo.di.FragmentScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by renjialiang on 16/12/5.
 */
@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
