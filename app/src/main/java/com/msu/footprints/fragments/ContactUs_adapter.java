package com.msu.footprints.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.msu.footprints.R;
import com.msu.footprints.models.Achievement;
import com.msu.footprints.models.Contact;

public class ContactUs_adapter extends RecyclerView.Adapter<ContactUs_adapter.ViewHolder> {

    private Contact[] contactUses_list;
    ContactUs_adapter(Contact[] list) {
        this.contactUses_list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        Contact mycontact_list = contactUses_list[position];
        holder.name.setText(contactUses_list[position].getName());
        holder.designation.setText(contactUses_list[position].getDesignation());
        holder.email.setText(contactUses_list[position].getEmail());
        holder.contactno.setText(contactUses_list[position].getContactNo());
    }


    @Override
    public int getItemCount() {
        return contactUses_list.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView designation;
        public TextView email;
        public TextView contactno;

        public ViewHolder(View itemview) {
            super(itemview);
            this.name = (TextView) itemView.findViewById(R.id.tvName);
            this.designation = (TextView) itemView.findViewById(R.id.tvDesignation);
            this.email = (TextView) itemView.findViewById(R.id.tvEmail);
            this.contactno = (TextView) itemView.findViewById(R.id.tvContactNo);
        }
    }
}