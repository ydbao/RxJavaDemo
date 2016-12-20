package cn.neoclub.rxjavademo.di.component;

import javax.inject.Singleton;

import cn.neoclub.rxjavademo.app.App;
import cn.neoclub.rxjavademo.di.ContextLife;
import cn.neoclub.rxjavademo.di.module.AppModule;
import cn.neoclub.rxjavademo.model.db.RealmHelper;
import cn.neoclub.rxjavademo.model.net.RetrofitHelper;
import dagger.Component;

/**
 * Created by renjialiang on 16/11/30.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    App getContext();

    RetrofitHelper getRetrofitHelper();

    RealmHelper getRealmHelper();
}
