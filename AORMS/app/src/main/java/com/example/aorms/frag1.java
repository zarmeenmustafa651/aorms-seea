package com.example.aorms;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class frag1 extends Fragment {
    private DatabaseReference mDatabase;
    private List<OrderModel> Orders;
    private List<OrderModel> ordersready;
    private List<OrderDishInfoModel> ordersplaced;
    private RecyclerView rcv;
    private RecyclerView.Adapter adapter;
    boolean flag;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.frag1layout, container, false);

        rcv = (RecyclerView) view.findViewById(R.id.notificationRCV);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setHasFixedSize(true);

        String Database_Path = "Orders";
        mDatabase= FirebaseDatabase.getInstance().getReference(Database_Path);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Orders= new ArrayList<>();
                ordersready=new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    OrderModel order = dataSnapshot.getValue(OrderModel.class);
                    Orders.add(order);
                }
                for(int i=0; i< Orders.size();i++){
                    ordersplaced= Orders.get(i).getOrderPlaced();
                    flag=true;
                    for(int j=0;j<ordersplaced.size();j++){
                        System.out.println("firebase dish status " + ordersplaced.get(j).getDish_status());
                        if(!ordersplaced.get(j).getDish_status().equalsIgnoreCase("cooked")){
                           flag= false;
                           return;
                        }
                    }
                    if(flag==true)
                    {
                      ordersready.add(Orders.get(i));
                      int x= ordersready.indexOf(Orders.get(i));
                      System.out.println("index " + x);
                      ordersready.get(x).setStatus("Ready to server");
                      System.out.println("changed order status " + ordersready.get(x).getStatus());
                      mDatabase.child(Orders.get(i).order_id).child("status").setValue("ready",
                                new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if (databaseError != null) {
                                    System.out.println("Data could not be saved " + databaseError.getMessage());

                                } else {
                                    System.out.println("Data saved successfully.");
                                    Toast.makeText(getActivity(),"changed order status",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                    adapter = new hm_notificationAdapter(getContext(),ordersready);
                    rcv.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.w(TAG, "loadOrder:onCancelled", databaseError.toException());
            }
        });

        return view;
    }
}
