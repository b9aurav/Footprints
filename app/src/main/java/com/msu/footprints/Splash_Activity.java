package com.msu.footprints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.agrawalsuneet.svgloaderspack.loaders.SVGLoader;

public class Splash_Activity extends AppCompatActivity {

    SVGLoader svgLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //IDs
        svgLoader = findViewById(R.id.loading);

        //Animation
        svgLoader.startAnimation();
        svgLoader.endAnimation();

        new Handler().postDelayed(() -> {
            startActivity(new Intent(Splash_Activity.this,MainActivity.class));
            this.finish();
        },4700);
    }
}