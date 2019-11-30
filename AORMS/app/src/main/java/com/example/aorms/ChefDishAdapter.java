package com.example.aorms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChefDishAdapter extends RecyclerView.Adapter<ChefDishAdapter.ChefDishViewHolder> {
    @NonNull
    @Override
    public ChefDishAdapter.ChefDishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_queue_list,parent,false);
        return new ChefDishAdapter.ChefDishViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChefDishViewHolder holder, int position) {
        ChefOrderQueue dish = dishes.get(position);
        holder.name.setText(dish.getName());
        holder.type.setText(dish.getDish_type());
        holder.orderId.setText("Order Id is: " + dish.getOrder_id());
        holder.completiontime.setText("Complete in "+dish.getEstimated_time()+" mins");
    }


    private ArrayList<ChefOrderQueue> dishes = new ArrayList<>();
    private Context mContext;

    public ChefDishAdapter(Context context, ArrayList<ChefOrderQueue> dishes) {
        mContext = context;
        this.dishes = dishes;
    }
    @Override
    public int getItemCount() {
        return dishes.size();
    }


    public static class ChefDishViewHolder extends RecyclerView.ViewHolder{
        public TextView name,type,completiontime,orderId;
        public ChefDishViewHolder(View viewItem){
            super(viewItem);
            name = (TextView) itemView.findViewById(R.id.dishName);
            type = (TextView) itemView.findViewById(R.id.dishType);
            completiontime = (TextView) itemView.findViewById(R.id.completiontime);
            orderId = (TextView) itemView.findViewById(R.id.orderId);
        }
    }
}



