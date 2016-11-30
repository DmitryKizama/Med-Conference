package com.stkizema.medconference.user.topcontroller;

import android.util.Log;
import android.view.View;

import com.stkizema.medconference.db.DbConferenceHelper;
import com.stkizema.medconference.db.DbUserHelper;
import com.stkizema.medconference.model.Conference;
import com.stkizema.medconference.model.Topic;
import com.stkizema.medconference.model.User;

import java.util.Date;

public class AdminController {
    protected View parent;
    protected AdminControllerListener adminControllerListener;

    public interface AdminControllerListener {
        void onBtnAddConferenceClickListener();
    }

    public AdminController(View parent, AdminControllerListener adapterListener) {
        this.parent = parent;
        this.adminControllerListener = adapterListener;
        Conference c = new Conference();
        c.setDate(new Date(1432, 11, 30));
        User user = new User();
        user.setEmail("blya");
        user.setLogin("urod");
        user.setPassword("gandon");
        user.setPermission(User.PERMISSIONDOCTOR);
        DbUserHelper.getUserDao().insert(user);

        Topic topic = new Topic();
        topic.setCreatorId(DbUserHelper.getUser("urod").getId());
        topic.setDescription("DSGJNDSKJNDNGJK: DN SND LGDS");
        topic.setName("FUCK");
        DbConferenceHelper.getTopicDao().insert(topic);

        DbConferenceHelper.getConferenceDao().insert(c);

//        c.getListInvitedDoctors().add(user);
//        c.getListTopics().add(topic);

        for (Conference conf : DbConferenceHelper.getListConferences()) {
            if (conf.getConferenceId() == 1L) {
                conf.getListInvitedDoctors().add(user);
                conf.getListTopics().add(topic);
            }
        }

//        c.getListTopics().add();
        for (Conference confer : DbConferenceHelper.getListConferences()) {
            Log.d("AdminCntrollerLog", "Doctors = " + confer.getDate());
            Log.d("AdminCntrollerLog", "Doctors = " + confer.getConferenceId());
            Log.d("AdminCntrollerLog", "Doctors = " + confer.getListTopics().get(0).getCreatorId());
            Log.d("AdminCntrollerLog", "Doctors = " + confer.getListInvitedDoctors().get(0).getLogin());
        }
    }
}
