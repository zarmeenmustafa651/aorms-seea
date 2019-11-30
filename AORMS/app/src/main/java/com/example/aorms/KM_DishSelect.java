package com.example.aorms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class KM_DishSelect extends AppCompatActivity {
    Context c;
    RecyclerView recyclerView1;
    ArrayList<DishExtender> DishArrayList1; //will be used to store the dishes that have been downloaded from the database
    ArrayList<DishExtender> DishDisplayList; //will be used to extend and display the dishes
    DishesAdapter adapter1;
    String orderKey;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference dishRef;
    ArrayList<Dish> dishList; //will be used to store the dishes that are in previous order
    List<OrderDishInfoModel> orderDishInfoModels;
    ArrayList<OrderDishInfoModel> newOrderDishInfoModels;
    OrderModel concernedOrder;
    ArrayList<Dish> temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_km_dishselect);
        c=this;

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Orders");
        dishRef = database.getReference("Dishes");

        dishList = new ArrayList<Dish>();
        DishArrayList1 = new ArrayList<>();
        newOrderDishInfoModels = new ArrayList<>();
        DishDisplayList = new ArrayList<>();
        Intent i = getIntent();
        orderKey = i.getStringExtra("orderkey");

    }
    protected void onStart(){
        super.onStart();
        final int arr[] = new int [1000];
        dishRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DishArrayList1.clear();
                for (DataSnapshot dishSnapshot: dataSnapshot.getChildren()){
                    Dish dish = dishSnapshot.getValue(Dish.class);
                    DishExtender dishExtender = new DishExtender(dish, dishSnapshot.getKey());
                    DishArrayList1.add(dishExtender);
                }
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        DishDisplayList.clear();
                        if(orderDishInfoModels != null)
                            orderDishInfoModels.clear();
                        dishList.clear();
                        DishDisplayList.clear();
                        for (DataSnapshot orderSnapshot : dataSnapshot.getChildren()){
                            OrderModel orderModel = orderSnapshot.getValue(OrderModel.class);
                            if (orderModel.getOrder_id().equals(orderKey) /*&& orderModel.table_id == 1*/){
                                // "-LunCeYj0UgxxynPv8A4
                                orderDishInfoModels = orderModel.getOrderPlaced();
                                concernedOrder = orderModel;
                            }
                        }
                        for (int j = 0; j<1000; j++){
                            arr[j]=99999;
                        }
                        int k = 0;
                        for(int i = 0; i < orderDishInfoModels.size();i++){
                            int dishId = orderDishInfoModels.get(i).dish_id;
                            boolean flag = true;
                            for (int j = 0; j<1000; j++){
                                if (dishId ==  arr[j]){
                                    flag = false;
                                }
                            }
                            if (flag) {
                                arr[k] = dishId;
                                if (!DishArrayList1.isEmpty())
                                    dishList.add(DishArrayList1.get(arr[k]-1)); //I have obtained the dishlist
                                k++;
                            }
                        }
                        for(int i = 0; i< dishList.size();i++){
                            DishExtender de = new DishExtender(dishList.get(i), "0");
                            DishDisplayList.add(de);
                        }
                        adapter1 = new DishesAdapter(DishDisplayList,R.layout.activity_gridview);
                        recyclerView1 = findViewById(R.id.rv1);
                        recyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView1.setItemAnimator(new DefaultItemAnimator());
                        recyclerView1.setAdapter(adapter1);
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("onCancelledKaMasla", "Failed to read value.", error.toException());
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("onCancelledKaMasla", "Failed to read value.", databaseError.toException());
            }
        });

        Button bt2=(Button) findViewById(R.id.kmProceedButton);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int l = 0;
                newOrderDishInfoModels.clear();
                for (int n = 0 ; arr[n] != 99999; n++) {
                    if (DishDisplayList.get(l).getSelected()) {
                        int amount = DishDisplayList.get(l).getCount();
                        for (int m = 0; m < amount; m++) {
                            OrderDishInfoModel obj = new OrderDishInfoModel();
                            obj.dish_id = arr[n];
                            obj.dish_status = "waiting";
                            obj.priority = 2;
                            obj.start_time = 0;
                            obj.setDish_prep_time(DishDisplayList.get(l).time);
                            newOrderDishInfoModels.add(obj);
                        }
                    }
                    l++;
                }
                Intent i=new Intent(getApplicationContext(),KMSpecialOrderActivity.class);
                Bundle bundle = new Bundle ();
                bundle.putSerializable("order", concernedOrder);
                bundle.putSerializable("newOrderedDishes", newOrderDishInfoModels);
                bundle.putSerializable("quantities" , DishDisplayList);
                bundle.putSerializable("allMenuDishes", DishArrayList1 );
                i.putExtras(bundle);
                startActivity(i);

            }
        });
    }
}
