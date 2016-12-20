package cn.neoclub.rxjavademo.app;

import android.app.Application;

import cn.neoclub.rxjavademo.di.component.AppComponent;
import cn.neoclub.rxjavademo.di.component.DaggerAppComponent;
import cn.neoclub.rxjavademo.di.module.AppModule;

/**
 * Created by renjialiang on 16/11/30.
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(instance)).build();
    }
}
