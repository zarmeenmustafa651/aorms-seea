package com.example.aorms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChangeThresholdActivity extends AppCompatActivity {

    int current_threshold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_threshold);

        final TextView textView = findViewById(R.id.currentValue);
        final TextView caution = findViewById(R.id.caution);
        caution.setVisibility(View.INVISIBLE);

        final EditText editText = findViewById(R.id.newThresh);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        final Button change = findViewById(R.id.change);

        Intent intent = getIntent();
        final String chef_id = intent.getStringExtra("chef_id");
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Chef").child(chef_id);

        dbRef.child("threshold").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                        current_threshold = dataSnapshot.getValue(Integer.class);
                        textView.setText(current_threshold + " min");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.toString(current_threshold).equals(editText.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Same Threshold...", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(editText.getText().toString()) <= 30)
                {
                    caution.setText("Threshold should be greater than 30 mins...");
                    caution.setVisibility(View.VISIBLE);
                }
                else {
                    FirebaseDatabase.getInstance().getReference().child("Chef").child(chef_id).child("threshold").setValue(Integer.parseInt(editText.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            caution.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), ChefListActivity.class);
                            startActivity(i);

                        }
                    });
                }
            }
        });
    }
}
