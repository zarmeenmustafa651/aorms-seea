package com.example.aorms;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Pakistani extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pakistani);
        Spinner x;
        String UTypes[]={"Chicken Bombay Biryani",
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
                "Suji Ka Halwa with Puri"};
        ArrayAdapter<String> ad= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,UTypes);
        x=findViewById(R.id.ctype);
        x.setAdapter(ad);
    }
}
