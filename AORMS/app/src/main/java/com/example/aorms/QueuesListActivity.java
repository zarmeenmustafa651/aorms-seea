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

public class QueuesListActivity extends AppCompatActivity {
    private RecyclerView recyclerView1,recyclerView2,recyclerView3;
    private RecyclerView.LayoutManager layoutManager1,layoutManager2,layoutManager3;
    private ChefDishAdapter adapter1,adapter2,adapter3;
    private ArrayList<Chef> chefArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queues_list);
        DatabaseReference chefRef = FirebaseDatabase.getInstance().getReference("Chef");
        chefRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (chefArrayList.size()>0) {
                    chefArrayList.clear();
                }
                for (DataSnapshot chef : dataSnapshot.getChildren()) {
                    chefArrayList.add(chef.getValue(Chef.class));
                    Toast.makeText(getApplicationContext(), chef.getValue(Chef.class).getName(),Toast.LENGTH_SHORT).show();
                }
                //1st queue
                recyclerView1 = (RecyclerView) findViewById(R.id.chefque1);
                recyclerView1.setHasFixedSize(true);
                layoutManager1 = new LinearLayoutManager(getApplicationContext());
//                Toast.makeText(getApplicationContext(), chefArrayList.get(0).getName(),Toast.LENGTH_SHORT).show();
                if (chefArrayList.get(0).getChefOrderQueues().size()>0) {
                    adapter1 = new ChefDishAdapter(getApplicationContext(), chefArrayList.get(0).getChefOrderQueues());
                    recyclerView1.setLayoutManager(layoutManager1);
                    recyclerView1.setAdapter(adapter1);
                }
                //2nd queue
                if (chefArrayList.get(1).getChefOrderQueues().size()>0) {
                    recyclerView2 = (RecyclerView) findViewById(R.id.chefque2);
                    recyclerView2.setHasFixedSize(true);
                    layoutManager2 = new LinearLayoutManager(getApplicationContext());
                    adapter2 = new ChefDishAdapter(getApplicationContext(), chefArrayList.get(1).getChefOrderQueues());
                    recyclerView2.setLayoutManager(layoutManager2);
                    recyclerView2.setAdapter(adapter2);
                }
                //3rd queue
                if (chefArrayList.get(2).getChefOrderQueues().size()>0) {
                    recyclerView3 = (RecyclerView) findViewById(R.id.chefque3);
                    recyclerView3.setHasFixedSize(true);
                    layoutManager3 = new LinearLayoutManager(getApplicationContext());
                    adapter3 = new ChefDishAdapter(getApplicationContext(), chefArrayList.get(2).getChefOrderQueues());
                    recyclerView3.setLayoutManager(layoutManager3);
                    recyclerView3.setAdapter(adapter3);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
