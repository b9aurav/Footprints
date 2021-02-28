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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.msu.footprints.R;
import com.msu.footprints.models.Event;
import com.msu.footprints.models.Sponsor;

public class Sponsors extends Fragment {

    RecyclerView sponsors_rv;
    Sponsor_adapter adapter;
    Sponsor[] sponsor_data;

    FirebaseFirestore firebaseFirestore;
    String ImageURL;

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

        sponsors_rv = view.findViewById(R.id.sponsor_rv);
        sponsors_rv.setLayoutManager(new LinearLayoutManager(getContext()));


        //Getting data fromFirestore
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Sponsors").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                int i = 0;
                int len = 0;
                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                    len++;
                }
                sponsor_data = new Sponsor[len];
                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                    Sponsor sponsor;
                    ImageURL = documentSnapshot.getString("ImageURL");
                    sponsor = new Sponsor(ImageURL);
                    sponsor_data[i] = sponsor;
                    i++;
                }

                //Setting Adapter in Recyclerview
                adapter = new Sponsor_adapter(sponsor_data);
                sponsors_rv.setAdapter(adapter);
            }
        });
    }
}