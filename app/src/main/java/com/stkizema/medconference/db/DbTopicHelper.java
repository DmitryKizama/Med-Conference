package com.stkizema.medconference.db;

import android.content.Context;

import com.stkizema.medconference.TopApp;
import com.stkizema.medconference.model.DaoSession;
import com.stkizema.medconference.model.Topic;
import com.stkizema.medconference.model.TopicDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;

public class DbTopicHelper {

    private static DaoSession daoSession;
    private static DbTopicHelper instance;
    private Context con;

    private DbTopicHelper() {
    }

    private DbTopicHelper(Context context) {
        initialize(context);
    }

    public static synchronized void setInstance(Context context) {
        if (instance == null) {
            instance = new DbTopicHelper(context);
        }
    }

    public void initialize(Context context) {
        this.con = context;
        daoSession = ((TopApp) context).getDaoSession();
    }

    public static TopicDao getTopicDao() {
        return daoSession.getTopicDao();
    }

    public static List<Topic> getAllTopic() {
        TopicDao topicDao = daoSession.getTopicDao();
        Query<Topic> query = topicDao.queryBuilder().orderAsc(TopicDao.Properties.TopicId).build();
        return query.list();
    }

    public static List<Topic> getAllTopicByConferenceId(Long id) {
        TopicDao topicDao = daoSession.getTopicDao();
        Query<Topic> query = topicDao.queryBuilder().where(TopicDao.Properties.ConferenceId.eq(id)).build();
        return query.list();
    }


}
