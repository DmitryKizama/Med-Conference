package com.stkizema.medconference.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.stkizema.medconference.R;

public class ViewHolderConference extends RecyclerView.ViewHolder {

    public TextView name, date;

    public ViewHolderConference(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.tvConferenceName);
        date = (TextView) itemView.findViewById(R.id.tvDate);
    }
}
