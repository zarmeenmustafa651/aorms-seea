package com.example.aorms;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Spinner x;
        String UTypes[]={"Inventory Manager", "Hall Manager", "Waiter", "Pakistani Chef", "Chinese Chef", "Continental Chef"};
        ArrayAdapter<String> ad= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,UTypes);
        x=findViewById(R.id.type);
        x.setAdapter(ad);
    }
}
