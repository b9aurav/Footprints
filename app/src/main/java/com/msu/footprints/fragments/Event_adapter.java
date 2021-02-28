package com.msu.footprints.fragments;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.msu.footprints.R;
import com.msu.footprints.models.Event;

public class Event_adapter extends RecyclerView.Adapter<Event_adapter.ViewHolder> {

    private Event[] events_list;
    Event_adapter(Event[] list) {
        this.events_list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_event, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        final Event myevents_list = events_list[position];
        holder.title.setText(events_list[position].getTitle());
        holder.summary.setText(events_list[position].getSummary());
        holder.description.setText(events_list[position].getDescription());
        Glide.with(holder.context)
              .load(events_list[position].getImageURL())
              .into(holder.banner);
    }




    @Override
    public int getItemCount() {
        return events_list.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView banner;
        public TextView title;
        public TextView summary;
        public TextView description;
        public Context context;

        public ViewHolder(View itemview) {
            super(itemview);
            this.banner = (ImageView) itemView.findViewById(R.id.iv_Event);
            this.title = (TextView) itemView.findViewById(R.id.tvTitle);
            this.summary = (TextView) itemView.findViewById(R.id.tvSummary);
            this.description = (TextView) itemView.findViewById(R.id.tvDescription);
            this.context = itemview.getContext();
        }
    }
}