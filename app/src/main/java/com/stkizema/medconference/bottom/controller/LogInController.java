package com.stkizema.medconference.bottom.controller;

import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.stkizema.medconference.R;
import com.stkizema.medconference.db.DbHelper;
import com.stkizema.medconference.db.User;

public class LogInController extends BaseBottomController {

    private TextView tvRegister;
    private Button btnOk;
    private EditText etLogIn, etPassword;

    public LogInController(View parent, BottomControllerListener adapterListener) {
        super(parent, adapterListener);
        btnOk = (Button) parent.findViewById(R.id.btn_login);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkOk()) {
                    return;
                }
                bottomControllerListener.onBtnOkClickListener();
            }
        });

        tvRegister = (TextView) parent.findViewById(R.id.tv_register);
        tvRegister.setPaintFlags(tvRegister.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomControllerListener.onRegisterLoginChange(true);
            }
        });

        etLogIn = (EditText) parent.findViewById(R.id.et_login);
        etPassword = (EditText) parent.findViewById(R.id.et_password);
    }

    private boolean checkOk() {
        boolean check = false;
        String login = etLogIn.getText().toString();
        String pass = etPassword.getText().toString();

        for (User user : DbHelper.getList()) {
            if (user.getLogin().equals(login)) {
                check = true;
                return check;
            }
        }
        return check;
    }

}
