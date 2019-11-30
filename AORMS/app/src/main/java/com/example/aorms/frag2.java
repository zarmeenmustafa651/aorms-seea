package com.example.aorms;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class frag2 extends Fragment {
    public static EditText nameedt ,tedt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.frag2layout, container, false);
        Button btn=view.findViewById(R.id.button4);
       nameedt=view.findViewById(R.id.itemName);
        tedt=view.findViewById(R.id.threshold);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,threshold;
                name=nameedt.getText().toString();
                threshold=tedt.getText().toString();
                 DatabaseReference mDatabase;
                String Database_Path = "Menu";
                mDatabase= FirebaseDatabase.getInstance().getReference(Database_Path);
                DatabaseReference childRef = mDatabase.child(name).child("Threshold");

                childRef.setValue(threshold, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            System.out.println("Data could not be saved " + databaseError.getMessage());

                        } else {
                            System.out.println("Data saved successfully.");
                            Toast.makeText(getActivity(),"Threshold set ",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        return view;
    }
}