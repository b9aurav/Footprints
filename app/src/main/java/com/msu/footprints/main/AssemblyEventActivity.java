package com.msu.footprints.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.github.florent37.awesomebar.AwesomeBar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.msu.footprints.R;
import com.msu.footprints.models.Event;

public class AssemblyEventActivity extends AppCompatActivity{

    AwesomeBar toolbar;
    TextView titles, tvPrevious;
    RecyclerView recyclerView1, recyclerView2;
    FirebaseFirestore firebaseFirestore;
    AssemblyEventAdapter assemblyEventAdapter1, assemblyEventAdapter2;
    FirestoreRecyclerAdapter firestoreRecyclerAdapter1, firestoreRecyclerAdapter2;
    String path, title;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly_event);

        Intent intent = getIntent();
        path = intent.getStringExtra("Path");
        title = intent.getStringExtra("Title");

        firebaseFirestore = FirebaseFirestore.getInstance();
        toolbar = findViewById(R.id.toolbar);
        recyclerView1 = findViewById(R.id.recyclerview1);
        recyclerView2 = findViewById(R.id.recyclerview2);
        tvPrevious = findViewById(R.id.tvPrevious);
        titles = findViewById(R.id.titles);

        tvPrevious.append(" " + title);
        titles.setText(title);
        toolbar.displayHomeAsUpEnabled(true);

        toolbar.setOnMenuClickedListener(v -> onBackPressed());


        recyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Query query = firebaseFirestore.collection(path + "/" + title).whereEqualTo("current", true);
        FirestoreRecyclerOptions<Event> options =
                new FirestoreRecyclerOptions.Builder<Event>().setQuery(query, Event.class).build();
        assemblyEventAdapter1 = new AssemblyEventAdapter(AssemblyEventActivity.this, options, title);
        firestoreRecyclerAdapter1 = assemblyEventAdapter1;
        recyclerView1.setAdapter(firestoreRecyclerAdapter1);

        recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Query query2 = firebaseFirestore.collection(path + "/" + title).whereEqualTo("current", false);
        FirestoreRecyclerOptions<Event> options2 =
                new FirestoreRecyclerOptions.Builder<Event>().setQuery(query2, Event.class).build();
        assemblyEventAdapter2 = new AssemblyEventAdapter(AssemblyEventActivity.this, options2, title);
        firestoreRecyclerAdapter2 = assemblyEventAdapter2;
        recyclerView2.setAdapter(firestoreRecyclerAdapter2);
    }

    @Override
    public void onStart(){
        super.onStart();
        firestoreRecyclerAdapter1.startListening();
        firestoreRecyclerAdapter2.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        firestoreRecyclerAdapter1.stopListening();
        firestoreRecyclerAdapter2.stopListening();
    }
}