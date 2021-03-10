package com.msu.footprints.main;

import android.content.Context;
import android.content.Intent;
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
import com.msu.footprints.fragments.EventAdapter;
import com.msu.footprints.fragments.RollingSquares;
import com.msu.footprints.models.Event;

public class RollingSquaresAdapter extends FirestoreRecyclerAdapter<Event, RollingSquaresAdapter.ViewHolder>{

    Context context;

    public RollingSquaresAdapter(Context context, @NonNull FirestoreRecyclerOptions<Event> options){
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_rolling_squares, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RollingSquaresAdapter.ViewHolder holder){
        super.onViewDetachedFromWindow(holder);
        holder.event_card.clearAnimation();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RollingSquaresAdapter.ViewHolder holder){
        super.onViewAttachedToWindow(holder);
        setScaleAnimation(holder.event_card);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Event model){

        Glide.with(holder.context)
                .load(model.getImageURL())
                .into(holder.banner);

    }

    private void setScaleAnimation(View view){
        ScaleAnimation anim = new ScaleAnimation(0.6f, 1.0f, 0.6f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(700);
        view.startAnimation(anim);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView banner;
        public CardView event_card;
        public Context context;

        public ViewHolder(View itemview){
            super(itemview);
            this.banner = itemView.findViewById(R.id.ivEvent);
            this.event_card = itemView.findViewById(R.id.event_card);
            this.context = itemview.getContext();

        }
    }
}
