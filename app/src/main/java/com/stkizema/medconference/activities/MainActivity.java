package com.stkizema.medconference.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.stkizema.medconference.R;
import com.stkizema.medconference.main.controller.BaseBottomController;
import com.stkizema.medconference.main.controller.LogInController;
import com.stkizema.medconference.main.controller.RegisterController;

public class MainActivity extends AppCompatActivity implements BaseBottomController.BottomControllerListener {

    public static final String EXTRALOGIN = "LOGIN";
    public static final String EXTRAPERMISSION = "PERMISSION";
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
    public void onBtnOkClickListener(String permission, String login) {
        Intent intent = new Intent(MainActivity.this, ConferencesActivity.class);
        intent.putExtra(EXTRALOGIN, login);
        intent.putExtra(EXTRAPERMISSION, permission);
        startActivity(intent);
    }

    @Override
    public void onRegisterLoginChange(boolean registerLogin) {
        if (registerLogin) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_main_bottom_register, bottomLayout, false);
            bottomLayout.removeAllViews();
            bottomLayout.addView(view);
            bottomController = new RegisterController(bottomLayout, this);
        } else {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_main_bottom_login, bottomLayout, false);
            bottomLayout.removeAllViews();
            bottomLayout.addView(view);
            bottomController = new LogInController(bottomLayout, this);
        }
    }
}
