package com.example.aorms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QueueChangeActivity extends AppCompatActivity {
    Button sameChef,otherChef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_change);
        sameChef = (Button)findViewById(R.id.samechef);
        otherChef = (Button)findViewById(R.id.otherchef);
        sameChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SameQueueChangeActivity.class);
                startActivity(intent);
            }
        });
        otherChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DifferentQueueChangeActivity.class);
                startActivity(intent);
            }
        });
    }
}
