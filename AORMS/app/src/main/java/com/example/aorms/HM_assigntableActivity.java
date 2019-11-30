package com.example.aorms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HM_assigntableActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    EditText tid,tbid;
    String tb_id,T_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hm_assigntable);
         Button btn= findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tid=findViewById(R.id.tidedt1);
                tbid=findViewById(R.id.tbidedt1);

                T_id=tid.getText().toString();
                tb_id=tbid.getText().toString();

                DatabaseReference mDatabase;
                String Database_Path = "Table";
                mDatabase= FirebaseDatabase.getInstance().getReference(Database_Path);
                DatabaseReference childRef = mDatabase.child(T_id).child("TabletID");
                System.out.println("Table and table id are " + T_id  +tb_id);
                childRef.setValue(tb_id, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            System.out.println("Data could not be saved " + databaseError.getMessage());

                        } else {
                            System.out.println("Data saved successfully.");
                            Toast.makeText(getBaseContext(),"Tablet assigned",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//                Intent i = new Intent(getApplicationContext(), Main2Activity.class);
//                startActivity(i);
            }
        });
    }
}
