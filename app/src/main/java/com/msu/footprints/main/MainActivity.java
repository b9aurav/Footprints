package com.msu.footprints.main;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.github.florent37.awesomebar.AwesomeBar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.infideap.drawerbehavior.AdvanceDrawerLayout;
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

import razerdp.basepopup.QuickPopupBuilder;
import razerdp.basepopup.QuickPopupConfig;
import razerdp.util.animation.AnimationHelper;
import razerdp.util.animation.ScaleConfig;
import razerdp.widget.QuickPopup;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity{

    private static int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;

    public static AwesomeBar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    public static TextView titles;

    QuickPopup popup;
    EditText report_text;
    TextView submit;

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

        checkPermission();

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        titles = findViewById(R.id.titles);
        titles.setText("Events");

        toolbar.setOnMenuClickedListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        toolbar.addAction(R.drawable.ic_bug_report, "Report");
        toolbar.setActionItemClickListener((position, actionItem) -> {
            report_popup();
            report_details();
        });

        navigationView = findViewById(R.id.navigationView);

        navigationView.setCheckedItem(R.id.mHome);
        getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, new EventFragment()).commit();

        navigationView.setNavigationItemSelectedListener(item -> {
            navigation_listener(item);
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
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

    public void navigation_listener(MenuItem item){
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

    public void share(){
        ApplicationInfo api = getApplicationContext().getApplicationInfo();
        String apkpath = api.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/vnd.android.package-archive");

        Uri uri = FileProvider.getUriForFile(getApplicationContext(), "com.msu.footprints.provider", new File(apkpath));

        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent, "Share App Using"));
    }

    public void report_details(){
        submit = popup.findViewById(R.id.submit);

        submit.setOnClickListener(v -> {
            report_text = popup.findViewById(R.id.etReportDescription);
            final String reportText = String.valueOf(report_text.getText()).trim();
            if (TextUtils.isEmpty(reportText)) {
                Toast.makeText(getApplicationContext(), "Please write few words!", Toast.LENGTH_SHORT).show();
            } else {
                Map<String, Object> bugs = new HashMap<>();
                bugs.put("Description", reportText);
                FirebaseFirestore.getInstance().collection("Bugs").document(UUID.randomUUID().toString()).set(bugs);
                Toast.makeText(getApplicationContext(), "Your report were submitted", Toast.LENGTH_SHORT).show();
                popup.dismiss();
            }
        });
    }

    public void report_popup(){
        popup = QuickPopupBuilder.with(this).contentView(R.layout.dialog_report_bug).config(new QuickPopupConfig().withShowAnimation(AnimationHelper.asAnimation().withScale(ScaleConfig.CENTER).toShow()).withDismissAnimation(AnimationHelper.asAnimation().withScale(ScaleConfig.CENTER).toDismiss()).withClick(R.id.dismiss, null, true).blurBackground(true).outSideDismiss(false)).show();
    }

    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(MainActivity.this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }
}