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
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.msu.footprints.R;
import com.msu.footprints.models.Event;

public class EventAdapter extends FirestoreRecyclerAdapter<Event, EventAdapter.ViewHolder>{

    private Context context;

    public EventAdapter(Context context, @NonNull FirestoreRecyclerOptions<Event> options){
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
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Event model){
        holder.title.setText(model.getTitle());
        holder.summary.setText(model.getSummary());
        holder.description.setText(model.getDescription());
        Glide.with(holder.context)
                .load(model.getImageURL())
                .into(holder.banner);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView banner;
        public TextView title;
        public TextView summary;
        public TextView description;
        public Context context;

        public ViewHolder(View itemview){
            super(itemview);
            this.banner = (ImageView) itemView.findViewById(R.id.ivEvent);
            this.title = (TextView) itemView.findViewById(R.id.tvTitle);
            this.summary = (TextView) itemView.findViewById(R.id.tvSummary);
            this.description = (TextView) itemView.findViewById(R.id.tvDescription);
            this.context = itemview.getContext();
        }
    }
}