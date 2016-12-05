package com.stkizema.medconference.db;

import android.content.Context;

import com.stkizema.medconference.TopApp;
import com.stkizema.medconference.model.Conference;
import com.stkizema.medconference.model.ConferenceDao;
import com.stkizema.medconference.model.ConnectionConfUser;
import com.stkizema.medconference.model.ConnectionConfUserDao;
import com.stkizema.medconference.model.DaoSession;
import com.stkizema.medconference.model.Topic;
import com.stkizema.medconference.model.TopicDao;
import com.stkizema.medconference.model.User;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DbConferenceHelper {

    private static DbConferenceHelper instance;
    private Context context;
    private static DaoSession daoSession;

    private DbConferenceHelper() {
    }

    private DbConferenceHelper(Context context) {
        initialize(context);
    }

    public static synchronized void setInstance(Context context) {
        if (instance == null) {
            instance = new DbConferenceHelper(context);
        }
    }

    public static List<Conference> getListConferencesByUserLogin(String login) {
        if (DbUserHelper.getUser(login) == null) {
            return null;
        }
        ConnectionConfUserDao connDao = daoSession.getConnectionConfUserDao();
        Query<ConnectionConfUser> queryConn = connDao.queryBuilder().where(ConnectionConfUserDao.Properties.UserId.eq(DbUserHelper.getUser(login).getId())).build();

        List<ConnectionConfUser> listJ = queryConn.list();
        List<Conference> list = new ArrayList<>();
        for (ConnectionConfUser j : listJ) {
            Conference conference = DbConferenceHelper.getConferenceById(j.getConfId());
            list.add(conference);
        }
        return list;
    }

    public static Conference getConferenceById(Long id) {
        ConferenceDao conferenceDao = daoSession.getConferenceDao();
        Query<Conference> conferenceQuery = conferenceDao.queryBuilder().where(ConferenceDao.Properties.ConferenceId.eq(id)).build();
        if (conferenceQuery.list() == null || conferenceQuery.list().isEmpty()) {
            return null;
        }
        return conferenceQuery.list().get(0);
    }

    public static List<User> getAllUsersByConferenceId(Long conferenceId) {
        ConnectionConfUserDao connDao = daoSession.getConnectionConfUserDao();
        Query<ConnectionConfUser> queryConn = connDao.queryBuilder().where(ConnectionConfUserDao.Properties.ConfId.eq(conferenceId)).build();
        List<ConnectionConfUser> listJ = queryConn.list();
        List<User> list = new ArrayList<>();
        for (ConnectionConfUser j : listJ) {
            User user = DbUserHelper.selectById(j.getUserId());
            list.add(user);
        }
        return list;
    }

    private void initialize(Context context) {
        this.context = context;
        daoSession = ((TopApp) context).getDaoSession();
    }

    public static List<Conference> getListConferences() {
        ConferenceDao conferenceDao = daoSession.getConferenceDao();
        Query<Conference> conferenceQuery = conferenceDao.queryBuilder().orderAsc(ConferenceDao.Properties.ConferenceId).build();
        return conferenceQuery.list();
    }

    public static ConferenceDao getConferenceDao() {
        return daoSession.getConferenceDao();
    }

    public static List<Topic> getListTopics() {
        TopicDao topicDao = daoSession.getTopicDao();
        Query<Topic> topicQuery = topicDao.queryBuilder().orderAsc(TopicDao.Properties.TopicId).build();
        return topicQuery.list();
    }

    public static TopicDao getTopicDao() {
        return daoSession.getTopicDao();
    }

    public static ConnectionConfUserDao getConnDao() {
        return daoSession.getConnectionConfUserDao();
    }

    public static List<ConnectionConfUser> getListConn() {
        ConnectionConfUserDao connDao = daoSession.getConnectionConfUserDao();
        Query<ConnectionConfUser> queryConn = connDao.queryBuilder().orderAsc(ConnectionConfUserDao.Properties.ConfId).build();
        return queryConn.list();
    }

    public static Conference getConference(String name) {
        for (Conference conf : getListConferences()) {
            if (conf.getName().equals(name)) {
                return conf;
            }
        }
        return null;
    }

    public static List<ConnectionConfUser> getAllConnectionByConferenceId(Long id){
        ConnectionConfUserDao connDao = daoSession.getConnectionConfUserDao();
        Query<ConnectionConfUser> queryConn = connDao.queryBuilder().where(ConnectionConfUserDao.Properties.ConfId.eq(id)).build();
        return queryConn.list();
    }


}
