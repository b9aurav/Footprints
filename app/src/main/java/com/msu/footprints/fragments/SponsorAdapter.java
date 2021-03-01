package com.msu.footprints.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.msu.footprints.R;
import com.msu.footprints.models.Sponsor;

public class SponsorAdapter extends FirestoreRecyclerAdapter<Sponsor, SponsorAdapter.ViewHolder>{

    private Context context;

    public SponsorAdapter(Context context, @NonNull FirestoreRecyclerOptions<Sponsor> options){
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_sponsor, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Sponsor model){
        Glide.with(holder.context)
                .load(model.getImageURL())
                .into(holder.sponsor_banner);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView sponsor_banner;
        public Context context;

        public ViewHolder(View itemview){
            super(itemview);
            this.sponsor_banner = (ImageView) itemView.findViewById(R.id.ivSponsor);
            context = itemview.getContext();
        }
    }
}