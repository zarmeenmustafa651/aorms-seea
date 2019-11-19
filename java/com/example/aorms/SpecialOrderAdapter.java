package com.example.aorms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SpecialOrderAdapter extends RecyclerView.Adapter<SpecialOrderAdapter.MyViewHolder> {

    private List<SpecialOrder> orderList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, quantity;
        public  ImageView photo;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.dishNameTextView);
            quantity = (TextView) view.findViewById(R.id.dishQuantitytextView);
            photo= (ImageView) view.findViewById(R.id.dishImageView);
        }
    }


    public SpecialOrderAdapter(List<SpecialOrder> moviesList) {
        this.orderList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.special_order_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SpecialOrder order = orderList.get(position);
        holder.name.setText(order.getDishName());
        holder.quantity.setText(String.valueOf(order.getQuantity()));
        holder.photo.setImageResource(order.getImgId());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}