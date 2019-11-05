package com.example.seproject;

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
import android.widget.Toast;

import java.util.ArrayList;

public class HM_Recycle_Page extends AppCompatActivity implements RecyclerView.OnItemTouchListener {
    GestureDetector gestureDetector;
    Context c;
    RecyclerView rv;
    HM_Adapter md;
    ArrayList<HM_Val> dt=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hm__recycle__page);

        c=this;

        HM_Val hm1=new HM_Val("Table No: 3","Order No: 6");
        HM_Val hm2=new HM_Val("Table No: 3","Order No: 6");
        HM_Val hm3=new HM_Val("Table No: 3","Order No: 6");
        HM_Val hm4=new HM_Val("Table No: 3","Order No: 6");
        HM_Val hm5=new HM_Val("Table No: 3","Order No: 6");
        HM_Val hm6=new HM_Val("Table No: 3","Order No: 6");
        HM_Val hm7=new HM_Val("Table No: 3","Order No: 6");
        HM_Val hm8=new HM_Val("Table No: 3","Order No: 6");


        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null)
                {
                    Intent i=new Intent(getApplicationContext(),HM_DishSelect.class);
                    startActivity(i);

                }
                return true;
            }
        }

        );


        dt.add(hm1);
        dt.add(hm2);
        dt.add(hm3);
        dt.add(hm4);
        dt.add(hm5);
        dt.add(hm6);
        dt.add(hm7);
        dt.add(hm8);

        md=new HM_Adapter(dt,R.layout.card_hm);
        rv=(RecyclerView) findViewById(R.id.rv_hm);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addOnItemTouchListener(this);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(md);


    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        gestureDetector.onTouchEvent(e);
        return true;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}

