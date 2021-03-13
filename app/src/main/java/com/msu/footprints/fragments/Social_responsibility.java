package com.msu.footprints.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.github.florent37.awesomebar.AwesomeBar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.msu.footprints.R;
import com.msu.footprints.models.Event;

public class Social_responsibility extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    String path, title;

    AwesomeBar toolbar;
    TextView titles;
    RecyclerView recyclerView;
    Social_ResponsibilityAdapter social_responsibilityAdapter;
    FirestoreRecyclerAdapter firestoreRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_responsibility);

        Intent intent = getIntent();
        path = intent.getStringExtra("Path");
        title = intent.getStringExtra("Title");

        toolbar = findViewById(R.id.toolbar);
        titles = findViewById(R.id.titles);
        titles.setText(title);
        recyclerView = findViewById(R.id.recyclerview);
        firebaseFirestore = FirebaseFirestore.getInstance();

        toolbar.displayHomeAsUpEnabled(true);
        toolbar.setOnMenuClickedListener(v -> onBackPressed());

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Query query = firebaseFirestore.collection(path + "/" + title);
        FirestoreRecyclerOptions<Event> options =
                new FirestoreRecyclerOptions.Builder<Event>().setQuery(query, Event.class).build();
        social_responsibilityAdapter = new Social_ResponsibilityAdapter(Social_responsibility.this, options, title);
        firestoreRecyclerAdapter = social_responsibilityAdapter;
        recyclerView.setAdapter(firestoreRecyclerAdapter);
    }

    @Override
    public void onStart(){
        super.onStart();
        firestoreRecyclerAdapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        firestoreRecyclerAdapter.stopListening();
    }
}