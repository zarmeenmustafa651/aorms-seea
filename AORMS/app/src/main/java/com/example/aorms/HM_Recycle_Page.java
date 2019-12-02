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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HM_Recycle_Page extends AppCompatActivity implements RecyclerView.OnItemTouchListener{

    RecyclerView rv;
    HM_Adapter md;
    ArrayList<HM_Val> dt=new ArrayList<>();
    Context c;
    GestureDetector gestureDetector;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hm__recycle__page);

        c=getApplicationContext();
        rv=(RecyclerView) findViewById(R.id.rv_hm);

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
                        HM_Val hm =new HM_Val( orderModel.getTable_id() , "Order No: " + orderSnapshot.getKey());
                        //new KM_Val ("Order No: " + String.valueOf(i), orderSnapshot.getKey());
                        dt.add(hm);
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
                    Intent i= new Intent(getApplicationContext(),HM_DishSelect.class);
                    int row = rv.getChildAdapterPosition(child);
                    i.putExtra("orderkey", dt.get(row).orderval);
                    startActivity(i);
                }
                return true;
            }
        });

        md=new HM_Adapter(dt,R.layout.card_hm);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addOnItemTouchListener(this);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(md);



      /*  c=getApplicationContext();
        rv=findViewById(R.id.rv_hm);

        gestureDetector = new GestureDetector(c, new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                //      Toast.makeText(c,"onSingleTap",Toast.LENGTH_SHORT).show();

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null)
                {

                    Intent i= new Intent(getApplicationContext(),HM_DishSelect.class);
                    i.putExtra("Orderno","9");
                    i.putExtra("Tableno","3");
					i.putExtra("HM_name","shafin");
                    startActivity(i);



                }
                return true;
            }
        });



        md=new HM_Adapter(dt,R.layout.card_hm);
        RecyclerView rv=(RecyclerView) findViewById(R.id.rv_hm);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addOnItemTouchListener(this);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(md);
*/

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
