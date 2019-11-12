package com.example.aorms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {
    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_km,parent,false);
        return new DishViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        Dish dish = dishes.get(position);
        holder.name.setText(dish.getName());
        holder.type.setText(dish.getType());
    }

    private ArrayList<Dish> dishes = new ArrayList<>();
    private Context mContext;

    public DishAdapter(Context context, ArrayList<Dish> dishes) {
        mContext = context;
        this.dishes = dishes;
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }
    public class DishViewHolder extends RecyclerView.ViewHolder{
        public TextView name,type;
        public DishViewHolder(View viewItem){
            super(viewItem);
            name = (TextView) itemView.findViewById(R.id.dishName);
            type = (TextView) itemView.findViewById(R.id.dishType);
        }
}
}
