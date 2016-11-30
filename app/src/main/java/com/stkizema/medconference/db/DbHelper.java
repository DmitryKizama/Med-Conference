package com.stkizema.medconference.db;

import android.content.Context;

import com.stkizema.medconference.TopApp;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class DbHelper {

    private static DbHelper instance;
    private Context con;
    private static UserDao userDao;
    private static Query<User> userQuery;

    private DbHelper(Context context) {
        initialize(context);
    }

    public static synchronized DbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbHelper(context);
        }
        return instance;
    }

    public void initialize(Context context) {
        this.con = context;
        DaoSession daoSession = ((TopApp) context).getDaoSession();
        userDao = daoSession.getUserDao();

        userQuery = userDao.queryBuilder().orderAsc(UserDao.Properties.Id).build();
    }

    public static User getUser(String login) {
        for (User user : getList()) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    public static List<User> getList() {
        return userQuery.list();
    }

    public static UserDao getUserDao() {
        return userDao;
    }

}
