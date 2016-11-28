package com.stkizema.medconference;

import android.app.Application;

import com.stkizema.medconference.db.DaoMaster;
import com.stkizema.medconference.db.DaoSession;
import com.stkizema.medconference.db.DbHelper;

import org.greenrobot.greendao.database.Database;

public class TopApp extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        DbHelper.getInstance(this);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
