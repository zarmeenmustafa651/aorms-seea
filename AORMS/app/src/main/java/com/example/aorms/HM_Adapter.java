package com.example.aorms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HM_Adapter extends RecyclerView.Adapter<HM_ViewHolder> {

    ArrayList<HM_Val> dt;
    int itemlayout;

    HM_Adapter(ArrayList<HM_Val> d,int itm)
    {
        dt=d;
        itemlayout=itm;
    }

    @NonNull
    @Override
    public HM_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(itemlayout,parent,false);
        return new HM_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HM_ViewHolder holder, int position) {
        holder.Table_No.setText("Table No. " + String.valueOf(dt.get(position).tableval));
        holder.Order_No.setText("Order No. " + dt.get(position).orderval);
    }

    @Override
    public int getItemCount() {
        return dt.size();
    }
}
