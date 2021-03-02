package com.msu.footprints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EventDetails extends AppCompatActivity {
    TextView titleTV;
    TextView descriptionTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Intent i = getIntent();
//        titleTV.setText("Title");
//        descriptionTV.setText(i.getStringExtra("description"));
    }
}