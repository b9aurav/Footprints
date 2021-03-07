package com.msu.footprints.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.msu.footprints.R;
import com.msu.footprints.main.EventDetailsActivity;
import com.msu.footprints.main.MainActivity;
import com.msu.footprints.models.About;
import com.msu.footprints.models.Achievement;
import com.msu.footprints.models.Contact;
import com.msu.footprints.models.Event;

public class ContactUs extends Fragment {

    RecyclerView contactus_rv;
    ContactUs_adapter adapter;

    FirestoreRecyclerAdapter firestoreRecyclerAdapter;
    FirebaseFirestore firebaseFirestore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_us, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.titles.setText("Contact Us");

        //Initializing RecyclerView
        contactus_rv = view.findViewById(R.id.contact_rv);
        contactus_rv.setLayoutManager(new LinearLayoutManager(getContext()));

        //Getting data fromFirestore
        firebaseFirestore = FirebaseFirestore.getInstance();
        Query query = firebaseFirestore.collection("Contact Us");
        FirestoreRecyclerOptions<Contact> options =
                new FirestoreRecyclerOptions.Builder<Contact>().setQuery(query, Contact.class).build();
        adapter = new ContactUs_adapter(getContext(), options);
        firestoreRecyclerAdapter = adapter;
        contactus_rv.setAdapter(firestoreRecyclerAdapter);
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