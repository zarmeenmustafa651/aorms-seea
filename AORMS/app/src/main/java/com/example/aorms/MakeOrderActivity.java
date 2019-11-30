package com.example.aorms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity {
    Button makeOrder,deleteOrder,deleteDish,updateOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        makeOrder =  (Button)findViewById(R.id.makeorder);
        updateOrder =  (Button)findViewById(R.id.updateorder);
        deleteOrder =  (Button)findViewById(R.id.deleteorder);
        deleteDish =  (Button)findViewById(R.id.deletedish);
        makeOrder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                final ArrayList<OrderDishInfoModel> arrayList1 = new ArrayList<>();
                OrderDishInfoModel orderDishInfoModel1 = new OrderDishInfoModel(2,1,"waiting",20,5);
                OrderDishInfoModel orderDishInfoModel2 = new OrderDishInfoModel(2,1,"waiting",20,5);
                OrderDishInfoModel orderDishInfoModel3 = new OrderDishInfoModel(2,1,"waiting",20,5);
                arrayList1.add(orderDishInfoModel1);
                arrayList1.add(orderDishInfoModel2);
                arrayList1.add(orderDishInfoModel3);
                FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                final DatabaseReference dbRef = mDatabase.getReference().child("Orders");
                String mGroupId = dbRef.push().getKey();
                OrderModel o1 = new OrderModel(mGroupId,"waiting",4,45,770,arrayList1);
                dbRef.child(mGroupId).setValue(o1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Order Placed",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Order Placement Failed",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        updateOrder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("SpecialOrderQueue");
                DatabaseReference id = myRef.push();
                String orderId = "-LuvROxjFFyvtWc710Fx";
                UpdateOrder obj = new UpdateOrder(orderId);
                id.setValue(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Order Updated",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        deleteOrder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("DeleteOrderQueue");
                DatabaseReference id = myRef.push();
                String orderId = "-LuvRAMtCJSaeyUy4g_x";
                DeleteOrder obj = new DeleteOrder(orderId);
                id.setValue(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Order Deleted",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        deleteDish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("DeleteDishQueue");
                DatabaseReference id = myRef.push();
                String orderId = "-LuvRAMtCJSaeyUy4g_x";
                int key = 0;
                DeleteDishes obj = new DeleteDishes(orderId,key);
                id.setValue(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Dish Deleted",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
