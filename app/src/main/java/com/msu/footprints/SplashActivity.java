package com.msu.footprints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import com.agrawalsuneet.svgloaderspack.loaders.SVGLoader;

public class SplashActivity extends AppCompatActivity{

    SVGLoader svgLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

//        SharedPreferences sharedPreferences = getSharedPreferences("FootPrints_Data", MODE_PRIVATE);
//        boolean darkMode = sharedPreferences.getBoolean("DarkMode", false);
//        if (!darkMode) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        } else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }

        setContentView(R.layout.activity_slpash);

        svgLoader = findViewById(R.id.loading);

        //Animation
        svgLoader.startAnimation();
        svgLoader.endAnimation();

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            this.finish();
        },3700);

    }

}