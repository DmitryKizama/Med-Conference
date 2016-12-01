package com.stkizema.medconference.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stkizema.medconference.R;
import com.stkizema.medconference.model.User;

import java.util.List;

public class DoctorRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolderDoctor> {

    private List<User> list;

    public DoctorRecyclerViewAdapter(List<User> list) {
        this.list = list;
    }

    @Override
    public ViewHolderDoctor onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor_adapter, parent, false);
        return new ViewHolderDoctor(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderDoctor holder, int position) {
        holder.name.setText(list.get(holder.getAdapterPosition()).getLogin());
        holder.emailDoctor.setText(list.get(holder.getAdapterPosition()).getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
