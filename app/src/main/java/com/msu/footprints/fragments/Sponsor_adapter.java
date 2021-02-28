package com.msu.footprints.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.msu.footprints.R;
import com.msu.footprints.models.Sponsor;

public class Sponsor_adapter extends RecyclerView.Adapter<Sponsor_adapter.ViewHolder> {

    private Sponsor[] sponsors_list;

    Sponsor_adapter(Sponsor[] list) {
        this.sponsors_list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_sponsor, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        final Sponsor mysponsors_list = sponsors_list[position];
        Glide.with(holder.context)
                .load(sponsors_list[position].getImageURL())
                .into(holder.sponsor_banner);
    }


    @Override
    public int getItemCount() {
        return sponsors_list.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView sponsor_banner;
        public Context context;

        public ViewHolder(View itemview) {
            super(itemview);
            this.sponsor_banner = (ImageView) itemView.findViewById(R.id.ivSponsor);
            context = itemview.getContext();
        }
    }
}