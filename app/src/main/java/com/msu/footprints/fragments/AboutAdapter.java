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

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.msu.footprints.R;
import com.msu.footprints.models.About;
import com.msu.footprints.models.Achievement;

public class AboutAdapter extends FirestoreRecyclerAdapter<About, AboutAdapter.ViewHolder> {

    private Context context;

    public AboutAdapter(Context context, @NonNull FirestoreRecyclerOptions<About> options){
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_about, parent, false);
        return new AboutAdapter.ViewHolder(v);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.about_card.clearAnimation();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        setScaleAnimation(holder.about_card);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull About model) {
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.mob.setText(model.getMob());

        holder.cb_email.setOnClickListener(v -> {
            Clipboard(holder.email);
        });

        holder.cb_mob.setOnClickListener(v -> {
            Clipboard(holder.mob);
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
        public TextView name;
        public TextView email;
        public TextView mob;
        public ImageView cb_email;
        public ImageView cb_mob;
        public CardView about_card;

        public ViewHolder(View itemview) {
            super(itemview);
            this.name = (TextView) itemView.findViewById(R.id.tvName);
            this.email = (TextView) itemView.findViewById(R.id.tvEmail);
            this.mob = (TextView) itemView.findViewById(R.id.tvMob);
            this.cb_email = (ImageView) itemView.findViewById(R.id.cb_email);
            this.cb_mob = (ImageView) itemView.findViewById(R.id.cb_contact);
            this.about_card = (CardView) itemView.findViewById(R.id.about_card);
        }
    }
}