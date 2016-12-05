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

public class DoctorRecyclerViewAdapter <T> extends RecyclerView.Adapter<ViewHolderDoctor> {

    private List<T> list;
    private DoctorRecyclerViewAdapterListener listener;

    public interface DoctorRecyclerViewAdapterListener {
        void onCheckBoxClickListener(User user);
        void onCheckBoxClickListener(Topic topic);
    }

    public DoctorRecyclerViewAdapter(List<T> list, DoctorRecyclerViewAdapterListener listener) {
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

        T t = list.get(holder.getAdapterPosition());

        if (t instanceof User) {
            User user = (User) t;
            holder.name.setText(user.getLogin());
            holder.emailDoctor.setText(user.getEmail());

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    T t = list.get(holder.getAdapterPosition());
                    User user = (User) t;
                    listener.onCheckBoxClickListener(user);
                }
            });
        } else {
            Topic topic = (Topic) t;

            holder.name.setText(topic.getName());
            holder.emailDoctor.setText(DbUserHelper.getUserById(topic.getCreatorId()).getLogin());

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    T t = list.get(holder.getAdapterPosition());
                    Topic topic = (Topic) t;
                    listener.onCheckBoxClickListener(topic);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (list == null){
            return 0;
        }

        return list.size();
    }
}
