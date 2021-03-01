package com.msu.footprints.fragments;

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
import com.msu.footprints.models.Achievement;
import com.msu.footprints.models.Sponsor;

public class SponsorsFragment extends Fragment {

    RecyclerView recyclerView;
    SponsorAdapter adapter;
    FirestoreRecyclerAdapter firestoreRecyclerAdapter;
    FirebaseFirestore firebaseFirestore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sponsors, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.sponsor_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        firebaseFirestore = FirebaseFirestore.getInstance();
        Query query = firebaseFirestore.collection("Sponsors");
        FirestoreRecyclerOptions<Sponsor> options =
                new FirestoreRecyclerOptions.Builder<Sponsor>().setQuery(query, Sponsor.class).build();
        adapter = new SponsorAdapter(getContext(), options);
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