package com.example.aorms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectModuleActivity extends AppCompatActivity {
    Button kitchenmodule,ordermodule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_module);
        ordermodule =  (Button)findViewById(R.id.ordermodule);
        kitchenmodule =  (Button)findViewById(R.id.kitchenmodule);

        ordermodule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MakeOrderActivity.class);
                startActivity(intent);

            }
        });
        kitchenmodule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KitchenManagerScreenActivity.class);
                startActivity(intent);

            }
        });
    }
}
