package com.example.aorms;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {
    private OnItemClickedListener mListener;
    public void setOnItemClickListener(OnItemClickedListener listener){
        mListener = listener;
    }
    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_km,parent,false);
        return new DishViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        Dish dish = dishes.get(position);
        holder.name.setText(dish.getDish_name());
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

    public interface OnItemClickedListener{
        void onMove(int position);
        void onDone(int position);
    }

    public static class DishViewHolder extends RecyclerView.ViewHolder{
        public Button done,move;
        public TextView name,type;
        public DishViewHolder(View viewItem, final OnItemClickedListener listener){
            super(viewItem);
            name = (TextView) itemView.findViewById(R.id.dishName);
            type = (TextView) itemView.findViewById(R.id.dishType);
            move = (Button)itemView.findViewById(R.id.movebutton);
            done = (Button)itemView.findViewById(R.id.donebutton);

            move.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if(listener!= null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onMove(position);
                        }
                    }
                }
            });

            done.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if(listener!= null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onDone(position);
                        }
                    }
                }
            });
        }
    }
}
