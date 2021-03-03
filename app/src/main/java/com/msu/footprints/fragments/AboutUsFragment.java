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
import com.msu.footprints.main.MainActivity;
import com.msu.footprints.models.About;

public class AboutUsFragment extends Fragment {

    RecyclerView recyclerView;
    AboutAdapter adapter;
    About[] abouts_data;

    FirebaseFirestore firebaseFirestore;
    String Name, Email;

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

        MainActivity.toolbar.setTitle("About Us");

        //Initializing RecyclerView
        recyclerView = view.findViewById(R.id.about_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Getting data fromFirestore
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("About").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                int i = 0;
                int len = 0;
                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                    len++;
                }
                abouts_data = new About[len];
                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                    About about;
                    Name = documentSnapshot.getString("Name");
                    Email = documentSnapshot.getString("Email");
                    about = new About(Name,Email);
                    abouts_data[i] = about;
                    i++;
                }

                //Setting Adapter in Recyclerview
                adapter = new AboutAdapter(abouts_data);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}