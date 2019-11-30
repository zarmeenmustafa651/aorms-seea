package com.example.aorms;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class firebase {

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String Database_Path = "Orders";
    DatabaseReference myRef = database.getReference(Database_Path);

    List<OrderModel> Orders;
    List<OrderDishInfoModel> ordersplaced;

     public void readReportsData() {

         myRef.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot snapshot) {
                 Orders= new ArrayList<>();
                 for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                     OrderModel order = dataSnapshot.getValue(OrderModel.class);
                     Orders.add(order);
                 }
                 for(int i=0; i< Orders.size();i++){
                     ordersplaced= Orders.get(i).getOrderPlaced();
                     boolean flag = true;
                     for(int j=0;j<ordersplaced.size();j++){
                         System.out.println("firebase dish status " + ordersplaced.get(j).getDish_status());
                         if(!ordersplaced.get(j).getDish_status().equalsIgnoreCase("cooked")){
                             flag= false;
                             return;
                         }
                     }
                 }

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

                 Log.w(TAG, "loadOrder:onCancelled", databaseError.toException());
             }
         });
     }
}
