package com.stkizema.medconference.bottom.controller;

import android.view.View;
import android.widget.Button;

abstract public class BaseBottomController {
    protected View parent;
    protected BottomControllerListener bottomControllerListener;

    public interface BottomControllerListener {
        void onBtnOkClickListener();

        void onRegisterLoginChange(boolean flag);
    }

    public BaseBottomController(View parent, BottomControllerListener adapterListener) {
        this.parent = parent;
        this.bottomControllerListener = adapterListener;
    }

}
