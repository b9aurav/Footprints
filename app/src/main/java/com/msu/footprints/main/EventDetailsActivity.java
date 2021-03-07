package com.msu.footprints.main;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.florent37.awesomebar.AwesomeBar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.msu.footprints.R;
import com.msu.footprints.VolunteerFragment;
import com.msu.footprints.models.Event;

import java.util.List;

import razerdp.basepopup.BaseLazyPopupWindow;
import razerdp.basepopup.BasePopupFlag;
import razerdp.basepopup.BasePopupInitializer;
import razerdp.basepopup.BasePopupWindow;
import razerdp.basepopup.QuickPopupBuilder;
import razerdp.basepopup.QuickPopupConfig;
import razerdp.util.animation.AnimationHelper;
import razerdp.util.animation.ScaleConfig;
import razerdp.widget.QuickPopup;

public class EventDetailsActivity extends AppCompatActivity {

    QuickPopup popup;
    String pat;
    TextView tvVol1, tvVol2, tvVolEmail1, tvVolEmail2, tvVolMob1, tvVolMob2;
    ImageView cb_email, cb_contact, cb_email2, cb_contact2;

    TextView tvProblem, tvDescription, tvRule, tvRound;
    TextView tvRuleDes, tvRoundDes;
    TextView tvSpecification, tvAbstract, tvPresentation, tvGenInstruction, tvInstruction, tvCriteria, tvProvide, tvSubmission, tvRequirements;
    TextView tvSpecificationDes, tvAbstractDes, tvPresentationDes, tvGenInstructionDes, tvInstructionDes, tvCriteriaDes, tvProvideDes, tvSubmissionDes, tvRequirementsDes;
    TextView tvFee, tvTeamSize, tvFeeDes, tvTeamSizeDes;

    AwesomeBar toolbar;
    TextView titles;
    FirebaseFirestore firebaseFirestore;
    String path, title;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                event.setSubmission(document.getString("Submission"));
                event.setRequirements(document.getString("Requirements"));


                if (document.getBoolean("Temp") != null) {
                    Toast.makeText(getApplicationContext(), "In Developing...", Toast.LENGTH_LONG).show();
                }

                tvDescription.setText(event.getDescription().replace("\\n", "\n"));
                if (event.getFees() != null) {
                    tvFeeDes.setText(event.getFees());
                    tvFee.setVisibility(View.VISIBLE);
                    tvFeeDes.setVisibility(View.VISIBLE);
                }

                if (event.getTeamSize() != null) {
                    tvTeamSizeDes.setText(event.getTeamSize());
                    tvTeamSize.setVisibility(View.VISIBLE);
                    tvTeamSizeDes.setVisibility(View.VISIBLE);
                }

                if (event.isRuleRound()) {
                    tvRule.setText("Rules for Round 1");
                    tvRound.setText("Rules for Round 2");
                }
                if (event.getRules() != null) {
                    tvRuleDes.setText(event.getRules().replace("\\n", "\n"));
                    tvRule.setVisibility(View.VISIBLE);
                    tvRuleDes.setVisibility(View.VISIBLE);
                }
                if (event.getRounds() != null) {
                    tvRoundDes.setText(event.getRounds().replace("\\n", "\n"));
                    tvRound.setVisibility(View.VISIBLE);
                    tvRoundDes.setVisibility(View.VISIBLE);
                }

                if (event.getSpecification() != null) {
                    tvSpecificationDes.setText(event.getSpecification().replace("\\n", "\n"));
                    tvSpecification.setVisibility(View.VISIBLE);
                    tvSpecificationDes.setVisibility(View.VISIBLE);
                }

                if (event.getInstruction() != null) {
                    tvInstructionDes.setText(event.getInstruction().replace("\\n", "\n"));
                    tvInstruction.setVisibility(View.VISIBLE);
                    tvInstructionDes.setVisibility(View.VISIBLE);
                }

                if (event.getGenInstruction() != null) {
                    tvGenInstructionDes.setText(event.getGenInstruction().replace("\\n", "\n"));
                    tvGenInstruction.setVisibility(View.VISIBLE);
                    tvGenInstructionDes.setVisibility(View.VISIBLE);
                }

                if (event.getCriteria() != null) {
                    tvCriteriaDes.setText(event.getCriteria().replace("\\n", "\n"));
                    tvCriteriaDes.setVisibility(View.VISIBLE);
                    tvCriteria.setVisibility(View.VISIBLE);
                }

                if (event.getAbstract() != null) {
                    tvAbstractDes.setText(event.getAbstract().replace("\\n", "\n"));
                    tvAbstractDes.setVisibility(View.VISIBLE);
                    tvAbstract.setVisibility(View.VISIBLE);
                }

                if (event.getSubmission() != null) {
                    tvSubmissionDes.setText(event.getSubmission().replace("\\n", "\n"));
                    tvSubmissionDes.setVisibility(View.VISIBLE);
                    tvSubmission.setVisibility(View.VISIBLE);
                }

                if (event.getProvide() != null) {
                    tvProvideDes.setText(event.getProvide().replace("\\n", "\n"));
                    tvProvide.setVisibility(View.VISIBLE);
                    tvProvideDes.setVisibility(View.VISIBLE);
                }

