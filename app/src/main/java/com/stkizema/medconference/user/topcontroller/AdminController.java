package com.stkizema.medconference.user.topcontroller;

import android.view.View;

public class AdminController {
    protected View parent;
    protected AdminControllerListener adminControllerListener;

    public interface AdminControllerListener {
        void onBtnAddConferenceClickListener();
    }

    public AdminController(View parent, AdminControllerListener adapterListener) {
        this.parent = parent;
        this.adminControllerListener = adapterListener;
    }
}
