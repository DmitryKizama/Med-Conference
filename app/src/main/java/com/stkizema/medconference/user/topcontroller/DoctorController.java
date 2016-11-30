package com.stkizema.medconference.user.topcontroller;

import android.view.View;

public class DoctorController {

    protected View parent;
    protected DoctorControllerListener doctorControllerListener;

    public interface DoctorControllerListener {
        void onBtnMenuClickListener();
    }

    public DoctorController(View parent, DoctorControllerListener adapterListener) {
        this.parent = parent;
        this.doctorControllerListener = adapterListener;
    }

}
