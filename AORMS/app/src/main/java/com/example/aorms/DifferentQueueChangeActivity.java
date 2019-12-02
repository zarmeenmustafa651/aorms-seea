package com.example.aorms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import android.widget.AdapterView.OnItemSelectedListener;

public class DifferentQueueChangeActivity extends AppCompatActivity {
    private ArrayList<OrderModel> orderModelArrayList = new ArrayList<>();
    int oldChefPosition,newChefPosition,oldQueuePosition,newQueuePosition;
    EditText ed ;
    Button change;
    private ArrayList<Chef> chefArrayList = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;

    EditText sItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_queue_change);
        Intent intent = getIntent();
        sItems =  findViewById(R.id.chefs);
        getCheflist();
        getOrders();
        ed = findViewById(R.id.newPos);
        firebaseDatabase = FirebaseDatabase.getInstance();
        oldChefPosition = (int) intent.getIntExtra("chef_id",0);
        oldQueuePosition = (int) intent.getIntExtra("oldPos",0);
        ed = findViewById(R.id.newPos);
        ed.setInputType(InputType.TYPE_CLASS_NUMBER);
        change = (Button)findViewById(R.id.changequeDif);
        Toast.makeText(getApplicationContext(),"Selected", Toast.LENGTH_LONG).show();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected = sItems.getText().toString();
                //for(int j =0;j<chefArrayList.size();j++)
                //  if(selected.equals(chefArrayList.get(j).getName()))
                newChefPosition = Integer.parseInt(sItems.getText().toString());
                newQueuePosition = Integer.parseInt(ed.getText().toString());
                Toast.makeText(getApplicationContext(),Integer.toString(newChefPosition),Toast.LENGTH_LONG).show();
                moveOrder(oldChefPosition-1,newChefPosition-1,oldQueuePosition,newQueuePosition-1);

            }
        });

    }



    public void getCheflist()
    {
        DatabaseReference chefRef = FirebaseDatabase.getInstance().getReference("Chef");
        chefRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot chef : dataSnapshot.getChildren()) {
                    chefArrayList.add(chef.getValue(Chef.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void moveOrder(int oldChefPosition,int newChefPosition,int oldQueuePosition,int newQueuePosition)
    {
        //get chefs
        ChefOrderQueue chefOrderQueue = chefArrayList.get(oldChefPosition).chefOrderQueues.get(oldQueuePosition);
        int workloadOld = chefArrayList.get(oldChefPosition).getCurrentWorkload();
        int workloadNew = chefArrayList.get(newChefPosition).getCurrentWorkload();
        workloadOld = workloadOld - chefArrayList.get(oldChefPosition).chefOrderQueues.get(oldQueuePosition).getEstimated_time();
        workloadNew = workloadNew + chefArrayList.get(oldChefPosition).chefOrderQueues.get(oldQueuePosition).getEstimated_time();
        chefArrayList.get(newChefPosition).chefOrderQueues.add(newQueuePosition,chefOrderQueue);
        chefArrayList.get(oldChefPosition).chefOrderQueues.remove(oldChefPosition);
        chefArrayList.get(oldChefPosition).setCurrentWorkload(workloadOld);
        chefArrayList.get(newChefPosition).setCurrentWorkload(workloadNew);

        for (int i=0;i<chefArrayList.size();i++)
        {
            DatabaseReference chefOrderRef = FirebaseDatabase.getInstance().getReference("Chef");
            chefOrderRef.child(String.valueOf(i+1)).setValue(chefArrayList.get(i));
        }
        updateOrderTime();

    }
    public void updateOrderTime()
    {
        for (int i=0; i<chefArrayList.size();i++)
        {
            int estimated_time=0;
            for (int j=0;j<chefArrayList.get(i).chefOrderQueues.size();j++)
            {
                String order_id=chefArrayList.get(i).chefOrderQueues.get(j).getOrder_id();
                if(chefArrayList.get(i).chefOrderQueues.get(j).getStatus().equals("being_cook"))
                {
                    Date date1 = chefArrayList.get(i).chefOrderQueues.get(j).getStart_time();
                    Date date2 = Calendar.getInstance().getTime();
                    long different = date2.getTime() - date1.getTime();
                    int hours = (int) (different / (60 * 60 * 1000));
                    int min = (int) (different / (60 * 1000));
                    int seconds = (int) (different / (1000));
                    String time = hours + ":" + min +":" +seconds;
                    String[] split = time.split(":");
                    long minutes = 0;
                    if(split.length == 3) {
                        minutes = TimeUnit.HOURS.toMinutes(Integer.parseInt(split[0])) +
                                Integer.parseInt(split[1]) +
                                TimeUnit.SECONDS.toMinutes(Integer.parseInt(split[2]));
                    }
                    int y = (int) minutes;
                    int time2 = chefArrayList.get(i).chefOrderQueues.get(j).getEstimated_time()-y;
                    if (time2<0){
                        time2=0;
                    }
                    estimated_time += time2;
                }
                else {
                    estimated_time += chefArrayList.get(i).chefOrderQueues.get(j).getEstimated_time();
                }
                for (int k=0;k<orderModelArrayList.size();k++)
                {
                    if(order_id.equals(orderModelArrayList.get(k).order_id))
                    {
                        if (orderModelArrayList.get(k).order_prep_time==0)
                        {
                            orderModelArrayList.get(k).order_prep_time=estimated_time;
                        }
                        else
                        {
                            if (orderModelArrayList.get(k).order_prep_time<estimated_time)
                            {
                                orderModelArrayList.get(k).order_prep_time=estimated_time;
                            }
                        }
                        break;
                    }
                }
            }
        }
        updateOrderDatabase();
    }
    public void updateOrderDatabase()
    {
        for (int i=0;i<orderModelArrayList.size();i++)
        {
            DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("Orders");
            orderRef.child(orderModelArrayList.get(i).order_id).child("order_prep_time").setValue(orderModelArrayList.get(i).order_prep_time);
        }
    }
    public void getOrders()
    {
        DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("Orders");
        orderRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot order : dataSnapshot.getChildren()) {
                    OrderModel orderModel = order.getValue(OrderModel.class);
                    if (orderModel != null) {
                        if (orderModel.status.equals("waiting") || orderModel.status.equals("being_cook") || orderModel.status.equals("in_queue")) {
                            orderModelArrayList.add(orderModel);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
