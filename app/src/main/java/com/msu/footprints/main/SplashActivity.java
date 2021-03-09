package com.msu.footprints.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.agrawalsuneet.svgloaderspack.loaders.SVGLoader;
import com.msu.footprints.R;

public class SplashActivity extends AppCompatActivity{

    SVGLoader svgLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slpash);

        svgLoader = findViewById(R.id.loading);

        //Animation
        svgLoader.startAnimation();
        svgLoader.endAnimation();

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            this.finish();
        },3700);

    }

}