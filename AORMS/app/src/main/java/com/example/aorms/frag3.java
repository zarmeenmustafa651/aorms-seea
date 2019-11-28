package com.example.aorms;



import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class frag3 extends Fragment {

    private DatabaseReference mDatabase;
    private List<OrderModel> orders;
    private RecyclerView recyclerView;
    private hm_billAdapter adapter;
    boolean flag=true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.frag3layout, container, false);
        orders= new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.billstatusRCV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        String Database_Path = "Orders";
        mDatabase= FirebaseDatabase.getInstance().getReference(Database_Path);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    OrderModel order = dataSnapshot.getValue(OrderModel.class);
                    orders.add(order);
                    adapter = new hm_billAdapter(getContext(),orders);
                    recyclerView.setAdapter(adapter);
                    recyclerView.smoothScrollToPosition(0);
                    adapter.SetOnItemClicklistener(new hm_billAdapter.OnItemClickListener() {
                        @Override
                        public void onpaidbtnClick(int position) {
                            mDatabase.child(orders.get(position).order_id).child("status").setValue("paid");
                            flag=false;
                        }
                    });
                    if(flag==false)
                    {
                        break;
                    }
                 }
            }
             @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.w(TAG, "loadOrder:onCancelled", databaseError.toException());
            }
        });

        return view;
    }




}
