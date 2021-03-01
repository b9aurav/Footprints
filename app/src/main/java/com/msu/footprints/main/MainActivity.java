package com.msu.footprints.main;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.msu.footprints.R;
import com.msu.footprints.fragments.AboutUsFragment;
import com.msu.footprints.fragments.AchievementFragment;
import com.msu.footprints.fragments.ContactUs;
import com.msu.footprints.fragments.EventFragment;
import com.msu.footprints.fragments.SponsorsFragment;

public class MainActivity extends AppCompatActivity{

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    TextView textView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == R.id.mReport) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView = findViewById(R.id.navigationView);

        navigationView.setCheckedItem(R.id.mHome);
        getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, new EventFragment()).commit();

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.mHome:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new EventFragment()).commit();
                    break;
                case R.id.mAchievements:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new AchievementFragment()).commit();
                    break;
                case R.id.mSponsor:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new SponsorsFragment()).commit();
                    break;
                case R.id.mContactUs:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new ContactUs()).commit();
                    break;
                case R.id.mAbout:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new AboutUsFragment()).commit();
                    break;
                default:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new EventFragment()).commit();
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        sharedPreferences = getSharedPreferences("FootPrints_Data", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Switch switchButton = navigationView.getHeaderView(0).findViewById(R.id.switch_dark);

        switchButton.setChecked(isDarkThemeOn());
        switchButton.setOnCheckedChangeListener((compoundButton, b) -> {
            changeAppTheme(!b);
        });
//        ImageView imageView = findViewById(R.id.image);
//        FirebaseFirestore.getInstance().collection("Event").document("E1").get().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                Glide.with(this /* context */)
//                        .load(task.getResult().get("URL"))
//                        .into(imageView);
//                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void changeAppTheme(boolean isChecked){
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
//        editor.putBoolean("DarkMode", !isChecked).apply();
    }
    private boolean isDarkThemeOn(){
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                return false;
            case Configuration.UI_MODE_NIGHT_YES:
                return true;
        }
        return false;
    }
}