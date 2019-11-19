package com.example.aorms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class KM_DishSelect extends AppCompatActivity {
    Context c;
    RecyclerView recyclerView1;
    List<Dish> DishArrayList1;
    DishesAdapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_km_dishselect);
        c=this;
      /*  String order = getIntent().getStringExtra("Orderno");
        setTitle("Kitchen Manager | Order No. "+order);*/
        DishArrayList1 = new ArrayList<>();
        Dish d1 = new Dish();
        d1.name = "Stuffed Chicken";
        d1.img =  ResourcesCompat.getDrawable(getResources(),R.drawable.cordonbleu,null);
        Dish d2 = new Dish();
        d2.name = "Chicken Cordon Bleu";
        d2.img = ResourcesCompat.getDrawable(getResources(),R.drawable.cordonbleu,null);
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
                int size1 = ((DishesAdapter) recyclerView1.getAdapter()).getItemCount();
                Intent i =new Intent(getApplicationContext(),HMSpecialOrderActivity.class);
                int k = 0;
                ArrayList <Dish> PassDishes = new ArrayList<Dish>();
                for (int j = 0 ; j < DishArrayList1.size(); j++){
                    if (DishArrayList1.get(j).getSelected()){
                        PassDishes.add(DishArrayList1.get(j));
                    }
                }
                i.putParcelableArrayListExtra("dishes", PassDishes);
                startActivity(i);
            }
        });
    }
}
