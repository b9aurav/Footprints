package com.msu.footprints;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class VolunteerFragment extends BottomSheetDialogFragment{

    String path;
    TextView tvVol1, tvVol2, tvVolEmail1, tvVolEmail2, tvVolMob1, tvVolMob2;

    public VolunteerFragment(String path){
        this.path = path;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_volunteer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        tvVol1 = view.findViewById(R.id.tvIncharge1);
        tvVol2 = view.findViewById(R.id.tvIncharge2);
        tvVolEmail1 = view.findViewById(R.id.tvInchargeEmail1);
        tvVolEmail2 = view.findViewById(R.id.tvInchargeEmail2);
        tvVolMob1 = view.findViewById(R.id.tvInchargeMob1);
        tvVolMob2 = view.findViewById(R.id.tvInchargeMob2);

        FirebaseFirestore.getInstance().collection(path + "/Incharge").orderBy("Priority").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<DocumentSnapshot> documents = task.getResult().getDocuments();
                if (documents.get(0) != null) {
                    view.findViewById(R.id.linearLayout1).setVisibility(View.VISIBLE);
                    tvVol1.setText(documents.get(0).getString("Name"));
                    tvVolEmail1.setText(documents.get(0).getString("Email"));
                    tvVolMob1.setText(documents.get(0).getString("Mob"));
                }
                if (documents.get(1) != null) {
                    view.findViewById(R.id.linearLayout2).setVisibility(View.VISIBLE);
                    tvVol2.setText(documents.get(1).getString("Name"));
                    tvVolEmail2.setText(documents.get(1).getString("Email"));
                    tvVolMob2.setText(documents.get(1).getString("Mob"));
                }

            }

        });
    }
}