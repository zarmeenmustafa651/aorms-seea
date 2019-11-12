package com.example.muhammadzain.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddIngrediante extends AppCompatActivity {
    EditText a,b;
    String s;
    Button Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingrediante);
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
        x=findViewById(R.id.IngName);
        x.setAdapter(ad);

        x.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s= (String) parent.getItemAtPosition(position).toString();
                b=findViewById(R.id.IngrediantName);
                b.setText(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Btn=findViewById(R.id.Back);
        Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddIngrediante.this, Pakistani.class);
                startActivity(i);
            }
        });

    }
}
