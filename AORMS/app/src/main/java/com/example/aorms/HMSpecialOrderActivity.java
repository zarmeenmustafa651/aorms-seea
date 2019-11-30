package com.example.aorms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HMSpecialOrderActivity extends AppCompatActivity {
    private List<SpecialOrder> orderList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SpecialOrderAdapter mAdapter;
    private OrderModel orderedObject;
    private List<OrderDishInfoModel>newOrderedDishes;
    private ArrayList<DishExtender> quantityList= new ArrayList<>();
    private ArrayList<DishExtender> menuList = new ArrayList<>();
    DishDetail artist;
    Ingredient one_ingredient;
    private DatabaseReference IngredientsDatabase;
    LinkedList<UpdateIngredient> Ingredient_List;
    List<DishDetail> DishIngredients;
    private DatabaseReference OrderDetailsDatabase;
    private DatabaseReference OrderDetailsDatabase1;
    private DatabaseReference DishDetailsDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hm_special_order);
        recyclerView = (RecyclerView) findViewById(R.id.hmSpecialOrderSummaryrecyclerView);
        OrderDetailsDatabase = FirebaseDatabase.getInstance().getReference("Orders");
        DishDetailsDatabase = FirebaseDatabase.getInstance().getReference("DishDetail");
        IngredientsDatabase = FirebaseDatabase.getInstance().getReference("Ingredient");

        List<OrderDishInfoModel>l= new LinkedList<>();
        DishIngredients=new LinkedList<>();
        Ingredient_List = new LinkedList<>();

        Intent intent= this.getIntent();
        Bundle bundle= intent.getExtras();

        orderedObject=(OrderModel) bundle.getSerializable("order");
        newOrderedDishes= new LinkedList<>();
        newOrderedDishes=(List<OrderDishInfoModel>) bundle.getSerializable("newOrderedDishes");
        quantityList = (ArrayList<DishExtender>)  bundle.getSerializable("quantities");
        menuList = (ArrayList<DishExtender>)  bundle.getSerializable("allMenuDishes");

        for(int i=0; i < newOrderedDishes.size(); i++)
        {
            OrderDishInfoModel dObj = newOrderedDishes.get(i);
            for(int j=0; j< menuList.size(); j++)
            {
                if(String.valueOf(dObj.getDish_id()).equals(menuList.get(j).id) )
                {
                    for(int k=0; k< quantityList.size(); k++)
                    {
                        if(menuList.get(j).getName().equals(quantityList.get(k).name))
                        {
                            boolean found=false;
                            for(int m=0; m<orderList.size() && found == false ; m++)
                            {
                                if(orderList.get(m).getDishName().equals(menuList.get(j).getName()))
                                {
                                    found =true;
                                }

                            }
                            if (found == false) {
                                SpecialOrder st = new SpecialOrder(quantityList.get(k).name,R.drawable.cordonbleu,quantityList.get(k).count);
                                orderList.add(st);
                            }


                            break;
                        }
                    }
                    break;
                }
            }
        }

        mAdapter = new SpecialOrderAdapter(orderList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
        Button confirmButton =findViewById(R.id.kmSpecialConfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for(int i=0; i<newOrderedDishes.size() ; i++)
                {
                    orderedObject.OrderPlaced.add(newOrderedDishes.get(i));
                }

               /* List<OrderDishInfoModel>l= new LinkedList<>();
                l.add(new OrderDishInfoModel(1,1,"waiting",30,20 ));
                l.add(new OrderDishInfoModel(2,1,"waiting",30,20));
                OrderModel obj = new OrderModel(order_id,"waiting",1,60,3209,l);*/
                addInSpecialOrder(orderedObject);


            }
        });

    }

    public void addInSpecialOrder(OrderModel obj)
    {
        for(int i=0; i< newOrderedDishes.size() ; i++) {
            getDishDetail(newOrderedDishes.get(i).dish_id, obj);


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("SpecialOrderQueue");
            DatabaseReference id = myRef.push();
            String orderId = orderedObject.order_id;
            UpdateAndSpecialOrder obj1 = new UpdateAndSpecialOrder(orderId);
            id.setValue(obj1);
        }
        obj.setStatus("placed");
        OrderDetailsDatabase.child(orderedObject.order_id).setValue(obj);

    }
    public void getDishDetail (final int dish_id , final  OrderModel obj) {

        OrderDetailsDatabase1.addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                                                //getting artist
                                                                artist = postSnapshot.getValue(DishDetail.class);
                                                                if(artist.getDish_Id() == dish_id)
                                                                {
                                                                    DishIngredients.add(artist);
                                                                }

                                                            }
                                                            getIngredient(DishIngredients);
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                        }
                                                    }

        );



    }
    public void updateOrder (String id ,OrderModel obj )
    {

        obj.setStatus("placed");
        OrderDetailsDatabase1= FirebaseDatabase.getInstance().getReference("Orders");
        OrderDetailsDatabase1.child("1").setValue(obj);
    }

    public void getIngredient (final List<DishDetail> Ingredients )
    {
        ;
        //.child("1").child("orderPlaced");
        IngredientsDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Ingredient_List.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    one_ingredient = postSnapshot.getValue(Ingredient.class);
                    for(int n=0; n <Ingredients.size() ;n++)
                    {
                        if(Ingredients.get(n).getIngredient_name().equals(one_ingredient.name) )
                        {
                            Ingredient_List.add(new UpdateIngredient(postSnapshot.getKey() ,Ingredients.get(n),one_ingredient));
                        }
                    }


                }
                //  getIngredient(DishIngredients);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void updateInventory(List<UpdateIngredient> updateIngredients)
    {

        for(int  m=0; m < updateIngredients.size(); m++)
        {
            Ingredient iobj= updateIngredients.get(m).ingredientDetail;
            iobj.setQuantity(updateIngredients.get(m).ingredientDetail.getQuantity() - updateIngredients.get(m).dishDetail.quantity);
            IngredientsDatabase.child( updateIngredients.get(m).key).setValue(iobj);
        }
    }
}
