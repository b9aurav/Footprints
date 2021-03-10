package com.msu.footprints.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.github.florent37.awesomebar.AwesomeBar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.msu.footprints.R;
import com.msu.footprints.main.AssemblyEventActivity;
import com.msu.footprints.main.AssemblyEventAdapter;
import com.msu.footprints.main.RollingSquaresAdapter;
import com.msu.footprints.models.Event;

public class RollingSquares extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    String path, title;

    TextView Description, tvPrevious;
    AwesomeBar toolbar;
    TextView titles;
    RecyclerView recyclerView1, recyclerView2;
    RollingSquaresAdapter rollingSquaresAdapter1, rollingSquaresAdapter2;
    FirestoreRecyclerAdapter firestoreRecyclerAdapter1, firestoreRecyclerAdapter2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rolling_squares);

        Intent intent = getIntent();
        path = intent.getStringExtra("Path");
        title = intent.getStringExtra("Title");

        toolbar = findViewById(R.id.toolbar);
        titles = findViewById(R.id.titles);
        tvPrevious = findViewById(R.id.tvPrevious);
        titles.setText(title);
        tvPrevious.setText("Previous Concerts");
        Description = findViewById(R.id.tvDescription);
        recyclerView1 = findViewById(R.id.recyclerview1);
        recyclerView2 = findViewById(R.id.recyclerview2);
        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.document(path).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                Description.setText(document.getString("Description"));
            }
        });

        recyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Query query = firebaseFirestore.collection(path + "/" + "Images").whereEqualTo("current", true);
        FirestoreRecyclerOptions<Event> options =
                new FirestoreRecyclerOptions.Builder<Event>().setQuery(query, Event.class).build();
        rollingSquaresAdapter1 = new RollingSquaresAdapter(RollingSquares.this, options);
        firestoreRecyclerAdapter1 = rollingSquaresAdapter1;
        recyclerView1.setAdapter(firestoreRecyclerAdapter1);

        recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Query query2 = firebaseFirestore.collection(path + "/" + "Images").whereEqualTo("current", false);
        FirestoreRecyclerOptions<Event> options2 =
                new FirestoreRecyclerOptions.Builder<Event>().setQuery(query2, Event.class).build();
        rollingSquaresAdapter2 = new RollingSquaresAdapter(RollingSquares.this, options2);
        firestoreRecyclerAdapter2 = rollingSquaresAdapter2;
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