package com.msu.footprints.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
        holder.address.setText(model.getAddress());
        holder.email.setText(model.getEmail());
        holder.mob.setText(model.getMob());
        Glide.with(holder.context)
                .load(model.getImageURL())
                .into(holder.contact);

        holder.cb_email.setOnClickListener(v -> {
            Clipboard(holder.email);
        });

        holder.cb_mob.setOnClickListener(v -> {
            Clipboard(holder.mob);
        });

        holder.cb_address.setOnClickListener(v -> {
            Clipboard(holder.address);
        });
    }

    public void Clipboard(TextView textView) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", textView.getText().toString().trim());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
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
        public ImageView cb_address;
        public ImageView cb_email;
        public ImageView cb_mob;
        public CardView contact_card;
        public Context context;

        public ViewHolder(View itemview) {
            super(itemview);
            this.contact = (ImageView) itemView.findViewById(R.id.ivContact);
            this.address = (TextView) itemView.findViewById(R.id.tvAddress);
            this.email = (TextView) itemView.findViewById(R.id.tvEmail);
            this.mob = (TextView) itemView.findViewById(R.id.tvMob);
            this.cb_address = (ImageView) itemView.findViewById(R.id.cb_address);
            this.cb_email = (ImageView) itemView.findViewById(R.id.cb_email);
            this.cb_mob = (ImageView) itemView.findViewById(R.id.cb_contact);
            this.contact_card = (CardView) itemView.findViewById(R.id.contact_card);
            this.context = itemview.getContext();
        }
    }
}