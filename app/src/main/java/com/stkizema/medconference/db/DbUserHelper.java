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
    private static DaoSession daoSession;
    private Context con;

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
        daoSession = ((TopApp) context).getDaoSession();
    }

    public static User getUser(String login) {
        for (User user : getList()) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    public static User getUserById(Long id) {
        UserDao userDao = daoSession.getUserDao();
        Query<User> userQuery = userDao.queryBuilder().where(UserDao.Properties.Id.eq(id)).build();
        return userQuery.list().get(0);
    }

    public static List<User> getListDoctors() {
        UserDao userDao = daoSession.getUserDao();
        Query<User> userQuery = userDao.queryBuilder().where(UserDao.Properties.Permission.eq(User.PERMISSIONDOCTOR)).build();
        return userQuery.list();
    }

    public static User selectById(Long userId) {
        UserDao userDao = daoSession.getUserDao();
        Query<User> userQuery = userDao.queryBuilder().where(UserDao.Properties.Id.eq(userId)).build();
        if (userQuery.list() == null || userQuery.list().isEmpty()) {
            return null;
        }
        return userQuery.list().get(0);
    }

    public static List<User> getList() {
        UserDao userDao = daoSession.getUserDao();
        Query<User> userQuery = userDao.queryBuilder().orderAsc(UserDao.Properties.Id).build();
        return userQuery.list();
    }

    public static UserDao getUserDao() {

        return daoSession.getUserDao();
    }

    public static List<User> getListDoctorsDontInvited(Long id) {
        UserDao userDao = daoSession.getUserDao();
        Query<User> userQuery = userDao.queryBuilder().where(UserDao.Properties.Permission.eq(User.PERMISSIONDOCTOR)).build();
        List<User> listConf = DbConferenceHelper.getAllUsersByConferenceId(id);
        List<User> list = new ArrayList<>();
        for (User u : userQuery.list()) {
            if (!isContains(u, listConf)) {
                list.add(u);
            }
        }
        return list;
    }

    private static boolean isContains(User user, List<User> list) {
        for (User u : list) {
            if (u.getId().equals(user.getId())) {
                return true;
            }
        }
        return false;
    }

}
