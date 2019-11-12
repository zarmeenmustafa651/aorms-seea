package com.example.muhammadzain.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Addinventory extends AppCompatActivity {
    EditText b;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinventory);
        Spinner x;
        String UTypes[]={"Soy",
                "Corn",
                "Sunflower",
                "Nuts",
                "Seeds",
                "Beans",
                "Coconut",
                "Sweeteners",
                "Fruits",
                "Vegetables","Other"};
        ArrayAdapter<String> ad= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,UTypes);
        x=findViewById(R.id.addinventory);
        x.setAdapter(ad);

        b=findViewById(R.id.i);
        b.setText("Please Type");
        x.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s= (String) parent.getItemAtPosition(position).toString();
                b.setText(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(b.equals("Other"))
        {
            b.setText("TypeHere");
        }
    }
}
