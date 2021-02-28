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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.msu.footprints.R;
import com.msu.footprints.models.Achievement;

public class Achievements extends Fragment {

    RecyclerView achievements_rv;
    Achievement_adapter adapter;
    Achievement[] achievements_data;

    FirebaseFirestore firebaseFirestore;
    String ImageURL, Title, Year, Description;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_achievements, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        achievements_rv = view.findViewById(R.id.achievements_rv);
        achievements_rv.setLayoutManager(new LinearLayoutManager(getContext()));



        //Getting data fromFirestore
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Achievements").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                int i = 0;
                int len = 0;
                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                    len++;
                }
                achievements_data = new Achievement[len];
                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                    Achievement achievement;
                    ImageURL = documentSnapshot.getString("ImageURL");
                    Title = documentSnapshot.getString("Title");
                    Year = documentSnapshot.getString("year");
                    Description = documentSnapshot.getString("Description");
                    achievement = new Achievement(Title,Year,Description,ImageURL);
                    achievements_data[i] = achievement;
                    i++;
                }

                //Setting Adapter in Recyclerview
                adapter = new Achievement_adapter(achievements_data);
                achievements_rv.setAdapter(adapter);
            }
        });
    }
}