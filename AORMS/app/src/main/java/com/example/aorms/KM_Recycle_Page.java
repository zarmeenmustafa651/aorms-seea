package com.example.aorms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KM_Recycle_Page extends AppCompatActivity implements RecyclerView.OnItemTouchListener{

    RecyclerView rv;
    KM_Adapter md;
    ArrayList<KM_Val> dt;
    Context c;
    GestureDetector gestureDetector;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_km__recycle__page);
        c=this;
        dt = new ArrayList<>();

    }
    protected void onStart() {
        super.onStart();
        c=getApplicationContext();
        rv=findViewById(R.id.rv_km);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Orders");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot orderSnapshot : dataSnapshot.getChildren()) {
                    i++;
                    OrderModel orderModel = orderSnapshot.getValue(OrderModel.class);
                    if (!orderModel.getStatus().equalsIgnoreCase("paid")){
                        KM_Val km =new KM_Val("Order No: " + orderSnapshot.getKey(), orderSnapshot.getKey());
                                //new KM_Val ("Order No: " + String.valueOf(i), orderSnapshot.getKey());
                        dt.add(km);
                    }
                }
                md.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext() , "Error fetching data from Order Table " ,Toast.LENGTH_LONG ).show();

            }
        });

        gestureDetector = new GestureDetector(c, new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null)
                {
                    Intent i= new Intent(getApplicationContext(),KM_DishSelect.class);
                    int row = rv.getChildAdapterPosition(child);
                    i.putExtra("orderkey", dt.get(row).orderkey);
                    startActivity(i);
                }
                return true;
            }
        });

        md=new KM_Adapter(dt,R.layout.card_km);
        RecyclerView rv=(RecyclerView) findViewById(R.id.rv_km);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addOnItemTouchListener(this);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(md);
    }
    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
