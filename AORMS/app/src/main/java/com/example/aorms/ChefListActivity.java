package com.example.aorms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;


public class ChefListActivity extends AppCompatActivity implements ChefAdapter.OnChefListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Chef> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_list);
        recyclerView = (RecyclerView) findViewById(R.id.cheflist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        arrayList = new ArrayList<>();
        for(int i=1; i< 11;i++)
            arrayList.add(new Chef("Chef " + i, "Specialty " + i));
        adapter = new ChefAdapter(this,arrayList,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnChefClick(int position) {
        viewQueue(position);
    }

    @Override
    public void OnViewQueue(int position) {
        viewQueue(position);
    }

    @Override
    public void OnChangeThreshold(int position) {
        Intent intent = new Intent(this, ChangeThresholdActivity.class);
        intent.putExtra("cheflist", arrayList);
        intent.putExtra("position", position);
        startActivity(intent);
    }
    public void viewQueue(int position){
        Intent intent = new Intent(this, ChefQueActivity.class);
        intent.putExtra("cheflist", arrayList);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
