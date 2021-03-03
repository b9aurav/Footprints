package com.msu.footprints.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.msu.footprints.R;
import com.msu.footprints.models.Event;

public class EventDetailsActivity extends AppCompatActivity{

    TextView tvProblem, tvDescription, tvRule, tvRound;
    TextView tvRuleDes, tvRoundDes;
    TextView tvSpecification, tvAbstract, tvPresentation, tvGenInstruction, tvInstruction, tvCriteria, tvProvide;
    TextView tvSpecificationDes, tvAbstractDes, tvPresentationDes, tvGenInstructionDes, tvInstructionDes, tvCriteriaDes, tvProvideDes;
    TextView tvFee, tvTeamSize, tvFeeDes, tvTeamSizeDes;

    Toolbar toolbar;
    FirebaseFirestore firebaseFirestore;
    String path, title;
    Event event;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);


        Intent intent = getIntent();
        path = intent.getStringExtra("Path");
        title = intent.getStringExtra("Title");
        init();


        firebaseFirestore.document(path).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();

                event.setTitle(title);
                event.setDescription(document.getString("Description"));
                event.setSummary(document.getString("Summary"));
                event.setRules(document.getString("Rules"));
                event.setRounds(document.getString("Rounds"));
                event.setRuleRound(document.getBoolean("RuleRound") != null);
                event.setFees(document.getString("Fees"));
                event.setTeamSize(document.getString("TeamSize"));
                event.setRegisterLink(document.getString("RegisterLink"));

                event.setAbstract(document.getString("Abstract"));
                event.setSpecification(document.getString("Specification"));
                event.setInstruction(document.getString("Instruction"));
                event.setGenInstruction(document.getString("GenInstruction"));
                event.setCriteria(document.getString("Criteria"));
                event.setProvide(document.getString("Provide"));
                event.setPresentation(document.getString("Presentation"));

                tvDescription.setText(event.getDescription());
                if (event.getFees() != null)
                    tvFeeDes.setText(event.getFees());
                else {
                    tvFeeDes.setVisibility(View.GONE);
                    tvFee.setVisibility(View.GONE);
                }

                if (event.getTeamSize() != null)
                    tvTeamSizeDes.setText(event.getTeamSize());
                else {
                    tvTeamSize.setVisibility(View.GONE);
                    tvTeamSizeDes.setVisibility(View.GONE);
                }

                if (event.isRuleRound()) {
                    tvRule.setText("Rules for Round 1");
                    tvRound.setText("Rules for Round 2");
                }
                if (event.getRules() != null)
                    tvRuleDes.setText(document.getString("Rules"));
                else {
                    tvRule.setVisibility(View.GONE);
                    tvRuleDes.setVisibility(View.GONE);
                }
                if (event.getRounds() != null)
                    tvRoundDes.setText(document.getString("Rounds"));
                else {
                    tvRound.setVisibility(View.GONE);
                    tvRoundDes.setVisibility(View.GONE);
                }

                if (event.getSpecification() != null){
                    tvSpecificationDes.setText(event.getSpecification());
                    tvSpecification.setVisibility(View.VISIBLE);
                    tvSpecificationDes.setVisibility(View.VISIBLE);
                }

                if (event.getInstruction() != null){
                    tvInstructionDes.setText(event.getInstruction());
                    tvInstruction.setVisibility(View.VISIBLE);
                    tvInstructionDes.setVisibility(View.VISIBLE);
                }

                if (event.getGenInstruction() != null){
                    tvGenInstructionDes.setText(event.getGenInstruction());
                    tvGenInstruction.setVisibility(View.VISIBLE);
                    tvGenInstructionDes.setVisibility(View.VISIBLE);
                }

                if (event.getCriteria() != null){
                    tvCriteriaDes.setText(event.getCriteria());
                    tvCriteriaDes.setVisibility(View.VISIBLE);
                    tvCriteria.setVisibility(View.VISIBLE);
                }

                if (event.getAbstract() != null){
                    tvAbstractDes.setText(event.getAbstract());
                    tvAbstractDes.setVisibility(View.VISIBLE);
                    tvAbstract.setVisibility(View.VISIBLE);
                }

            } else {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }

        });


    }

    private void init(){
        tvProblem = findViewById(R.id.tvProblem);
        tvDescription = findViewById(R.id.tvDescription);
        tvRule = findViewById(R.id.tvRule);
        tvRound = findViewById(R.id.tvRound);

        tvRuleDes = findViewById(R.id.tvRuleDescription);
        tvRoundDes = findViewById(R.id.tvRoundDescription);

        tvSpecification = findViewById(R.id.tvSpecification);
        tvAbstract = findViewById(R.id.tvAbstract);
        tvPresentation = findViewById(R.id.tvPresentation);
        tvGenInstruction = findViewById(R.id.tvGenInstruction);
        tvInstruction = findViewById(R.id.tvInstruction);
        tvCriteria = findViewById(R.id.tvCriteria);
        tvProvide = findViewById(R.id.tvProvide);

        tvSpecificationDes = findViewById(R.id.tvSpecificationDescription);
        tvAbstractDes = findViewById(R.id.tvAbstractDescription);
        tvPresentationDes = findViewById(R.id.tvPresentationDescription);
        tvGenInstructionDes = findViewById(R.id.tvGenInstructionDescription);
        tvInstructionDes = findViewById(R.id.tvInstructionDescription);
        tvCriteriaDes = findViewById(R.id.tvCriteriaDescription);
        tvProvideDes = findViewById(R.id.tvProvideDescription);

        tvFee = findViewById(R.id.tvFee);
        tvTeamSize = findViewById(R.id.tvTeamSize);
        tvFeeDes = findViewById(R.id.tvFeeDescription);
        tvTeamSizeDes = findViewById(R.id.tvTeamSizeDescription);

        firebaseFirestore = FirebaseFirestore.getInstance();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        event = new Event();
    }
}