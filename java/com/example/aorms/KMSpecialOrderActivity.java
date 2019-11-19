package com.example.aorms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class KMSpecialOrderActivity extends AppCompatActivity {
    private List<SpecialOrder> orderList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SpecialOrderAdapter mAdapter;
    private DatabaseReference mDatabase;
    private Button confirmButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mDatabase = FirebaseDatabase.getInstance().getReference("OrderDetails");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Order Details");

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

        confirmButton = findViewById(R.id.kmSConfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               OrderDetail user = new OrderDetail(2, 9,1, 2400, false ,"b", 2,6);
              //  Toast.makeText(getBaseContext(), "clicked", Toast.LENGTH_LONG).show();
                DatabaseReference mOrder = mDatabase.push();
                mOrder.setValue(user);
                mOrder.push();


                // Code here executes on main thread after user presses button
            }
        });

        Button discard = findViewById(R.id.kmSpecialDiscardButton);
        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  OrderDetail user = new OrderDetail(1 , 1,1, 2400, false ,"b", 2,6);
                Toast.makeText(getBaseContext(), "clicked", Toast.LENGTH_LONG).show();
              //  mDatabase.child("users").child("OrderDetails").setValue(user);
                // Code here executes on main thread after user presses button
            }
        });



    }
}
