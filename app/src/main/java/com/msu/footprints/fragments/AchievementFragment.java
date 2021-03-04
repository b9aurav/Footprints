package com.msu.footprints.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.msu.footprints.R;
import com.msu.footprints.main.MainActivity;
import com.msu.footprints.models.Achievement;

public class AchievementFragment extends Fragment{

    RecyclerView recyclerView;
    AchievementAdapter adapter;
    FirestoreRecyclerAdapter firestoreRecyclerAdapter;
    FirebaseFirestore firebaseFirestore;
    String ImageURL, Title, Year, Description;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_achievements, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        MainActivity.titles.setText("Achievements");

        recyclerView = view.findViewById(R.id.achievements_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Getting data fromFirestore
        firebaseFirestore = FirebaseFirestore.getInstance();
        Query query = firebaseFirestore.collection("Achievements");
        FirestoreRecyclerOptions<Achievement> options =
                new FirestoreRecyclerOptions.Builder<Achievement>().setQuery(query, Achievement.class).build();
        adapter = new AchievementAdapter(getContext(), options);
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