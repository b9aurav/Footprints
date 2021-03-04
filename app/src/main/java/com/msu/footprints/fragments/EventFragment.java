package com.msu.footprints.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.msu.footprints.main.EventDetailsActivity;
import com.msu.footprints.R;
import com.msu.footprints.main.MainActivity;
import com.msu.footprints.models.Event;

public class EventFragment extends Fragment {

    RecyclerView recyclerView;
    EventAdapter adapter;
    FirestoreRecyclerAdapter firestoreRecyclerAdapter;
    FirebaseFirestore firebaseFirestore;

    //Loading Dialog
    public static AlertDialog loading;
    AlertDialog.Builder ab;
    LayoutInflater layoutInflater;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.titles.setText("Events");

        //Loading Dialog Initialization
        layoutInflater = LayoutInflater.from(getContext());
        this.view = layoutInflater.inflate(R.layout.loading_dialog, null);
        ab = new AlertDialog.Builder(getContext());
        ab.setView(this.view);
        loading = ab.create();
        loading.setCancelable(false);
        loading.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

//        loading.show();


        //Recycler View Initialization
        recyclerView = view.findViewById(R.id.events_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Getting data fromFirestore
        firebaseFirestore = FirebaseFirestore.getInstance();
        Query query = firebaseFirestore.collection("Events").orderBy("Priority");

        FirestoreRecyclerOptions<Event> options =
                new FirestoreRecyclerOptions.Builder<Event>().setQuery(query, Event.class).build();
        adapter = new EventAdapter(getContext(), options);
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