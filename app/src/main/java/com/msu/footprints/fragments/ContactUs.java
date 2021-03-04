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
import com.msu.footprints.models.Achievement;
import com.msu.footprints.models.Contact;
import com.msu.footprints.models.Event;

public class ContactUs extends Fragment {

    RecyclerView contactus_rv;
    ContactUs_adapter adapter;
    Contact[] contacts_data;

    FirebaseFirestore firebaseFirestore;
    String Name, Designation, Email, ContactNo;

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
        firebaseFirestore.collection("Contact Us").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                int i = 0;
                int len = 0;
                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                    len++;
                }
                contacts_data = new Contact[len];
                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                    Contact contact;
                    Name = documentSnapshot.getString("Name");
                    Designation = documentSnapshot.getString("Designation");
                    Email = documentSnapshot.getString("Email");
                    ContactNo = documentSnapshot.getString("ContactNo");
                    contact = new Contact(Name,Designation,Email,ContactNo);
                    contacts_data[i] = contact;
                    i++;
                }

                //Setting Adapter in Recyclerview
                adapter = new ContactUs_adapter(contacts_data);
                contactus_rv.setAdapter(adapter);
            }
        });
    }
}