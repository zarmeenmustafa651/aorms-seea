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

public class HM_DishSelect extends AppCompatActivity {
    Context c;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    List<Dish> DishArrayList1;
    List<Dish> DishArrayList2;
    DishesAdapter adapter1;
    DishesAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hm_dishselect);
      /*  Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Hall Manager");
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

        DishArrayList2 = new ArrayList<>();
        Dish d3 = new Dish();
        d3.name = "Mint Chutney";
        d3.img = getDrawable(R.drawable.chutney);
        DishArrayList2.add(d3);
        adapter2 = new DishesAdapter(DishArrayList2,R.layout.activity_gridview);
        recyclerView2 = findViewById(R.id.rv2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(adapter2);

        Button bt1=(Button) findViewById(R.id.hmProceedButton);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),HMSpecialOrderActivity.class);
                startActivity(i);
            }
        });
    }
}