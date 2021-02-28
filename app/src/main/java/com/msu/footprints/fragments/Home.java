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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.msu.footprints.R;
import com.msu.footprints.models.Event;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    RecyclerView events_rv;
    Event_adapter adapter;
    Event[] events_data;
    FirebaseFirestore firebaseFirestore;

    String ImageURL, Title, Summary, Description;
    Boolean Category;

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

        //Recycler View Initialization
        events_rv = view.findViewById(R.id.events_rv);
        events_rv.setLayoutManager(new LinearLayoutManager(getContext()));

        //Getting data fromFirestore
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Events").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                int i = 0;
                int len = 0;
                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                    len++;
                }
                events_data = new Event[len];
                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                    Event event;
                    ImageURL = documentSnapshot.getString("ImageURL");
                    Title = documentSnapshot.getString("Title");
                    Summary = documentSnapshot.getString("Summary");
                    Description = documentSnapshot.getString("Description");
                    Category = documentSnapshot.getBoolean("Category");
                    event = new Event(ImageURL,Title,Summary,Description);
                    events_data[i] = event;
                    i++;
                }

                //Setting Adapter in Recyclerview
                adapter = new Event_adapter(events_data);
                events_rv.setAdapter(adapter);
            }
        });
    }
}