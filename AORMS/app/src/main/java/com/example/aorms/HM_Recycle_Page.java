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

import java.util.ArrayList;

public class HM_Recycle_Page extends AppCompatActivity implements RecyclerView.OnItemTouchListener{

    RecyclerView rv;
    HM_Adapter md;
    ArrayList<HM_Val> dt=new ArrayList<>();
    Context c;
    GestureDetector gestureDetector;

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


        dt.add(hm1);
        dt.add(hm2);
        dt.add(hm3);
        dt.add(hm4);
        dt.add(hm5);
        dt.add(hm6);
        dt.add(hm7);
        dt.add(hm8);


        c=getApplicationContext();
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
