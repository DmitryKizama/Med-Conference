package com.stkizema.medconference.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.stkizema.medconference.R;
import com.stkizema.medconference.adapters.DoctorRecyclerViewAdapter;
import com.stkizema.medconference.db.DbUserHelper;

public class ConferenceCreateActivity extends AppCompatActivity {

    private RecyclerView rv;
    private DoctorRecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_conference);

        rv = (RecyclerView) findViewById(R.id.rv_doctor);
        adapter = new DoctorRecyclerViewAdapter(DbUserHelper.getListDoctors());
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(mLayoutManager);
    }
}
