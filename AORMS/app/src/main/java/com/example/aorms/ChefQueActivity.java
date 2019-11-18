package com.example.aorms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;

import java.util.ArrayList;

public class ChefQueActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DishAdapter adapter;
    private ArrayList<Dish> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_que);
        recyclerView = (RecyclerView) findViewById(R.id.chefque);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        arrayList = new ArrayList<>();
        for(int i=1; i< 11;i++)
            arrayList.add(new Dish("Dish " + i, "Type " + i));
        adapter = new DishAdapter(this,arrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DishAdapter.OnItemClickedListener() {
            @Override
            public void onDelete(int position) {
                removeItem(position);
            }
            @Override
            public void onMove(int position) {
                Intent intent = new Intent(getApplicationContext(), QueueChangeActivity.class);
                intent.putExtra("dishlist", arrayList);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }
    public void removeItem(int position){
        arrayList.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemChanged(position);
        adapter.notifyItemRangeChanged(position, arrayList.size());
    }
}
