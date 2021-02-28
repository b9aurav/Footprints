package com.msu.footprints.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.msu.footprints.R;
import com.msu.footprints.models.About;

public class About_adapter extends RecyclerView.Adapter<About_adapter.ViewHolder> {

    private About[] about_list;
    About_adapter(About[] list) {
        this.about_list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_about, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        About myabout_list = about_list[position];
        holder.name.setText(about_list[position].getName());
        holder.email.setText(about_list[position].getEmail());
    }


    @Override
    public int getItemCount() {
        return about_list.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView email;

        public ViewHolder(View itemview) {
            super(itemview);
            this.name = (TextView) itemView.findViewById(R.id.tvName);
            this.email = (TextView) itemView.findViewById(R.id.tvEmail);
        }
    }
}