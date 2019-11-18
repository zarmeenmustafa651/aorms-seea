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

public class Pakistani extends AppCompatActivity {
    EditText b,a;
    String s,t,c,d;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pakistani);
        Spinner x;
        String UTypes[]={
                "Please Select",
                "Chicken Bombay Biryani",
                "Chicken Daleem",
                "Chinioti Mutton Kunna",
                "Chicken Achar Gosht",
                "Mutton Taka Tak" +
                "Chicken Frontier Karahi",
                "Daal Gosht (Mutton)",
                "Batair Karahi",
                "Aloo Ki Bhujia",
                "Shahi Mutton Kofta",
                "Channa Curry",
                "Suji Ka Halwa with Puri","Chicken Kanpao",
                "Dragon Chicken",
                "Peking Chowmein",
                "Fried Rice","French Fries","Shahi Bun Kabab","Chicken Lasange","Other"};
        ArrayAdapter<String> ad= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,UTypes);
        x=findViewById(R.id.ctype);
        x.setAdapter(ad);
        b=findViewById(R.id.Dname);

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
        b.setText("Please Type");

        Spinner y;
        String Types[]={"Please Select","Pakistani", "Chinese", "Continental", "Other"};
        ArrayAdapter<String> ad1= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Types);
        y=findViewById(R.id.ctype2);
        y.setAdapter(ad1);


        a=findViewById(R.id.Dtype);

        y.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t= (String) parent.getItemAtPosition(position).toString();
                a.setText(t);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        a.setText("Please Type");

        btn1 = findViewById(R.id.next);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pakistani.this, AddIngrediante.class);
                startActivity(i);
            }
        });

    }
}
