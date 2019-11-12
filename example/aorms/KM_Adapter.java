package com.example.aorms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KM_Adapter extends RecyclerView.Adapter<KM_ViewHolder> {

    ArrayList<KM_Val> dt;
    int itemlayout;

    KM_Adapter(ArrayList<KM_Val> d,int itm)
    {
        dt=d;
        itemlayout=itm;
    }

    @NonNull
    @Override
    public KM_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(itemlayout,parent,false);
        return new KM_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KM_ViewHolder holder, int position) {

        holder.order_NO.setText(dt.get(position).orderval);

    }

    @Override
    public int getItemCount() {
        return dt.size();
    }
}
