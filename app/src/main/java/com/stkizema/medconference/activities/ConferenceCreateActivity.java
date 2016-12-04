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
import com.stkizema.medconference.db.DbUserHelper;
import com.stkizema.medconference.model.Conference;
import com.stkizema.medconference.model.ConnectionConfUser;
import com.stkizema.medconference.model.Topic;
import com.stkizema.medconference.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConferenceCreateActivity extends AppCompatActivity implements DoctorRecyclerViewAdapter.DoctorRecyclerViewAdapterListener {

    private RecyclerView rv;
    private DoctorRecyclerViewAdapter adapter;
    private Button btnCreateConfirance;
    private EditText etNameConference;
    private List<User> listUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_conference);

        rv = (RecyclerView) findViewById(R.id.rv_doctor);
        adapter = new DoctorRecyclerViewAdapter(DbUserHelper.getListDoctors(), this);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(mLayoutManager);

        btnCreateConfirance = (Button) findViewById(R.id.btn_create_conference);
        etNameConference = (EditText) findViewById(R.id.et_name_conference);

        listUser = new ArrayList<>();

        btnCreateConfirance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkOk()) {
                    return;
                }
                Conference conf = new Conference();
                conf.setName(etNameConference.getText().toString());
                conf.setDate(Calendar.getInstance().getTime());
                DbConferenceHelper.getConferenceDao().insert(conf);

                for (User user : listUser) {
                    ConnectionConfUser conn = new ConnectionConfUser();
                    conn.setUserId(DbUserHelper.getUser(user.getLogin()).getId());
                    conn.setConfId(DbConferenceHelper.getConference(conf.getName()).getConferenceId());
                    DbConferenceHelper.getConnDao().insert(conn);
                }

                Intent intent = new Intent(ConferenceCreateActivity.this, ConferencesActivity.class);
                intent.putExtra(MainActivity.EXTRAPERMISSION, User.PERMISSIONADMIN);
                ConferenceCreateActivity.this.finish();
                startActivity(intent);
            }
        });
        Log.d("ConferenceCreateLog", "OnCreate");
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

    }

    private boolean checkOk() {
        for (Conference c : DbConferenceHelper.getListConferences()) {
            if (c.getName().equals(etNameConference.getText().toString())) {
                Toast.makeText(this, "Such event already exist!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (listUser.isEmpty()) {
            Toast.makeText(this, "Invite at least one doctor!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etNameConference.getText().toString().equals("")) {
            Toast.makeText(this, "Name!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
