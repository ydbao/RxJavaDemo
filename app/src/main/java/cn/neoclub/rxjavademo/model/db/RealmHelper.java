package cn.neoclub.rxjavademo.model.db;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by renjialiang on 16/12/2.
 */
public class RealmHelper {

    private static final String DB_NAME = "mRealm.db";

    private Realm mRealm;

    public RealmHelper(Context mContext) {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder(mContext)
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }
}
