package com.msu.footprints.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.github.florent37.awesomebar.AwesomeBar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.msu.footprints.R;
import com.msu.footprints.fragments.EventAdapter;
import com.msu.footprints.models.Event;

public class EventCategoryActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    EventAdapter adapter;
    FirestoreRecyclerAdapter firestoreRecyclerAdapter;
    FirebaseFirestore firebaseFirestore;
    AwesomeBar toolbar;
    TextView titles;
    String path, title;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_category);

        Intent intent = getIntent();
        path = intent.getStringExtra("Path");
        title = intent.getStringExtra("Title");

        toolbar = findViewById(R.id.toolbar);
        titles = findViewById(R.id.titles);
        recyclerView = findViewById(R.id.recyclerview);

        titles.setText(title);
        firebaseFirestore = FirebaseFirestore.getInstance();
        titles.setText(title);
        toolbar.displayHomeAsUpEnabled(true);

        toolbar.setOnMenuClickedListener(v -> onBackPressed());

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Query query = firebaseFirestore.collection(path + "/" + title).orderBy("Priority");
        FirestoreRecyclerOptions<Event> options =
                new FirestoreRecyclerOptions.Builder<Event>().setQuery(query, Event.class).build();
        adapter = new EventAdapter(EventCategoryActivity.this, options);
        firestoreRecyclerAdapter = adapter;
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