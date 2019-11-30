package com.example.aorms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class hm_billAdapter  extends RecyclerView.Adapter<hm_billViewHolder>  {

    private Context context;
    private List<OrderModel> orders;
    private OnItemClickListener mListener;
    int table_id;

    public interface OnItemClickListener{
        void onpaidbtnClick(int position);
    }
    public void SetOnItemClicklistener(OnItemClickListener listener) {
        mListener=listener;
    }


    public hm_billAdapter(Context context, List<OrderModel> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public hm_billViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hm_billstatusrow, parent, false);
        hm_billViewHolder holder = new hm_billViewHolder(view,mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull hm_billViewHolder holder, int position) {
        int bill=(int) orders.get(position).getBill();
        holder.orderid.setText(orders.get(position).getOrder_id());
        table_id=orders.get(position).getTable_id();
        holder.tableid.setText(Integer.toString(table_id));
        holder.bill.setText(Integer.toString(bill));
        holder.status.setText(orders.get(position).getStatus());

 }

    @Override
    public int getItemCount() {
        return orders.size();

    }


}
