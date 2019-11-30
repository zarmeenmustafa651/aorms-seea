package com.example.aorms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class hm_notificationAdapter extends RecyclerView.Adapter<hm_notificationViewholder> {
    private Context context;
    private List<OrderModel> orders;

    public hm_notificationAdapter(Context context, List<OrderModel> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public hm_notificationViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hm_notification_row,parent,false);
        return new hm_notificationViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull hm_notificationViewholder holder, int position) {
        holder.orderid.setText(orders.get(position).getOrder_id());
        int table_id=orders.get(position).getTable_id();
        holder.tableid.setText(Integer.toString(table_id));
        holder.status.setText(orders.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
