package com.stkizema.medconference.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.stkizema.medconference.R;
import com.stkizema.medconference.adapters.ConferencesRecyclerViewAdapter;
import com.stkizema.medconference.db.DbConferenceHelper;
import com.stkizema.medconference.db.DbUserHelper;
import com.stkizema.medconference.model.Conference;
import com.stkizema.medconference.model.User;
import com.stkizema.medconference.user.topcontroller.AdminController;
import com.stkizema.medconference.user.topcontroller.DoctorController;

public class ConferencesActivity extends AppCompatActivity implements DoctorController.DoctorControllerListener, AdminController.AdminControllerListener,
        ConferencesRecyclerViewAdapter.ConferencesRecyclerViewAdapterListener {

    public static final String EXTRAPERMISSION = "extra_permission";
    public static final String EXTRACONFID = "extra_conf_id";
    public static final String EXTRAUSERNAME = "extra_user_name";
    private FrameLayout topLayout;
    private RecyclerView recyclerView;
    private ConferencesRecyclerViewAdapter recyclerViewAdapter;
    private DoctorController dController;
    private AdminController adminController;
    private String permission, userName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_conferences);
        topLayout = (FrameLayout) findViewById(R.id.frame_list_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        Log.d("ConferenceActivityLog", "onCreate");

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        if (getIntent() != null) {
            permission = getIntent().getStringExtra(MainActivity.EXTRAPERMISSION);
            userName = getIntent().getStringExtra(MainActivity.EXTRALOGIN);
            Log.d("ConferenceActivityLog", "not null");
            adminDoctorController();
        }
    }

    private void adminDoctorController() {
        if (permission.equals(User.PERMISSIONDOCTOR)) {

            recyclerViewAdapter = new ConferencesRecyclerViewAdapter(DbConferenceHelper.getListConferencesByUserLogin(userName), this);
            View view = LayoutInflater.from(this).inflate(R.layout.layout_controller_doctor, topLayout, false);
            topLayout.removeAllViews();
            topLayout.addView(view);
            dController = new DoctorController(DbUserHelper.getUser(userName)
                    , topLayout, this);
        } else {
            recyclerViewAdapter = new ConferencesRecyclerViewAdapter(DbConferenceHelper.getListConferences(), this);
            View view = LayoutInflater.from(this).inflate(R.layout.layout_controller_admin, topLayout, false);
            topLayout.removeAllViews();
            topLayout.addView(view);
            adminController = new AdminController("admin", topLayout, this);
        }
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onBtnMenuClickListener() {

    }

    @Override
    public void onBtnAddConferenceClickListener(String admin) {
        Intent intent = new Intent(this, ConferenceCreateActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConferenceClick(Conference conference) {
        Intent intent = new Intent(this, InConferenceActivity.class);
        intent.putExtra(EXTRACONFID, conference.getConferenceId());
        intent.putExtra(EXTRAUSERNAME, userName);
        intent.putExtra(EXTRAPERMISSION, permission);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
