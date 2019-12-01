package com.example.aorms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import java.util.Calendar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ConstantConditions")
public class ChefQueActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DishAdapter adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int dishId;
    private Handler orderHandler;
    private Handler updateHandler;
    private Runnable orderRunnable;
    private Runnable updateRunnable;
    private int chefId;
    String orderId;
    private ArrayList<Chef> chefArrayList = new ArrayList<>();
    private ArrayList<OrderModel> orderModelArrayList = new ArrayList<>();
    private ArrayList<ChefOrderQueue> chefOrderQueueArrayList = new ArrayList<>();
    private ArrayList<Dish> arrayList;
    String dish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_que);
        recyclerView = (RecyclerView) findViewById(R.id.chefque);
        recyclerView.setHasFixedSize(true);
        Intent intent = getIntent();
        chefId = (int) intent.getIntExtra("chef_id",0);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        orderHandler = new Handler();
        updateHandler = new Handler();
        getCheflist();
        getDeleteOrder();
        getDeleteDish();
        getOrderAfterInterval();
        getUpdatesAfterInterval();

    }
    public void getCheflist()
    {
        DatabaseReference chefRef = FirebaseDatabase.getInstance().getReference("Chef");
        chefRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (chefArrayList.size()>0) {
                    chefArrayList.clear();
                }
                for (DataSnapshot chef : dataSnapshot.getChildren()) {
                    chefArrayList.add(chef.getValue(Chef.class));
                }
                arrayList = new ArrayList<>();
                for(int j = 0;j<chefArrayList.get(chefId-1).getChefOrderQueues().size();j++) {
                    arrayList.add(new Dish(chefArrayList.get(chefId-1).getChefOrderQueues().get(j).getName(), chefArrayList.get(chefId-1).getChefOrderQueues().get(j).getDish_type()));
                }
                adapter = new DishAdapter(getApplicationContext(),arrayList);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new DishAdapter.OnItemClickedListener() {
                    @Override
                    public void onMove(int position) {
                        Intent intent = new Intent(getApplicationContext(), QueueChangeActivity.class);
                        intent.putExtra("chef_id", chefId);
                        intent.putExtra("oldPos", position);
                        startActivity(intent);
                    }

                    @Override
                    public void onDone(final int position) {
                        firebaseDatabase = FirebaseDatabase.getInstance();


                        orderId = chefArrayList.get(chefId-1).getChefOrderQueues().get(position).getOrder_id();
                        dishId = chefArrayList.get(chefId-1).getChefOrderQueues().get(position).getDishPosition();
                        dish = Integer.toString(dishId);
                        final int posi = position;
                        firebaseDatabase.getReference().child("Orders").child(orderId).child("orderPlaced").child(dish).child("status").setValue("cooked").addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                firebaseDatabase.getReference().child("Chef").child(Integer.toString(chefId)).child("chefOrderQueues").child(Integer.toString(position)).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getApplicationContext(),"Deleted from Queue",Toast.LENGTH_LONG).show();
                                        adapter.notifyItemRemoved(posi);
                                        adapter.notifyItemRangeChanged(posi,arrayList.size());

                                    }
                                });
                                Toast.makeText(getApplicationContext(),"Dish Cooked",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Request Failed",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getDeleteOrder()
    {
        DatabaseReference deleteOrderRef = FirebaseDatabase.getInstance().getReference("DeleteOrderQueue");
        deleteOrderRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot orderDelete : dataSnapshot.getChildren())
                {
                    String key = orderDelete.getKey();
                    int orderPosition = 0;
                    DeleteOrder deleteOrder = orderDelete.getValue(DeleteOrder.class);
                    if (deleteOrder!=null)
                    {
                        for (int i=0;i<orderModelArrayList.size();i++)
                        {
                            if (orderModelArrayList.get(i).getOrder_id().equals(deleteOrder.getOrderId()))
                            {
                                orderPosition = i;
                                break;
                            }
                        }
                        if (orderModelArrayList.get(orderPosition).getStatus().equals("waiting") || orderModelArrayList.get(orderPosition).getStatus().equals("in_queue") )
                        {
                            orderModelArrayList.get(orderPosition).setStatus("canceled");
                            for (int m = 0; m<orderModelArrayList.get(orderPosition).OrderPlaced.size();m++)
                            {
                                DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("Orders");
                                orderRef.child(deleteOrder.getOrderId()).child("orderPlaced").child(String.valueOf(m)).child("dish_status").setValue("canceled");
                                orderModelArrayList.get(orderPosition).OrderPlaced.get(m).setDish_status("canceled");
                            }
                            for (int j = 0;j<chefArrayList.size();j++)
                            {
                                for (int k=0;k<chefArrayList.get(j).chefOrderQueues.size();k++)
                                {
                                    if (chefArrayList.get(j).chefOrderQueues.get(k).getOrder_id().equals(deleteOrder.getOrderId()))
                                    {
                                        int currentWorkload = chefArrayList.get(j).currentWorkload;
                                        currentWorkload -= chefArrayList.get(j).chefOrderQueues.get(k).estimated_time;
                                        chefArrayList.get(j).currentWorkload = currentWorkload;
                                        chefArrayList.get(j).chefOrderQueues.remove(k);
                                        k--;
                                    }
                                }
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Dish already being made",Toast.LENGTH_LONG).show();
                        }
                    }
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference("DeleteOrderQueue");
                    db.child(key).removeValue();
                }
                if (dataSnapshot.getChildrenCount()>0) {
                    updateOrderTime();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getDeleteDish()
    {
        DatabaseReference deleteDishRef = FirebaseDatabase.getInstance().getReference("DeleteDishQueue");
        deleteDishRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dishDelete : dataSnapshot.getChildren())
                    {
                        String key = dishDelete.getKey();
                        int orderPosition=0;
                        boolean possibility=true;
                        DeleteDishes deleteDishes = dishDelete.getValue(DeleteDishes.class);
                        for (int i=0;i<orderModelArrayList.size();i++)
                        {
                            if (orderModelArrayList.get(i).getOrder_id().equals(deleteDishes.getOrderId()))
                            {
                                orderPosition = i;
                                if ((orderModelArrayList.get(i).OrderPlaced.get(deleteDishes.getDishKey()).getDish_status().equals("being_cook")) ||(orderModelArrayList.get(i).OrderPlaced.get(deleteDishes.getDishKey()).getDish_status().equals("cooked")))
                                {
                                    possibility = false;
                                }
                                break;
                            }
                        }
                        if (possibility)
                        {
                            orderModelArrayList.get(orderPosition).OrderPlaced.get(deleteDishes.getDishKey()).setDish_status("canceled");
                            boolean found = true;
                            for (int j =0;j<chefArrayList.size();j++)
                            {
                                for (int k=0;k<chefArrayList.get(j).chefOrderQueues.size();k++)
                                {
                                    if (chefArrayList.get(j).chefOrderQueues.get(k).getOrder_id().equals(deleteDishes.getOrderId()))
                                    {
                                        if (chefArrayList.get(j).chefOrderQueues.get(k).getDishPosition() == deleteDishes.getDishKey())
                                        {
                                            int currentWorkload = chefArrayList.get(j).currentWorkload;
                                            currentWorkload -= chefArrayList.get(j).chefOrderQueues.get(k).estimated_time;
                                            chefArrayList.get(j).currentWorkload = currentWorkload;
                                            chefArrayList.get(j).chefOrderQueues.remove(k);
                                            k--;
                                            found = false;
                                        }
                                    }
                                    if (!found)
                                    {
                                        break;
                                    }
                                }
                                if (!found)
                                {
                                    DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("Orders");
                                    orderRef.child(deleteDishes.getOrderId()).child("orderPlaced").child(String.valueOf(deleteDishes.getDishKey())).child("dish_status").setValue("canceled");
                                    for (int g=0;g<chefArrayList.size();g++)
                                    {
                                        DatabaseReference chefOrderRef = FirebaseDatabase.getInstance().getReference("Chef");
                                        chefOrderRef.child(String.valueOf(g+1)).setValue(chefArrayList.get(g));
                                    }
                                    break;
                                }
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Dish being cooked",Toast.LENGTH_LONG).show();
                        }
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("DeleteDishQueue");
                        db.child(key).removeValue();
                    }
                    if (dataSnapshot.getChildrenCount()>0) {
                        updateOrderTime();
                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void getUpdatesAfterInterval()
    {
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                getUpdate();
                getOrderAfterInterval();
                updateHandler.postDelayed(this,20000);
            }
        };
        updateHandler.postDelayed(updateRunnable,25000);
    }

    public void getUpdate()
    {
        final ArrayList<Integer>orderIndexes=new ArrayList<>();
        final ArrayList<OrderModel>orderModels = new ArrayList<>();
        DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference("SpecialOrderQueue");
        updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final int[] count = {(int) dataSnapshot.getChildrenCount()};
                for (DataSnapshot update : dataSnapshot.getChildren())
                {
                    String key = update.getKey();
                    final UpdateAndSpecialOrder orderID = update.getValue(UpdateAndSpecialOrder.class);
                    DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("Orders").child(orderID.getOrderId());
                    orderRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            OrderModel orderModel = dataSnapshot.getValue(OrderModel.class);
                            if (orderModel!=null)
                            {
                                for (int i=0;i<orderModelArrayList.size();i++)
                                {
                                    if (orderModel.order_id.equals(orderModelArrayList.get(i).order_id)){
                                        orderIndexes.add(i);
                                        orderModels.add(orderModel);
                                        break;
                                    }
                                }
                                count[0]--;
                                if (count[0] ==0)
                                {
                                    int dishIndex = chefOrderQueueArrayList.size();
                                    for (int i=0;i<orderIndexes.size();i++)
                                    {
                                        int diff = orderModels.get(i).OrderPlaced.size() - orderModelArrayList.get(orderIndexes.get(i)).OrderPlaced.size();
                                        int j = orderModels.get(i).OrderPlaced.size() - diff;
                                        for (; j < orderModels.get(i).OrderPlaced.size(); j++)
                                        {
                                            updateOrder(j, orderModels.get(i), orderIndexes.get(i));
                                        }
                                    }
                                    getDishTypes(dishIndex);
                                    orderModels.clear();
                                    orderIndexes.clear();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference("SpecialOrderQueue");
                    db.child(key).removeValue();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void updateOrder(int dishIndex,OrderModel orderModel,int orderIndex)
    {
        orderModelArrayList.get(orderIndex).OrderPlaced.add(orderModel.OrderPlaced.get(dishIndex));
        updateChefQueue(orderIndex,orderModelArrayList.get(orderIndex).OrderPlaced.size()-1);
    }

    public void updateChefQueue(int orderIndex,int dishIndex) {
        ChefOrderQueue chefOrderQueue = new ChefOrderQueue();
        if (orderModelArrayList.get(orderIndex).OrderPlaced.get(dishIndex).dish_status.equals("waiting") || orderModelArrayList.get(orderIndex).OrderPlaced.get(dishIndex).dish_status.equals("being_cook")) {
            chefOrderQueue.setOrder_id(orderModelArrayList.get(orderIndex).order_id);
            chefOrderQueue.setDish_id(orderModelArrayList.get(orderIndex).OrderPlaced.get(dishIndex).dish_id);
            chefOrderQueue.setPriority(orderModelArrayList.get(orderIndex).OrderPlaced.get(dishIndex).getPriority());
            chefOrderQueue.setStatus(orderModelArrayList.get(orderIndex).OrderPlaced.get(dishIndex).dish_status);
            chefOrderQueue.setStatus(orderModelArrayList.get(orderIndex).OrderPlaced.get(dishIndex).dish_status);
            chefOrderQueue.setDishPosition(dishIndex);
            chefOrderQueue.setTotal_time(0);
            chefOrderQueue.setOrderPosition(orderIndex);
            chefOrderQueue.setEstimated_time(orderModelArrayList.get(orderIndex).OrderPlaced.get(dishIndex).getDish_prep_time());
            chefOrderQueueArrayList.add(chefOrderQueue);
        }
    }

    // Orders Function

    public void getOrderAfterInterval()
    {
        orderRunnable = new Runnable() {
            @Override
            public void run() {
                getOrders();
                getDeleteOrder();
                getDeleteDish();
            }
        };
        orderHandler.postDelayed(orderRunnable,10000);
    }

    public void getOrders()
    {
        DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("Orders");
        orderRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int newOrderIndex = orderModelArrayList.size();
                for (DataSnapshot order : dataSnapshot.getChildren()) {
                    OrderModel orderModel = order.getValue(OrderModel.class);
                    if (orderModel != null) {
                        if (orderModel.status.equals("waiting") || orderModel.status.equals("being_cook") || orderModel.status.equals("in_queue") ) {
                            boolean newOrder = true;
                            for (int i = 0; i < orderModelArrayList.size(); i++) {
                                if (orderModel.order_id.equals(orderModelArrayList.get(i).order_id)) {
                                    newOrder = false;
                                    break;
                                }
                            }
                            if (newOrder) {
                                orderModelArrayList.add(orderModel);
                            }
                        }
                    }
                }
                int newDishIndex = chefOrderQueueArrayList.size();
                for (int i = newOrderIndex; i < orderModelArrayList.size(); i++) {
                    if (!((orderModelArrayList.get(i).getStatus().equals("in_queue")) || (orderModelArrayList.get(i).getStatus().equals("being_cook")))) {
                        orderModelArrayList.get(i).setStatus("in_queue");
                        orderModelArrayList.get(i).setOrder_prep_time(0);
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Orders");
                        db.child(orderModelArrayList.get(i).getOrder_id()).child("status").setValue("in_queue");
                        for (int j = 0; j < orderModelArrayList.get(i).getOrderPlaced().size(); j++) {
                            if (orderModelArrayList.get(i).OrderPlaced.get(j).dish_status.equals("waiting") || orderModelArrayList.get(i).OrderPlaced.get(j).dish_status.equals("being_cook")) {
                                ChefOrderQueue chefOrderQueue = new ChefOrderQueue();
                                chefOrderQueue.setOrder_id(orderModelArrayList.get(i).order_id);
                                chefOrderQueue.setDish_id(orderModelArrayList.get(i).OrderPlaced.get(j).dish_id);
                                chefOrderQueue.setPriority(orderModelArrayList.get(i).OrderPlaced.get(j).getPriority());
                                chefOrderQueue.setStatus(orderModelArrayList.get(i).OrderPlaced.get(j).dish_status);
                                chefOrderQueue.setStatus(orderModelArrayList.get(i).OrderPlaced.get(j).dish_status);
                                chefOrderQueue.setDishPosition(j);
                                chefOrderQueue.setTotal_time(0);
                                chefOrderQueue.setOrderPosition(i);
                                chefOrderQueue.setEstimated_time(orderModelArrayList.get(i).OrderPlaced.get(j).getDish_prep_time());
                                chefOrderQueueArrayList.add(chefOrderQueue);
                            }
                        }
                    }
                }
                if (chefOrderQueueArrayList.size() > 0) {
                    getDishTypes(newDishIndex);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void getDishTypes(final int newDishIndex)
    {
        final int[] count = {chefOrderQueueArrayList.size()};
        for (int i = 0; i< chefOrderQueueArrayList.size(); i++) {
            int dish_id = chefOrderQueueArrayList.get(i).dish_id;
            DatabaseReference dishRef = FirebaseDatabase.getInstance().getReference("Dishes").child(String.valueOf(dish_id));
            final int finalI = i;
            dishRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Dish dish = dataSnapshot.getValue(Dish.class);
                    chefOrderQueueArrayList.get(finalI).dish_type=dish.type.toLowerCase();
                    chefOrderQueueArrayList.get(finalI).name = dish.name.toLowerCase();
                    count[0]--;
                    if (count[0]==0) {
                        LoadBalancingforAllOrders(newDishIndex);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }
    public void LoadBalancingforAllOrders(int newDishIndex)
    {
        DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("Orders");
        for (int i=newDishIndex;i<chefOrderQueueArrayList.size();i++) {
            int minvalue = chefArrayList.get(0).currentWorkload;
            int minindex = 0;
            ArrayList<Integer> chefqueue = new ArrayList<>();
            for (int j = 0; j < chefArrayList.size(); j++) {
                if ((minvalue > chefArrayList.get(j).currentWorkload)) {
                    minindex = j;
                    minvalue = chefArrayList.get(j).currentWorkload;
                }
                if (chefArrayList.get(j).speciality.toLowerCase().equals(chefOrderQueueArrayList.get(i).getDish_type().toLowerCase())) {
                    chefqueue.add(j);
                }
            }
            if (chefqueue.size() == 0) {
                chefArrayList.get(minindex).chefOrderQueues.add(chefOrderQueueArrayList.get(i));
                chefArrayList.get(minindex).currentWorkload += chefOrderQueueArrayList.get(i).getEstimated_time();
            } else {
                minvalue = chefArrayList.get(chefqueue.get(0)).currentWorkload;
                int mini = chefqueue.get(0);
                for (int k = 0; k < chefqueue.size(); k++) {
                    if (minvalue > chefArrayList.get(chefqueue.get(k)).currentWorkload) {
                        mini = chefqueue.get(k);
                        minvalue = chefArrayList.get(mini).currentWorkload;
                    }
                }
                boolean available = true;
                if (chefArrayList.get(minindex).currentWorkload + chefOrderQueueArrayList.get(i).getEstimated_time() > chefArrayList.get(minindex).threshold) {
                    available = false;
                }
                if ((chefArrayList.get(minindex).currentWorkload < chefArrayList.get(mini).currentWorkload) && available) {
                    if (chefArrayList.get(minindex).chefOrderQueues.size() == 0) {
                        chefOrderQueueArrayList.get(i).setStatus("being_cook");
                        String order_id = chefOrderQueueArrayList.get(i).getOrder_id();
                        String dish_position = String.valueOf(chefOrderQueueArrayList.get(i).getDishPosition());
                        orderRef.child(order_id).child("status").setValue("being_cook");
                        orderRef.child(order_id).child("orderPlaced").child(dish_position).child("dish_status").setValue("being_cook");
                        Date currentTime = Calendar.getInstance().getTime();
                        chefOrderQueueArrayList.get(i).setStart_time(currentTime);
                        String orderId = chefOrderQueueArrayList.get(i).getOrder_id();
                        int dishPosition = chefOrderQueueArrayList.get(i).getDish_id();
                        for (int m=0;m<orderModelArrayList.size();m++)
                        {
                            if (orderId.equals(orderModelArrayList.get(m).order_id))
                            {
                                orderModelArrayList.get(m).setStatus("being_cook");
                                orderModelArrayList.get(m).OrderPlaced.get(dishPosition).setDish_status("being_cook");
                                break;
                            }
                        }
                    }
                    if ((chefOrderQueueArrayList.get(i).priority == 2 || chefOrderQueueArrayList.get(i).priority == 3) && chefArrayList.get(minindex).chefOrderQueues.size() > 0) {
                        chefArrayList.get(minindex).chefOrderQueues.add(1, chefOrderQueueArrayList.get(i));
                    } else {
                        chefArrayList.get(minindex).chefOrderQueues.add(chefOrderQueueArrayList.get(i));
                    }
                    chefArrayList.get(minindex).currentWorkload += chefOrderQueueArrayList.get(i).getEstimated_time();
                } else {
                    if (chefArrayList.get(mini).chefOrderQueues.size() == 0)
                    {
                        chefOrderQueueArrayList.get(i).setStatus("being_cook");
                        String order_id = chefOrderQueueArrayList.get(i).getOrder_id();
                        String dish_position = String.valueOf(chefOrderQueueArrayList.get(i).getDishPosition());
                        orderRef.child(order_id).child("status").setValue("being_cook");
                        orderRef.child(order_id).child("orderPlaced").child(dish_position).child("dish_status").setValue("being_cook");
                        Date currentTime = Calendar.getInstance().getTime();
                        chefOrderQueueArrayList.get(i).setStart_time(currentTime);
                        String orderId = chefOrderQueueArrayList.get(i).getOrder_id();
                        int dishPosition = chefOrderQueueArrayList.get(i).getDish_id();
                        for (int m=0;m<orderModelArrayList.size();m++)
                        {
                            if (orderId.equals(orderModelArrayList.get(m).order_id))
                            {
                                orderModelArrayList.get(m).setStatus("being_cook");
                                orderModelArrayList.get(m).OrderPlaced.get(dishPosition).setDish_status("being_cook");
                                break;
                            }
                        }
                    }
                    if ((chefOrderQueueArrayList.get(i).priority == 2 || chefOrderQueueArrayList.get(i).priority == 3) && (chefArrayList.get(mini).chefOrderQueues.size() > 0)) {
                        chefArrayList.get(mini).chefOrderQueues.add(1, chefOrderQueueArrayList.get(i));
                    } else {
                        chefArrayList.get(mini).chefOrderQueues.add(chefOrderQueueArrayList.get(i));
                    }
                    chefArrayList.get(mini).currentWorkload += chefOrderQueueArrayList.get(i).getEstimated_time();
                }
                if (chefqueue.size() > 0) {
                    chefqueue.clear();
                }
            }
        }
        for (int i=0;i<chefArrayList.size();i++)
        {
            DatabaseReference chefOrderRef = FirebaseDatabase.getInstance().getReference("Chef");
            chefOrderRef.child(String.valueOf(i+1)).setValue(chefArrayList.get(i));
            //chefOrderRef.child(String.valueOf(i+1)).child("chefOrderQueues").setValue(chefArrayList.get(i).chefOrderQueues);
        }
        chefOrderQueueArrayList.clear();
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

    public void moveOrder(int oldChefPosition,int newChefPosition,int oldQueuePosition,int newQueuePosition)
    {
        ChefOrderQueue chefOrderQueue = chefArrayList.get(oldChefPosition).chefOrderQueues.get(oldQueuePosition);
        chefArrayList.get(newChefPosition).chefOrderQueues.add(newQueuePosition,chefOrderQueue);
        chefArrayList.get(oldChefPosition).chefOrderQueues.remove(oldChefPosition);
        for (int i=0;i<chefArrayList.size();i++)
        {
            DatabaseReference chefOrderRef = FirebaseDatabase.getInstance().getReference("Chef");
            chefOrderRef.child(String.valueOf(i+1)).setValue(chefArrayList.get(i));
        }
        updateOrderTime();
    }
    public void showChefDishOptions(ArrayList<notCancelledDish>notCancelledDishes)
    {

    }

}