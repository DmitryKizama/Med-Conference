package com.stkizema.medconference.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stkizema.medconference.R;
import com.stkizema.medconference.db.DbUserHelper;
import com.stkizema.medconference.model.Topic;

import java.util.List;

public class TopicsRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolderTopic> {

    private List<Topic> list;

    public TopicsRecyclerViewAdapter(List<Topic> list) {
        this.list = list;
    }

    @Override
    public ViewHolderTopic onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic_adapter, parent, false);
        return new ViewHolderTopic(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderTopic holder, int position) {
        holder.tvName.setText(list.get(holder.getAdapterPosition()).getName());
        holder.tvCreator.setText("Creator: " + DbUserHelper.getUserById(list.get(position).getCreatorId()).getLogin()
                + " Email: " + DbUserHelper.getUserById(list.get(position).getCreatorId()).getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(Topic topic) {
        list.add(topic);
        notifyItemInserted(list.size() - 1);
    }

}
