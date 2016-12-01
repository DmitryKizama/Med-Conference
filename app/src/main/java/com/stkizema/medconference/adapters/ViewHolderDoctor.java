package com.stkizema.medconference.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.stkizema.medconference.R;

public class ViewHolderDoctor extends RecyclerView.ViewHolder {
    public TextView name, emailDoctor;
    public CheckBox checkBox;


    public ViewHolderDoctor(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.nameDoctor);
        emailDoctor = (TextView) itemView.findViewById(R.id.emailDoctor);
        checkBox = (CheckBox) itemView.findViewById(R.id.chbox_doctor);
    }
}
