package com.msu.footprints.fragments;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.zeuskartik.mediaslider.MediaSliderActivity;

import java.util.ArrayList;

public class ImageSlider extends MediaSliderActivity {

    FirebaseFirestore firebaseFirestore;

    String path;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        path = intent.getStringExtra("Path");
        firebaseFirestore = FirebaseFirestore.getInstance();
        list = new ArrayList<>();

        firebaseFirestore.document(path).collection("Images").addSnapshotListener((value, error) -> {
            if(value != null) {
                for (QueryDocumentSnapshot document : value) {
                    String url = document.getString("ImageURL");
                    list.add(url);
                }
                loadMediaSliderView(list,"image",true,true,true,"Previous Concerts","#000000",null,0);
            } else {
                Toast.makeText(ImageSlider.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
