package com.msu.footprints.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.github.florent37.awesomebar.AwesomeBar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.msu.footprints.R;
import java.util.ArrayList;
import java.util.List;

public class RollingSquaresFragment extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    String path, title;

    TextView Descripption;
    AwesomeBar toolbar;
    TextView titles;
    TextView preview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_rolling_squares);

        Intent intent = getIntent();
        path = intent.getStringExtra("Path");
        title = intent.getStringExtra("Title");

        toolbar = findViewById(R.id.toolbar);
        titles = findViewById(R.id.titles);
        titles.setText(title);
        Descripption = findViewById(R.id.tvDescription);
        preview = findViewById(R.id.preview);
        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.document(path).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();

                Descripption.setText(document.getString("Description"));
            }
        });

        preview.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, ImageSlider.class);
            intent1.putExtra("Path",path);
            startActivity(intent1);
        });
    }
}