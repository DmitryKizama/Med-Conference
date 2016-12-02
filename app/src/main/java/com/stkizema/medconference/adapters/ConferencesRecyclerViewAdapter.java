package com.stkizema.medconference.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stkizema.medconference.R;
import com.stkizema.medconference.model.Conference;

import java.util.List;

public class ConferencesRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolderConference> {

    private List<Conference> list;

    public ConferencesRecyclerViewAdapter(List<Conference> list) {
        this.list = list;
    }

    @Override
    public ViewHolderConference onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conference_adapter, parent, false);
        return new ViewHolderConference(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderConference holder, int position) {
        holder.name.setText(list.get(holder.getAdapterPosition()).getName());
        holder.date.setText(list.get(holder.getAdapterPosition()).getDate().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
