package com.example.muhammadzain.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Chinese extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese);
       // setContentView(R.layout.activity_add_user);
        Spinner x;
        String UTypes[]={"Chicken Kanpao",
                "Dragon Chicken",
                "Peking Chowmein",
                "Fried Rice"};
        ArrayAdapter<String> ad= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,UTypes);
        x=findViewById(R.id.ctype);
        x.setAdapter(ad);
    }
}
