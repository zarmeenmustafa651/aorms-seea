package com.example.muhammadzain.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Continental extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continental);
        Spinner x;
        String UTypes[]={"French Fries","Shahi Bun Kabab","Chicken Lasange"};
        ArrayAdapter<String> ad= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,UTypes);
        x=findViewById(R.id.ctype);
        x.setAdapter(ad);
    }
}
