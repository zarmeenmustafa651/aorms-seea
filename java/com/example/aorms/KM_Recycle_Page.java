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

import java.util.ArrayList;

public class KM_Recycle_Page extends AppCompatActivity implements RecyclerView.OnItemTouchListener{

    RecyclerView rv;
    KM_Adapter md;
    ArrayList<KM_Val> dt=new ArrayList<>();
    Context c;
    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_km__recycle__page);

        String[] myItems = new String[]{"Order No: 9", "Order No: 10", "Order No: 11", "Order No: 4", "Order No: 2","Order No: 1"};



        c=this;

        KM_Val km1=new KM_Val("Order No: 9");
        KM_Val km2=new KM_Val("Order No: 9");
        KM_Val km3=new KM_Val("Order No: 9");
        KM_Val km4=new KM_Val("Order No: 9");
        KM_Val km5=new KM_Val("Order No: 9");
        KM_Val km6=new KM_Val("Order No: 9");
        KM_Val km7=new KM_Val("Order No: 9");

        dt.add(km1);
        dt.add(km2);
        dt.add(km3);
        dt.add(km4);
        dt.add(km5);
        dt.add(km6);
        dt.add(km7);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                //android.R.layout.simple_list_item_1, android.R.id.text1, myItems);

        c=getApplicationContext();
        rv=findViewById(R.id.rv_km);


        gestureDetector = new GestureDetector(c, new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                //      Toast.makeText(c,"onSingleTap",Toast.LENGTH_SHORT).show();

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null)
                {


                    Intent i= new Intent(getApplicationContext(),KM_DishSelect.class);
                    i.putExtra("Orderno","9");
					i.putExtra("KM_name","shafin");
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
