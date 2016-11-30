package com.stkizema.medconference.user.topcontroller;

import android.view.View;

import com.stkizema.medconference.model.User;

public class DoctorController {

    protected View parent;
    private User user;
    protected DoctorControllerListener doctorControllerListener;

    public interface DoctorControllerListener {
        void onBtnMenuClickListener();
    }

    public DoctorController(User user, View parent, DoctorControllerListener adapterListener) {
        this.parent = parent;
        this.user = user;
        this.doctorControllerListener = adapterListener;
    }

}
