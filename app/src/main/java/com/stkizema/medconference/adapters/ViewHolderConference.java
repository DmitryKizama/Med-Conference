package com.stkizema.medconference.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stkizema.medconference.R;

public class ViewHolderConference extends RecyclerView.ViewHolder {

    public TextView name, date, tvDoctors;
    public LinearLayout llConference;

    public ViewHolderConference(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.tvConferenceName);
        date = (TextView) itemView.findViewById(R.id.tvDate);
        tvDoctors = (TextView) itemView.findViewById(R.id.tvDoctors);
        llConference = (LinearLayout) itemView.findViewById(R.id.llConference);
    }
}
