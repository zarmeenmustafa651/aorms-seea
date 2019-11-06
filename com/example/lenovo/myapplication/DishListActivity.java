package com.example.lenovo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DishListActivity extends AppCompatActivity {

    List<Dish> dishes;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_list);
        listView = (ListView) findViewById(R.id.listview);
        dishes = new ArrayList<>();

        dishes.add(new Dish("2 hours","Biryani","Pakistani"));
        dishes.add(new Dish("15 mins","Daal Chawal","Pakistani"));
        dishes.add(new Dish("20 mins","Macroni","Chinese"));
        dishes.add(new Dish("10 mins","Tea","Pakistani"));
        dishes.add(new Dish("30 mins","Karahi","Pakistani"));

        DishListKmAdapter adapter = new DishListKmAdapter(this,R.layout.list_item_km,dishes);
        listView.setAdapter(adapter);
    }
}
