package com.msu.footprints.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.msu.footprints.R;
import com.msu.footprints.models.Event;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class Social_ResponsibilityAdapter extends FirestoreRecyclerAdapter<Event, Social_ResponsibilityAdapter.ViewHolder>{

    Context context;
    FirebaseFirestore firebaseFirestore;
    List<String> list;
    String title;

    public Social_ResponsibilityAdapter(Context context, @NonNull FirestoreRecyclerOptions<Event> options, String title){
        super(options);
        this.context = context;
        this.title = title;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_social_responsibility, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull Social_ResponsibilityAdapter.ViewHolder holder){
        super.onViewDetachedFromWindow(holder);
        holder.event_card.clearAnimation();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull Social_ResponsibilityAdapter.ViewHolder holder){
        super.onViewAttachedToWindow(holder);
        setScaleAnimation(holder.event_card);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Event model){
        if(title.contains("Schitron")) {
            holder.Title.setText(model.getTitle());
            holder.Description.setText(model.getDescription());
            firebaseFirestore = FirebaseFirestore.getInstance();
            String path = this.getSnapshots().getSnapshot(position).getReference().getPath();
            list = new ArrayList<>();
            Slider.init(new SliderService(context));
            firebaseFirestore.collection(path + "/" + "Images").get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    for(DocumentSnapshot document : task.getResult().getDocuments()) {
                        String url =  document.getString("ImageURL");
                        list.add(url);
                    }
                    holder.slider.setAdapter(new SliderAdapter(list, list.size()));
                }
            });
        }
        else if(title.contains("Social Responsibility")) {
            holder.Title.setText(model.getTitle());
            holder.Description.setText(model.getDescription());
            firebaseFirestore = FirebaseFirestore.getInstance();
            String path = this.getSnapshots().getSnapshot(position).getReference().getPath();
            list = new ArrayList<>();
            Slider.init(new SliderService(context));
            firebaseFirestore.collection(path + "/" + "Images").get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    for(DocumentSnapshot document : task.getResult().getDocuments()) {
                        String url =  document.getString("ImageURL");
                        list.add(url);
                    }
                    holder.slider.setAdapter(new SliderAdapter(list, list.size()));
                }
            });
        }
    }

    private void setScaleAnimation(View view){
        ScaleAnimation anim = new ScaleAnimation(0.6f, 1.0f, 0.6f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(700);
        view.startAnimation(anim);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public Slider slider;
        public TextView Title;
        public TextView Description;
        public CardView event_card;
        public Context context;

        public ViewHolder(View itemview){
            super(itemview);
            this.slider = itemView.findViewById(R.id.slider);
            this.Title = itemView.findViewById(R.id.tvTitle);
            this.Description = itemView.findViewById(R.id.tvDescription);
            this.event_card = itemView.findViewById(R.id.event_card);
            this.context = itemview.getContext();
        }
    }
}
