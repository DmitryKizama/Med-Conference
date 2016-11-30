package com.stkizema.medconference.db;

import android.content.Context;

import com.stkizema.medconference.TopApp;
import com.stkizema.medconference.model.Conference;
import com.stkizema.medconference.model.ConferenceDao;
import com.stkizema.medconference.model.DaoSession;
import com.stkizema.medconference.model.Topic;
import com.stkizema.medconference.model.TopicDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class DbConferenceHelper {

    private static DbConferenceHelper instance;
    private Context context;
    private static ConferenceDao conferenceDao;
    private static TopicDao topicDao;
    private static Query<Conference> conferenceQuery;
    private static Query<Topic> topicQuery;

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

    private void initialize(Context context) {
        this.context = context;
        DaoSession daoSession = ((TopApp) context).getDaoSession();
        conferenceDao = daoSession.getConferenceDao();
        conferenceQuery = conferenceDao.queryBuilder().orderAsc(ConferenceDao.Properties.ConferenceId).build();

        topicDao = daoSession.getTopicDao();
        topicQuery = topicDao.queryBuilder().orderAsc(TopicDao.Properties.TopicId).build();
    }

    public static List<Conference> getListConferences() {
        return conferenceQuery.list();
    }

    public static ConferenceDao getConferenceDao() {
        return conferenceDao;
    }

    public static List<Topic> getListTopics() {
        return topicQuery.list();
    }

    public static TopicDao getTopicDao() {
        return topicDao;
    }
}
