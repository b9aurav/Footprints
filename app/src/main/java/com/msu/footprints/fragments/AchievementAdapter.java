package com.msu.footprints.fragments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.msu.footprints.R;
import com.msu.footprints.models.Achievement;

public class AchievementAdapter extends FirestoreRecyclerAdapter<Achievement, AchievementAdapter.ViewHolder>{
    private Context context;

    public AchievementAdapter(Context context, @NonNull FirestoreRecyclerOptions<Achievement> options){
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_achievement, parent, false);
        return new ViewHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Achievement model){
        holder.title.setText(model.getTitle());
        holder.year.setText(model.getYear());
        holder.description.setText(model.getDescription());
        Glide.with(holder.context)
                .load(model.getImageURL())
                .into(holder.achievement_banner);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView achievement_banner;
        public TextView title;
        public TextView year;
        public TextView description;
        public Context context;

        public ViewHolder(View itemview){
            super(itemview);
            this.achievement_banner = (ImageView) itemView.findViewById(R.id.ivAchievement);
            this.title = (TextView) itemView.findViewById(R.id.tvTitle);
            this.year = (TextView) itemView.findViewById(R.id.tvYear);
            this.description = (TextView) itemView.findViewById(R.id.tvDescription);
            this.context = itemview.getContext();
        }
    }
}