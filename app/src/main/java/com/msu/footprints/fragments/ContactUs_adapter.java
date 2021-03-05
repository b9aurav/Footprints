package com.msu.footprints.fragments;

import android.content.Context;
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
import com.msu.footprints.models.Contact;

public class ContactUs_adapter extends FirestoreRecyclerAdapter<Contact, ContactUs_adapter.ViewHolder> {

    private Context context;

    public ContactUs_adapter(Context context, @NonNull FirestoreRecyclerOptions<Contact> options){
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public ContactUs_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_contact, parent, false);
        return new ContactUs_adapter.ViewHolder(v);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ContactUs_adapter.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.contact_card.clearAnimation();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ContactUs_adapter.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        setScaleAnimation(holder.contact_card);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Contact model) {
        holder.address.setText(holder.address.getText() + model.getAddress());
        holder.email.setText(holder.email.getText() + model.getEmail());
        holder.mob.setText(holder.mob.getText() + model.getMob());
        Glide.with(holder.context)
                .load(model.getImageURL())
                .into(holder.contact);
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.6f, 1.0f, 0.6f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(700);
        view.startAnimation(anim);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView contact;
        public TextView address;
        public TextView email;
        public TextView mob;
        public CardView contact_card;
        public Context context;

        public ViewHolder(View itemview) {
            super(itemview);
            this.contact = (ImageView) itemView.findViewById(R.id.ivContact);
            this.address = (TextView) itemView.findViewById(R.id.tvAddress);
            this.email = (TextView) itemView.findViewById(R.id.tvEmail);
            this.mob = (TextView) itemView.findViewById(R.id.tvMob);
            this.contact_card = (CardView) itemView.findViewById(R.id.contact_card);
            this.context = itemview.getContext();
        }
    }
}