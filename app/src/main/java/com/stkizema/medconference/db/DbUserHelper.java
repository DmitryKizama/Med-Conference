package com.stkizema.medconference.db;

import android.content.Context;

import com.stkizema.medconference.TopApp;
import com.stkizema.medconference.model.DaoSession;
import com.stkizema.medconference.model.User;
import com.stkizema.medconference.model.UserDao;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DbUserHelper {

    private static DbUserHelper instance;
    private Context con;
    private static UserDao userDao;
    private static Query<User> userQuery;

    private DbUserHelper() {
    }

    private DbUserHelper(Context context) {
        initialize(context);
    }

    public static synchronized void setInstance(Context context) {
        if (instance == null) {
            instance = new DbUserHelper(context);
        }
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

    public static List<User> getListDoctors() {
        List<User> list = new ArrayList<>();
        for (User user : userQuery.list()) {
            if (user.getPermission().equals(User.PERMISSIONDOCTOR)) {
                list.add(user);
            }
        }
        return list;
    }

    public static List<User> getList() {
        return userQuery.list();
    }

    public static UserDao getUserDao() {
        return userDao;
    }

}
