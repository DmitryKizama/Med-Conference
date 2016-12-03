package com.stkizema.medconference.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stkizema.medconference.R;
import com.stkizema.medconference.db.DbConferenceHelper;
import com.stkizema.medconference.model.Conference;
import com.stkizema.medconference.model.User;

import java.util.List;

public class ConferencesRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolderConference> {

    private List<Conference> list;
    private ConferencesRecyclerViewAdapterListener listener;

    public interface ConferencesRecyclerViewAdapterListener {
        void onConferenceClick(Conference conference);
    }

    public ConferencesRecyclerViewAdapter(List<Conference> list, ConferencesRecyclerViewAdapterListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ViewHolderConference onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conference_adapter, parent, false);
        return new ViewHolderConference(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolderConference holder, int position) {
        holder.name.setText(list.get(holder.getAdapterPosition()).getName());
        holder.date.setText(list.get(holder.getAdapterPosition()).getDate().toString());

        String str = "Doctors :";
        int i = 1;
        for (User user : DbConferenceHelper.getAllUsersForConferenceId(list.get(holder.getAdapterPosition()).getConferenceId())) {
            str = str + i + ") " + user.getLogin() + "\n";
            ++i;
        }
        holder.tvDoctors.setText(str);

        holder.llConference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onConferenceClick(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
