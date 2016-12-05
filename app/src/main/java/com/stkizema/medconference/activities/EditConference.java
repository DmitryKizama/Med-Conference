package com.stkizema.medconference.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stkizema.medconference.R;
import com.stkizema.medconference.adapters.DoctorRecyclerViewAdapter;
import com.stkizema.medconference.db.DbConferenceHelper;
import com.stkizema.medconference.db.DbTopicHelper;
import com.stkizema.medconference.db.DbUserHelper;
import com.stkizema.medconference.model.Conference;
import com.stkizema.medconference.model.ConnectionConfUser;
import com.stkizema.medconference.model.Topic;
import com.stkizema.medconference.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditConference extends AppCompatActivity implements DoctorRecyclerViewAdapter.DoctorRecyclerViewAdapterListener {

    private RecyclerView rvDoctors, rvTopics;
    private EditText etName, etDate;
    private Button btnEdit, btnDelete;
    private DoctorRecyclerViewAdapter adapterDoc, topicAdapter;
    private List<User> listUser;
    private List<Topic> listTopic;
    private Long conferenceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        etDate = (EditText) findViewById(R.id.etDate);
        etName = (EditText) findViewById(R.id.etName);
        rvDoctors = (RecyclerView) findViewById(R.id.rvDoctors);
        rvTopics = (RecyclerView) findViewById(R.id.rvTopics);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnEdit = (Button) findViewById(R.id.btnEditOk);

        listUser = new ArrayList<>();
        listTopic = new ArrayList<>();

        conferenceId = 1L;
        if (getIntent() != null) {
            conferenceId = getIntent().getLongExtra(InConferenceActivity.EXTRACONFERID, 1L);
        }

        etDate.setText(DbConferenceHelper.getConferenceById(conferenceId).getDate().toString());
        etName.setText(DbConferenceHelper.getConferenceById(conferenceId).getName());

        adapterDoc = new DoctorRecyclerViewAdapter(DbUserHelper.getListDoctorsDontInvited(conferenceId), this);
        rvDoctors.setAdapter(adapterDoc);
        rvDoctors.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDoctors.setLayoutManager(mLayoutManager);

        topicAdapter = new DoctorRecyclerViewAdapter(DbTopicHelper.getAllTopicByConferenceId(conferenceId), this, false);
        rvTopics.setAdapter(topicAdapter);
        rvTopics.setHasFixedSize(true);
        LinearLayoutManager mLayoutManagerTopic = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvTopics.setLayoutManager(mLayoutManagerTopic);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editOk()) {
                    return;
                }
                startConferenceActivity();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
                startConferenceActivity();
            }
        });
    }

    private void startConferenceActivity() {
        Intent intent = new Intent(EditConference.this, ConferencesActivity.class);
        intent.putExtra(MainActivity.EXTRAPERMISSION, User.PERMISSIONADMIN);
        intent.putExtra(MainActivity.EXTRALOGIN, "admin");
        EditConference.this.finish();
        startActivity(intent);
    }

    private void delete() {
        Conference conf = DbConferenceHelper.getConferenceById(conferenceId);

        List<ConnectionConfUser> list = DbConferenceHelper.getAllConnectionByConferenceId(conferenceId);
        for (ConnectionConfUser con : list) {
            DbConferenceHelper.getConnDao().delete(con);
        }
        List<Topic> listTopic = DbTopicHelper.getAllTopicByConferenceId(conferenceId);
        for (Topic topic : listTopic) {
            DbTopicHelper.getTopicDao().delete(topic);
        }
        DbConferenceHelper.getConferenceDao().delete(conf);
    }

    private boolean editOk() {
        String name = etName.getText().toString();
        String date = etDate.getText().toString();
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // here set the pattern as you date in string was containing like date/month/year
            d = sdf.parse(date);
            Log.d("EditConference", "Date = " + d.toString());
        } catch (ParseException ex) {
            // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
            Log.d("EditConference", "Catch e = " + ex.toString());
            Toast.makeText(this, "Please enter time in format - `yyyy-MM-dd'T'HH:mm:ss'Z'`", Toast.LENGTH_LONG).show();
            return false;
        }
        if (name.equals("")) {
            Toast.makeText(this, "Please enter name field!", Toast.LENGTH_SHORT).show();
            return false;
        }
        Conference conf = DbConferenceHelper.getConferenceById(conferenceId);
        conf.setDate(d);
        conf.setName(name);
        DbConferenceHelper.getConferenceDao().update(conf);

        for (User user : listUser) {
            ConnectionConfUser conn = new ConnectionConfUser();
            conn.setUserId(user.getId());
            conn.setConfId(conferenceId);
            DbConferenceHelper.getConnDao().insert(conn);
        }

        List<Topic> list = DbTopicHelper.getAllTopicByConferenceId(conferenceId);
        int i = 0;
        for (Topic topic : listTopic) {
            Topic top = list.get(i);
            if (topic.getTopicId().equals(top.getTopicId())) {
                DbTopicHelper.getTopicDao().delete(topic);
            }
            ++i;
        }

        return true;
    }

    @Override
    public void onCheckBoxClickListener(User user) {
        for (User u : listUser) {
            if (user == u) {
                listUser.remove(user);
                Log.d("ConferenceCreateLog", "RETURN user = " + user.getLogin());
                return;
            }
        }
        listUser.add(user);
    }

    @Override
    public void onCheckBoxClickListener(Topic topic) {
        for (Topic u : listTopic) {
            if (topic == u) {
                listUser.remove(topic);
                Log.d("ConferenceCreateLog", "RETURN user = " + topic.getName());
                return;
            }
        }
        listTopic.add(topic);
    }
}
