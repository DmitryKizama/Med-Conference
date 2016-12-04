package com.stkizema.medconference.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.stkizema.medconference.R;

public class ViewHolderTopic extends RecyclerView.ViewHolder {

    public TextView tvCreator, tvName;

    public ViewHolderTopic(View itemView) {
        super(itemView);
        tvCreator = (TextView) itemView.findViewById(R.id.tv_creator);
        tvName = (TextView) itemView.findViewById(R.id.tv_name_topic);
    }
}
