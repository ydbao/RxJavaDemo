package cn.neoclub.rxjavademo.di.module;

import javax.inject.Singleton;

import cn.neoclub.rxjavademo.app.App;
import cn.neoclub.rxjavademo.di.ContextLife;
import cn.neoclub.rxjavademo.model.db.RealmHelper;
import cn.neoclub.rxjavademo.model.net.RetrofitHelper;
import dagger.Module;
import dagger.Provides;

/**
 * Created by renjialiang on 16/11/30.
 */
@Module
public class AppModule {

    private final App application;

    public AppModule (App instance) {
        this.application = instance;
    }

    @Provides
    @Singleton
    @ContextLife("Application")
    App provideAppContext() {
        return application;
    }

    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper() {
        return new RetrofitHelper();
    }

    @Provides
    @Singleton
    RealmHelper provide() { return new RealmHelper(application);}
}
