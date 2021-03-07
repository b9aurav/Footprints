package com.msu.footprints.main;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.github.florent37.awesomebar.AwesomeBar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.msu.footprints.R;
import com.msu.footprints.fragments.AboutUsFragment;
import com.msu.footprints.fragments.AchievementFragment;
import com.msu.footprints.fragments.ContactUs;
import com.msu.footprints.fragments.EventFragment;
import com.msu.footprints.fragments.SponsorsFragment;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity{

    public static AwesomeBar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    public static TextView titles;
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
        titles = findViewById(R.id.titles);
        titles.setText("Events");

        toolbar.setOnMenuClickedListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        toolbar.addAction(R.drawable.ic_bug_report, "Report");
        toolbar.setActionItemClickListener((position, actionItem) -> {
            report();
        });

        navigationView = findViewById(R.id.navigationView);

        navigationView.setCheckedItem(R.id.mHome);
        getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, new EventFragment()).commit();

        navigationView.setNavigationItemSelectedListener(item -> {
            navigation_listener(item);
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
    }

    private void changeAppTheme(boolean isChecked){
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            Intent i = getIntent();
            finish();
            startActivity(i);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            Intent i = getIntent();
            finish();
            startActivity(i);
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

    public void navigation_listener(MenuItem item) {
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
//                case R.id.mShare:
//                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//                    sharingIntent.setType("application/vnd.android.package-archive");
//                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "FootPrints");
//                    startActivity(Intent.createChooser(sharingIntent, "Share app via"));
//                    share();
//                    break;
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
    }

    public void report() {
        final LayoutInflater li = LayoutInflater.from(MainActivity.this);
        final View promptsView = li.inflate(R.layout.dialog_report_bug, null);

        final EditText etReport = promptsView.findViewById(R.id.etReportDescription);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder.setCancelable(false).setPositiveButton("Send", (dialog, id) -> {
        }).setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());

        alertDialogBuilder.setTitle("Please tell me about issue!");
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            final String reportText = String.valueOf(etReport.getText()).trim();
            if (TextUtils.isEmpty(reportText)) {
                Toast.makeText(getApplicationContext(), "Please write few words!", Toast.LENGTH_SHORT).show();
            } else {
                Map<String, Object> bugs = new HashMap<>();
                bugs.put("Description", reportText);
                FirebaseFirestore.getInstance().collection("Bugs").document(UUID.randomUUID().toString()).set(bugs);
                Toast.makeText(getApplicationContext(), "Your report were submitted", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

    public void share(){
        ApplicationInfo api = getApplicationContext().getApplicationInfo();
        String apkpath = api.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/vnd.android.package-archive");

        Uri uri = FileProvider.getUriForFile(getApplicationContext(), "com.msu.footprints.provider", new File(apkpath));

        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent, "Share App Using"));
    }
}