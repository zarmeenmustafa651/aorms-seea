package com.example.muhammadzain.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddUser extends AppCompatActivity {
    EditText b;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Spinner x;
        String UTypes[]={"Please Select","Inventory Manager", "Hall Manager", "Waiter"};
        ArrayAdapter<String> ad= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,UTypes);
        x=findViewById(R.id.type);
        x.setAdapter(ad);



        b=findViewById(R.id.UserName);
        b.setText("Designation");
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
    }
}
