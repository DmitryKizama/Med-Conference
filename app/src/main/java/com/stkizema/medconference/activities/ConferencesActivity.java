package com.stkizema.medconference.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.stkizema.medconference.R;
import com.stkizema.medconference.adapters.RecyclerViewAdapter;
import com.stkizema.medconference.db.DbHelper;
import com.stkizema.medconference.db.User;
import com.stkizema.medconference.user.topcontroller.AdminController;
import com.stkizema.medconference.user.topcontroller.DoctorController;

public class ConferencesActivity extends AppCompatActivity implements DoctorController.DoctorControllerListener, AdminController.AdminControllerListener {

    private User user;
    private FrameLayout topLayout;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private DoctorController dController;
    private AdminController adminController;

//    @Override
//    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) { // SHIT!!!
//        super.onCreate(savedInstanceState, persistentState);
//    }

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
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);

        if (getIntent() != null) {
            Log.d("ConferenceActivityLog", "not null");
            adminDoctorController(getIntent().getStringExtra(MainActivity.EXTRAPERMISSION));
            user = DbHelper.getUser(getIntent().getStringExtra(MainActivity.EXTRALOGIN));
        }
    }

    private void adminDoctorController(String permission) {
        if (permission.equals(User.PERMISSIONDOCTOR)) {
            Log.d("ConferenceActivityLog", "doctor");
            View view = LayoutInflater.from(this).inflate(R.layout.layout_controller_doctor, topLayout, false);
            topLayout.removeAllViews();
            topLayout.addView(view);
            dController = new DoctorController(topLayout, this);
        } else {
            Log.d("ConferenceActivityLog", "admin");
            View view = LayoutInflater.from(this).inflate(R.layout.layout_controller_admin, topLayout, false);
            topLayout.removeAllViews();
            topLayout.addView(view);
            adminController = new AdminController(topLayout, this);
        }
    }

    @Override
    public void onBtnMenuClickListener() {

    }

    @Override
    public void onBtnAddConferenceClickListener() {

    }
}
