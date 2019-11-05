package com.example.seproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class KM_DishSelect extends AppCompatActivity {
    GestureDetector gestureDetector;
    Context c;
    RecyclerView recyclerView1;
    List<Dish> DishArrayList1;
    DishesAdapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_km_dishselect);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Kitchen Manager");
        toolbar.setBackgroundColor(Color.parseColor("#687089"));*/
        c=this;

        DishArrayList1 = new ArrayList<>();
        Dish d1 = new Dish();
        d1.name = "Chicken Cordon Bleu";
        d1.img =  getDrawable(R.drawable.cordonbleu);
        Dish d2 = new Dish();
        d2.name = "Sandwiches";
        d2.img = getDrawable(R.drawable.sandwich);
        DishArrayList1.add(d1); DishArrayList1.add(d2);

        adapter1 = new DishesAdapter(DishArrayList1,R.layout.activity_gridview);
        recyclerView1 = findViewById(R.id.rv1);

        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(adapter1);


        Button bt2=(Button) findViewById(R.id.kmProceedButton);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),KMSpecialOrderActivity.class);
                startActivity(i);
            }
        });

    }
}
