package com.example.aorms;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class KMSpecialOrderActivity extends AppCompatActivity {
    private List<SpecialOrder> orderList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SpecialOrderAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_km_special_order);
        recyclerView = (RecyclerView) findViewById(R.id.kmSpecialOrderSummaryrecyclerView);
        SpecialOrder st = new SpecialOrder("Stuffed Chicken",R.drawable.cordonbleu,1);
        orderList.add(st);
        st = new SpecialOrder("Chicken Cordon Bleu",R.drawable.cordonbleu,1);
        orderList.add(st);

        mAdapter = new SpecialOrderAdapter(orderList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }
}
