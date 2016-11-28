package com.stkizema.medconference.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.stkizema.medconference.R;
import com.stkizema.medconference.bottom.controller.BaseBottomController;
import com.stkizema.medconference.bottom.controller.LogInController;
import com.stkizema.medconference.bottom.controller.RegisterController;

public class MainActivity extends AppCompatActivity implements BaseBottomController.BottomControllerListener {

    private FrameLayout bottomLayout;
    private BaseBottomController bottomController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomLayout = (FrameLayout) findViewById(R.id.frame_main_layout);
        onRegisterLoginChange(false);
    }

    @Override
    public void onBtnOkClickListener() {
        Toast.makeText(this, "Logined", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterLoginChange(boolean registerLogin) {
        if (registerLogin) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_bottom_register, bottomLayout, false);
            bottomLayout.removeAllViews();
            bottomLayout.addView(view);
            bottomController = new RegisterController(bottomLayout, this);
        } else {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_bottom_login, bottomLayout, false);
            bottomLayout.removeAllViews();
            bottomLayout.addView(view);
            bottomController = new LogInController(bottomLayout, this);
        }
    }
}
