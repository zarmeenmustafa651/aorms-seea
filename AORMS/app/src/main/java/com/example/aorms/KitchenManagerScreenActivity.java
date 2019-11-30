package com.example.aorms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class KitchenManagerScreenActivity extends AppCompatActivity {
    Button specialorder,notifications,chefs,queues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_manager_screen);
        chefs =  (Button)findViewById(R.id.chefs);
        queues =  (Button)findViewById(R.id.queues);
        specialorder =  (Button)findViewById(R.id.specialorder);
        chefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChefListActivity.class);
                startActivity(intent);

            }
        });
        queues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QueuesListActivity.class);
                startActivity(intent);

            }
        });
        specialorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KM_Recycle_Page.class);
                startActivity(intent);

            }
        });
    }
}
