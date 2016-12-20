package cn.neoclub.rxjavademo.di.module;

import android.app.Activity;

import cn.neoclub.rxjavademo.di.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by renjialiang on 16/11/30.
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return activity;
    }
}
