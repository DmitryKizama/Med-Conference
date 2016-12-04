package com.stkizema.medconference.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.stkizema.medconference.R;
import com.stkizema.medconference.activities.dialogs.CreateTopicDialog;
import com.stkizema.medconference.adapters.TopicsRecyclerViewAdapter;
import com.stkizema.medconference.db.DbConferenceHelper;
import com.stkizema.medconference.db.DbTopicHelper;
import com.stkizema.medconference.db.DbUserHelper;
import com.stkizema.medconference.model.Topic;
import com.stkizema.medconference.model.User;

public class InConferenceActivity extends AppCompatActivity implements CreateTopicDialog.CreateTopicDialogListener {

    private RecyclerView rvTopics;
    private TopicsRecyclerViewAdapter adapter;
    private TextView tvName, tvData, tvDoctors;
    private Button btnInConfAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_conference_activity);

        tvData = (TextView) findViewById(R.id.tvDate);
        tvName = (TextView) findViewById(R.id.conferenceName);
        tvDoctors = (TextView) findViewById(R.id.tvDocs);
        btnInConfAct = (Button) findViewById(R.id.btnInConferenceActivity);

        Long id = 0L;
        if (getIntent() != null) {
            String userName = getIntent().getStringExtra(ConferencesActivity.EXTRAUSERNAME);
            id = getIntent().getLongExtra(ConferencesActivity.EXTRACONFID, 1L);
            String permission = getIntent().getStringExtra(ConferencesActivity.EXTRAPERMISSION);
            initialize(userName, id, permission);
        }

        rvTopics = (RecyclerView) findViewById(R.id.recycler_view_topics);
        adapter = new TopicsRecyclerViewAdapter(DbTopicHelper.getAllTopicByConferenceId(id));
        rvTopics.setAdapter(adapter);
        rvTopics.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvTopics.setLayoutManager(mLayoutManager);
    }

    private void initialize(final String userName, final Long id, String permission) {
        tvData.setText(DbConferenceHelper.getConferenceById(id).getDate().toString());
        tvName.setText("Conference - " + DbConferenceHelper.getConferenceById(id).getName().toString());
        String str = "";
        int i = 1;
        for (User u : DbConferenceHelper.getAllUsersForConferenceId(id)) {
            str += i + ") " + u.getLogin() + "; ";
            ++i;
        }
        tvDoctors.setText("Doctors: " + str);

        if (permission.equals(User.PERMISSIONDOCTOR)) {
            btnInConfAct.setText("Create new topic");
            btnInConfAct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CreateTopicDialog dialog = new CreateTopicDialog(InConferenceActivity.this, DbUserHelper.getUser(userName), id, InConferenceActivity.this);
                    dialog.show();
                }
            });
        } else {
            btnInConfAct.setText("Change conference");
            btnInConfAct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }


    @Override
    public void onAddClick(Topic topic) {
        adapter.add(topic);
    }
}