                if (event.getRequirements() != null) {
                    tvRequirementsDes.setText(event.getRequirements().replace("\\n", "\n"));
                    tvRequirements.setVisibility(View.VISIBLE);
                    tvRequirementsDes.setVisibility(View.VISIBLE);
                }

                if (event.getPresentation() != null) {
                    tvPresentationDes.setText(event.getPresentation().replace("\\n", "\n"));
                    tvPresentation.setVisibility(View.VISIBLE);
                    tvPresentationDes.setVisibility(View.VISIBLE);
                }

            } else {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }

        });

        findViewById(R.id.fabRegister).setOnClickListener(view -> {
            if (event.getRegisterLink() != null) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(event.getRegisterLink()));
                startActivity(i);
            } else
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.fabIncharge).setOnClickListener(v -> {
            volunteer_popup();
            volunteer_details();
//            VolunteerFragment bottomSheet = new VolunteerFragment(path);
//            bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
        });

    }

    private void init() {
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
        tvSubmission = findViewById(R.id.tvSubmission);
        tvRequirements = findViewById(R.id.tvRequirements);

        tvSpecificationDes = findViewById(R.id.tvSpecificationDescription);
        tvAbstractDes = findViewById(R.id.tvAbstractDescription);
        tvPresentationDes = findViewById(R.id.tvPresentationDescription);
        tvGenInstructionDes = findViewById(R.id.tvGenInstructionDescription);
        tvInstructionDes = findViewById(R.id.tvInstructionDescription);
        tvCriteriaDes = findViewById(R.id.tvCriteriaDescription);
        tvProvideDes = findViewById(R.id.tvProvideDescription);
        tvSubmissionDes = findViewById(R.id.tvSubmissionDescription);
        tvRequirementsDes = findViewById(R.id.tvRequirementsDescription);

        tvFee = findViewById(R.id.tvFee);
        tvTeamSize = findViewById(R.id.tvTeamSize);
        tvFeeDes = findViewById(R.id.tvFeeDescription);
        tvTeamSizeDes = findViewById(R.id.tvTeamSizeDescription);

        firebaseFirestore = FirebaseFirestore.getInstance();
        toolbar = findViewById(R.id.toolbar);
        titles = findViewById(R.id.titles);
        titles.setText(title);
        toolbar.displayHomeAsUpEnabled(true);

        toolbar.setOnMenuClickedListener(v -> onBackPressed());


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);


        event = new Event();
    }

    public void volunteer_details() {
        tvVol1 = popup.findViewById(R.id.tvIncharge1);
        tvVol2 = popup.findViewById(R.id.tvIncharge2);
        tvVolEmail1 = popup.findViewById(R.id.tvInchargeEmail1);
        tvVolEmail2 = popup.findViewById(R.id.tvInchargeEmail2);
        cb_email = popup.findViewById(R.id.cb_email);
        cb_email2 = popup.findViewById(R.id.cb_email2);

        tvVolMob1 = popup.findViewById(R.id.tvInchargeMob1);
        tvVolMob2 = popup.findViewById(R.id.tvInchargeMob2);
        cb_contact = popup.findViewById(R.id.cb_contact);
        cb_contact2 = popup.findViewById(R.id.cb_contact2);

        pat = path;
        FirebaseFirestore.getInstance().collection(pat + "/Incharge").orderBy("Priority").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<DocumentSnapshot> documents = task.getResult().getDocuments();
                if (documents.get(0) != null) {
                    popup.findViewById(R.id.linearLayout1).setVisibility(View.VISIBLE);
                    tvVol1.setText(documents.get(0).getString("Name"));
                    tvVolEmail1.setText(documents.get(0).getString("Email"));
                    tvVolMob1.setText(documents.get(0).getString("Mob"));
                }
                if (documents.get(1) != null) {
                    popup.findViewById(R.id.linearLayout2).setVisibility(View.VISIBLE);
                    tvVol2.setText(documents.get(1).getString("Name"));
                    tvVolEmail2.setText(documents.get(1).getString("Email"));
                    tvVolMob2.setText(documents.get(1).getString("Mob"));
                }
            }
        });

        cb_email.setOnClickListener(v -> {
            Clipboard(tvVolEmail1);
        });
        cb_email2.setOnClickListener(v -> {
            Clipboard(tvVolEmail2);
        });
        cb_contact.setOnClickListener(v -> {
            Clipboard(tvVolMob1);
        });
        cb_contact2.setOnClickListener(v -> {
            Clipboard(tvVolMob2);
        });
    }

    public void Clipboard(TextView textView) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", textView.getText().toString().trim());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(EventDetailsActivity.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
    }

    public void volunteer_popup() {
        popup = QuickPopupBuilder.with((Context)this).contentView(R.layout.volunteer_popup).config(new QuickPopupConfig().withShowAnimation(((AnimationHelper.AnimationBuilder) AnimationHelper.asAnimation().withScale(ScaleConfig.CENTER)).toShow()).withDismissAnimation(((AnimationHelper.AnimationBuilder) AnimationHelper.asAnimation().withScale(ScaleConfig.CENTER)).toDismiss()).withClick(R.id.dismiss, (View.OnClickListener) null, true).blurBackground(true).outSideDismiss(false)).show();
    }
}