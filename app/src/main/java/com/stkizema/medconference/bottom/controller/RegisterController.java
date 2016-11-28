package com.stkizema.medconference.bottom.controller;

import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.stkizema.medconference.R;

public class RegisterController extends BaseBottomController {

    private TextView tvLogin;
    private Button btnOk;
    private EditText etLogIn, etPassword, etEmail;

    public RegisterController(View parent, BottomControllerListener adapterListener) {
        super(parent, adapterListener);

        tvLogin = (TextView) parent.findViewById(R.id.tv_login);
        tvLogin.setPaintFlags(tvLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomControllerListener.onRegisterLoginChange(false);
            }
        });

        btnOk = (Button) parent.findViewById(R.id.btn_register);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomControllerListener.onBtnOkClickListener();
            }
        });

        etLogIn = (EditText) parent.findViewById(R.id.et_login);
        etPassword = (EditText) parent.findViewById(R.id.et_password);
        etEmail = (EditText) parent.findViewById(R.id.et_email);
    }
}
