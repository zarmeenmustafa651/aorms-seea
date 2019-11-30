package com.example.aorms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChefListActivity extends AppCompatActivity implements ChefAdapter.OnChefListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Chef> chefArrayList = new ArrayList<>();
    ChefAdapter.OnChefListener thisListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_list);
        recyclerView = (RecyclerView) findViewById(R.id.cheflist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        chefArrayList = new ArrayList<>();
        thisListener  = this;
        DatabaseReference chefRef = FirebaseDatabase.getInstance().getReference("Chef");
        chefRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (chefArrayList.size()>0) {
                    chefArrayList.clear();
                }
                for (DataSnapshot chef : dataSnapshot.getChildren()) {
                    chefArrayList.add(chef.getValue(Chef.class));
                }
                adapter = new ChefAdapter(getApplicationContext(),chefArrayList,thisListener);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
        String id;
        id = Integer.toString(chefArrayList.get(position).getID());
        intent.putExtra("chef_id", id);
        startActivity(intent);
    }
    public void viewQueue(int position){
        Intent intent = new Intent(this, ChefQueActivity.class);
        int id;
        id = chefArrayList.get(position).getID();
        intent.putExtra("chef_id", id);

        startActivity(intent);
    }
}
