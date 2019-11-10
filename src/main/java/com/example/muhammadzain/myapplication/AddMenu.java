package com.example.muhammadzain.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AddMenu extends AppCompatActivity {
    Button Btn1, Btn2, Btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);


        Btn1 = findViewById(R.id.c);
        Btn2 = findViewById(R.id.ch);
        Btn3 = findViewById(R.id.P);
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddMenu.this, Continental.class);
                startActivity(i);
            }
        });


        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddMenu.this, Chinese.class);
                startActivity(i);
            }
        });


        Btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddMenu.this, Pakistani.class);
                startActivity(i);
            }
        });

    }
}