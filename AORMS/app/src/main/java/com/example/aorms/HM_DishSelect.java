package com.example.aorms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
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
        c=this;
        DishArrayList1 = new ArrayList<>();
        Dish d1 = new Dish();
        d1.name = "Stuffed Chicken";
        d1.img = ResourcesCompat.getDrawable(getResources(),R.drawable.cordonbleu,null);
        Dish d2 = new Dish();
        d2.name = "Chicken Cordon Bleu";
        d2.img = ResourcesCompat.getDrawable(getResources(),R.drawable.cordonbleu,null);
        DishArrayList1.add(d1); DishArrayList1.add(d2);

        adapter1 = new DishesAdapter(DishArrayList1,R.layout.activity_gridview);
        recyclerView1 = findViewById(R.id.rv1);

        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(adapter1);

        DishArrayList2 = new ArrayList<>();
        Dish d3 = new Dish();
        d3.name = "Chicken Bread";
        d3.img = ResourcesCompat.getDrawable(getResources(),R.drawable.cordonbleu,null);
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
                int size1 = ((DishesAdapter) recyclerView1.getAdapter()).getItemCount();
                int size = ((DishesAdapter) recyclerView2.getAdapter()).getItemCount();
                Intent i =new Intent(getApplicationContext(),HMSpecialOrderActivity.class);
                int k = 0;
                ArrayList <Dish> PassDishes = new ArrayList<Dish>();
                for (int j = 0 ; j < DishArrayList1.size(); j++){
                    if (DishArrayList1.get(j).getSelected()){
                        PassDishes.add(DishArrayList1.get(j));
                    }
                }
                for (int j = 0 ; j < DishArrayList2.size(); j++){
                    if (DishArrayList2.get(j).getSelected()){
                        PassDishes.add(DishArrayList2.get(j));
                    }
                }
                i.putParcelableArrayListExtra("dishes", PassDishes);
                startActivity(i);
            }
        });
    }
}