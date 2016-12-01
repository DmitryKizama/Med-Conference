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
    private DoctorRecyclerViewAdapterListener listener;

    public interface DoctorRecyclerViewAdapterListener {
        void onCheckBoxClickListener(User user);
    }

    public DoctorRecyclerViewAdapter(List<User> list, DoctorRecyclerViewAdapterListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ViewHolderDoctor onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor_adapter, parent, false);
        return new ViewHolderDoctor(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolderDoctor holder, int position) {
        holder.name.setText(list.get(holder.getAdapterPosition()).getLogin());
        holder.emailDoctor.setText(list.get(holder.getAdapterPosition()).getEmail());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCheckBoxClickListener(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
