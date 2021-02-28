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

public class Achievement_adapter extends RecyclerView.Adapter<Achievement_adapter.ViewHolder> {

    private Achievement[] achievements_list;
    Achievement_adapter(Achievement[] list) {
        this.achievements_list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_achievement, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        Achievement myachievement_list = achievements_list[position];
        holder.title.setText(achievements_list[position].getTitle());
        holder.year.setText(achievements_list[position].getYear());
        holder.description.setText(achievements_list[position].getDescription());
        Glide.with(holder.context)
                .load(achievements_list[position].getImageURL())
                .into(holder.achievement_banner);
    }


    @Override
    public int getItemCount() {
        return achievements_list.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView achievement_banner;
        public TextView title;
        public TextView year;
        public TextView description;
        public Context context;

        public ViewHolder(View itemview) {
            super(itemview);
            this.achievement_banner = (ImageView) itemView.findViewById(R.id.ivAchievement);
            this.title = (TextView) itemView.findViewById(R.id.tvTitle);
            this.year = (TextView) itemView.findViewById(R.id.tvYear);
            this.description = (TextView) itemView.findViewById(R.id.tvDescription);
            this.context = itemview.getContext();
        }
    }
}