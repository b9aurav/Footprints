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
import com.msu.footprints.models.Event;

import razerdp.basepopup.QuickPopupBuilder;
import razerdp.basepopup.QuickPopupConfig;
import razerdp.util.animation.AnimationHelper;
import razerdp.util.animation.ScaleConfig;
import razerdp.widget.QuickPopup;

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
    public void onViewDetachedFromWindow(@NonNull AssemblyEventAdapter.ViewHolder holder){
        holder.assembly_card.clearAnimation();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull AssemblyEventAdapter.ViewHolder holder){
        super.onViewAttachedToWindow(holder);
        setScaleAnimation(holder.assembly_card);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Event model){

        if (title.toLowerCase().equals("guest lectures") && model.isCurrent()){
            holder.description.setVisibility(View.GONE);
        }

//        holder.title.setSelected(true);
//        holder.summary.setSelected(true);
        holder.tvTitle.setText(model.getTitle());
        if (!title.toLowerCase().equals("techzibitions"))
            holder.summary.setText(model.getSummary());
        holder.description.setText(model.getDescription());
        Glide.with(holder.context)
                .load(model.getImageURL())
                .into(holder.banner);

        holder.assembly_card.setOnClickListener(v -> {
            if (title.toLowerCase().equals("workshops") && model.isCurrent()) {
                String path = this.getSnapshots().getSnapshot(position).getReference().getPath();
                Intent intent;
                intent = new Intent(context, EventDetailsActivity.class);
                intent.putExtra("Type", 1);
                intent.putExtra("Path", path);
                intent.putExtra("Title", model.getTitle());
                intent.putExtra("Fees", model.getFees());
                intent.putExtra("RegisterLink", model.getRegisterLink());
                intent.putExtra("TeamSize", model.getTeamSize());
                intent.putExtra("dateTime", model.getDateTime());
                intent.putExtra("highlights", model.getHighlights());
                intent.putExtra("docURL", model.getDocURL());
                intent.putExtra("prerequistes", model.getPrerequistes());
                intent.putExtra("schedule", model.getSchedule());
                intent.putExtra("problem", model.getProblem());
                context.startActivity(intent);
            }
            else if (title.toLowerCase().equals("guest lectures") && model.isCurrent()) {
                String path = this.getSnapshots().getSnapshot(position).getReference().getPath();
                Intent intent;
                intent = new Intent(context, EventDetailsActivity.class);
                intent.putExtra("Type", 2);
                intent.putExtra("Guest", model.getTitle());
                intent.putExtra("Description", model.getDescription());
                intent.putExtra("Title", title);
                intent.putExtra("RegisterLink", model.getRegisterLink());
                intent.putExtra("dateTime", model.getDateTime());
                context.startActivity(intent);
            }
        });
    }

    private void setScaleAnimation(View view){
        ScaleAnimation anim = new ScaleAnimation(0.6f, 1.0f, 0.6f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(700);
        view.startAnimation(anim);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView banner;
        public TextView tvTitle;
        public TextView summary;
        public TextView description;
        public CardView assembly_card;
        //public TextView description;
        public Context context;

        public ViewHolder(View itemview){
            super(itemview);
            this.banner = itemView.findViewById(R.id.ivEvent);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.summary = itemView.findViewById(R.id.tvSummary);
            this.assembly_card = itemView.findViewById(R.id.assembly_card);
            this.description = itemView.findViewById(R.id.tvDescription);
            this.context = itemview.getContext();

            if (title.toLowerCase().equals("techzibitions"))
                summary.setVisibility(View.GONE);
        }
    }
}
