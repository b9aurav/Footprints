package com.msu.footprints.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.msu.footprints.R;
import com.msu.footprints.main.EventCategoryActivity;
import com.msu.footprints.main.EventDetailsActivity;
import com.msu.footprints.models.Event;

public class EventAdapter extends FirestoreRecyclerAdapter<Event, EventAdapter.ViewHolder>{

    private Context context;
    private int lastPosition = -1;

    public EventAdapter(Context context, FirestoreRecyclerOptions<Event> options){
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_event, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder){
        super.onViewDetachedFromWindow(holder);
        holder.event_card.clearAnimation();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder){
        super.onViewAttachedToWindow(holder);
        setScaleAnimation(holder.event_card);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Event model){
        holder.title.setSelected(true);
        holder.summary.setSelected(true);
        holder.title.setText(model.getTitle());
        holder.summary.setText(model.getSummary());
        Glide.with(holder.context)
                .load(model.getImageURL())
                .into(holder.banner);

        holder.event_card.setOnClickListener(v -> {
            String path = this.getSnapshots().getSnapshot(position).getReference().getPath();
            Intent intent;
            if (model.isCategory()) {
                intent = new Intent(context, EventCategoryActivity.class);
            } else {
                intent = new Intent(context, EventDetailsActivity.class);
            }
            intent.putExtra("Path", path);
            intent.putExtra("Title", model.getTitle());
            context.startActivity(intent);
        });

        holder.event_card.setOnLongClickListener(view -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle(model.getTitle());
            alertDialogBuilder.setMessage(model.getDescription());
            alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return true;
        });
    }

    private void setScaleAnimation(View view){
        ScaleAnimation anim = new ScaleAnimation(0.6f, 1.0f, 0.6f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(700);
        view.startAnimation(anim);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView banner;
        public TextView title;
        public TextView summary;
        public CardView event_card;
        //public TextView description;
        public Context context;

        public ViewHolder(View itemview){
            super(itemview);
            this.banner = (ImageView) itemView.findViewById(R.id.ivEvent);
            this.title = (TextView) itemView.findViewById(R.id.tvTitle);
            this.summary = (TextView) itemView.findViewById(R.id.tvSummary);
            this.event_card = (CardView) itemView.findViewById(R.id.event_card);
//            this.description = (TextView) itemView.findViewById(R.id.tvDescription);
            this.context = itemview.getContext();
        }
    }
}