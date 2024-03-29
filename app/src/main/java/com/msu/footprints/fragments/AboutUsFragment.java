package com.msu.footprints.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.msu.footprints.R;
import com.msu.footprints.main.MainActivity;
import com.msu.footprints.models.About;
import com.msu.footprints.models.Achievement;

public class AboutUsFragment extends Fragment {

    RecyclerView recyclerView;
    AboutAdapter adapter;

    FirestoreRecyclerAdapter firestoreRecyclerAdapter;
    FirebaseFirestore firebaseFirestore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.titles.setText("About Us");

        //Initializing RecyclerView
        recyclerView = view.findViewById(R.id.about_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        //Getting data fromFirestore
        firebaseFirestore = FirebaseFirestore.getInstance();
        Query query = firebaseFirestore.collection("About").orderBy("Priority");
        FirestoreRecyclerOptions<About> options =
                new FirestoreRecyclerOptions.Builder<About>().setQuery(query, About.class).build();
        adapter = new AboutAdapter(getContext(), options);
        firestoreRecyclerAdapter = adapter;
        recyclerView.setAdapter(firestoreRecyclerAdapter);

//        startActivity(new Intent(getActivity(),ImageSlider.class));
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