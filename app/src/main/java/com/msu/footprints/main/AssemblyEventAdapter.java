package com.msu.footprints.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.msu.footprints.R;
import com.msu.footprints.models.Event;

public class AssemblyEventAdapter extends FirestoreRecyclerAdapter<Event, AssemblyEventAdapter.ViewHolder>{

    Context context;
    String title;

    public AssemblyEventAdapter(Context context, @NonNull FirestoreRecyclerOptions<Event> options, String title){
        super(options);
        this.context = context;
        this.title = title;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_assembly_event, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Event model){
//        holder.title.setSelected(true);
//        holder.summary.setSelected(true);
        holder.tvTitle.setText(model.getTitle());
        if (!title.toLowerCase().equals("techzibitions"))
            holder.summary.setText(model.getSummary());
        holder.description.setText(model.getDescription());
        Glide.with(holder.context)
                .load(model.getImageURL())
                .into(holder.banner);

        holder.event_card.setOnClickListener(v -> {
            String path = this.getSnapshots().getSnapshot(position).getReference().getPath();
            Intent intent;
            intent = new Intent(context, EventDetailsActivity.class);
            intent.putExtra("Path", path);
            intent.putExtra("Title", model.getTitle());
            context.startActivity(intent);
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView banner;
        public TextView tvTitle;
        public TextView summary;
        public TextView description;
        public CardView event_card;
        //public TextView description;
        public Context context;

        public ViewHolder(View itemview){
            super(itemview);
            this.banner = itemView.findViewById(R.id.ivEvent);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.summary = itemView.findViewById(R.id.tvSummary);
            this.event_card = itemView.findViewById(R.id.event_card);
            this.description = itemView.findViewById(R.id.tvDescription);
            this.context = itemview.getContext();

            if (title.toLowerCase().equals("techzibitions"))
                summary.setVisibility(View.GONE);
        }
    }
}
