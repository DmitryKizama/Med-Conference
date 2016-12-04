package com.stkizema.medconference.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stkizema.medconference.R;
import com.stkizema.medconference.db.DbUserHelper;
import com.stkizema.medconference.model.Topic;
import com.stkizema.medconference.model.User;

import java.util.List;

public class DoctorRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolderDoctor> {

    private List<User> listUser;
    private List<Topic> listTopic;
    private DoctorRecyclerViewAdapterListener listener;
    private boolean isDoctor;

    public interface DoctorRecyclerViewAdapterListener {
        void onCheckBoxClickListener(User user);

        void onCheckBoxClickListener(Topic topic);
    }

    public DoctorRecyclerViewAdapter(List<User> list, DoctorRecyclerViewAdapterListener listener) {
        this.listUser = list;
        this.listener = listener;
        isDoctor = true;
    }

    public DoctorRecyclerViewAdapter(List<Topic> listTopic, DoctorRecyclerViewAdapterListener listener, boolean flag) {
        this.listTopic = listTopic;
        this.listener = listener;
        isDoctor = false;
    }

    @Override
    public ViewHolderDoctor onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor_adapter, parent, false);
        return new ViewHolderDoctor(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolderDoctor holder, int position) {
        if (isDoctor) {
            holder.name.setText(listUser.get(holder.getAdapterPosition()).getLogin());
            holder.emailDoctor.setText(listUser.get(holder.getAdapterPosition()).getEmail());

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCheckBoxClickListener(listUser.get(holder.getAdapterPosition()));
                }
            });
        } else {
            holder.name.setText(listTopic.get(holder.getAdapterPosition()).getName());
            holder.emailDoctor.setText(DbUserHelper.getUserById(listTopic.get(holder.getAdapterPosition()).getCreatorId()).getLogin());

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCheckBoxClickListener(listTopic.get(holder.getAdapterPosition()));
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if (isDoctor) {
            return listUser.size();
        } else {
            return listTopic.size();
        }
    }
}
