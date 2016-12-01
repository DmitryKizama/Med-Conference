package com.stkizema.medconference.user.topcontroller;

import android.view.View;
import android.widget.Button;

import com.stkizema.medconference.R;

public class AdminController {
    protected View parent;
    protected AdminControllerListener adminControllerListener;
    private Button btnAdd;

    public interface AdminControllerListener {
        void onBtnAddConferenceClickListener(String admin);
    }

    public AdminController(final String admin, View parent, AdminControllerListener adapterListener) {
        this.parent = parent;
        this.adminControllerListener = adapterListener;
        btnAdd = (Button) parent.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminControllerListener.onBtnAddConferenceClickListener(admin);
            }
        });
    }
}
