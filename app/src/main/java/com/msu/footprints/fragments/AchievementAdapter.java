package com.msu.footprints.fragments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
    public void onViewDetachedFromWindow(@NonNull AchievementAdapter.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.achievement_card.clearAnimation();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull AchievementAdapter.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        setScaleAnimation(holder.achievement_card);
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

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.6f, 1.0f, 0.6f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(700);
        view.startAnimation(anim);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView achievement_banner;
        public TextView title;
        public TextView year;
        public TextView description;
        public CardView achievement_card;
        public Context context;

        public ViewHolder(View itemview){
            super(itemview);
            this.achievement_banner = (ImageView) itemView.findViewById(R.id.ivAchievement);
            this.title = (TextView) itemView.findViewById(R.id.tvTitle);
            this.year = (TextView) itemView.findViewById(R.id.tvYear);
            this.description = (TextView) itemView.findViewById(R.id.tvDescription);
            this.achievement_card = (CardView) itemView.findViewById(R.id.achievement_card);
            this.context = itemview.getContext();
        }
    }
}