package com.stkizema.medconference;

import android.app.Application;

import com.stkizema.medconference.db.DbConferenceHelper;
import com.stkizema.medconference.db.DbTopicHelper;
import com.stkizema.medconference.db.DbUserHelper;
import com.stkizema.medconference.model.DaoMaster;
import com.stkizema.medconference.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class TopApp extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "model", null);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        DbUserHelper.setInstance(this);
        DbConferenceHelper.setInstance(this);
        DbTopicHelper.setInstance(this);
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

}
