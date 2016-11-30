package com.stkizema.medconference.main.controller;

import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stkizema.medconference.R;
import com.stkizema.medconference.db.DbUserHelper;
import com.stkizema.medconference.model.User;

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
                if (!registerUser()) {
                    return;
                }
                bottomControllerListener.onBtnOkClickListener(User.PERMISSIONDOCTOR, etLogIn.getText().toString());
            }
        });

        etLogIn = (EditText) parent.findViewById(R.id.et_login);
        etPassword = (EditText) parent.findViewById(R.id.et_password);
        etEmail = (EditText) parent.findViewById(R.id.et_email);
    }

    private boolean registerUser() {
        String login = etLogIn.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (checkForExisting(login, email)) {
            return false;
        }
        if (password.length() < 8) {
            Toast.makeText(parent.getContext(), "Your password less than 8 characters!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isValidEmailAddress(email)) {
            Toast.makeText(parent.getContext(), "Incorrect email!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (adminRegistration(login, password)) {
            return false;
        }

        User user = new User();
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        user.setPermission(User.PERMISSIONDOCTOR);
        DbUserHelper.getUserDao().insert(user);
        return true;
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private boolean checkForExisting(String login, String email) {
        if (login.equals("") || email.equals("")) {
            Toast.makeText(parent.getContext(), "Blank!", Toast.LENGTH_SHORT).show();
            return true;
        }
        for (User user : DbUserHelper.getList()) {
            if (user.getLogin().equals(login)) {
                Toast.makeText(parent.getContext(), "Such login already exist!", Toast.LENGTH_SHORT).show();
                return true;
            }
            if (user.getEmail().equals(email)) {
                Toast.makeText(parent.getContext(), "Such email already exist!", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }

    private boolean adminRegistration(String login, String password) {
        if (login.equals("admin") && password.equals("admin")) {
            Toast.makeText(parent.getContext(), "Doesn`t allowed admin registration!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
