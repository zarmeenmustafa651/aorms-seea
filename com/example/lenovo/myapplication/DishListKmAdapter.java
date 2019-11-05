package com.example.lenovo.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class DishListKmAdapter extends ArrayAdapter<Dish> {
    Context ctx;
    int resource;
    List<Dish>dishList;
    public DishListKmAdapter(Context context, int resource, List<Dish> dishes) {
        super(context, resource,dishes);
        this.ctx = context;
        this.resource = resource;
        this.dishList = dishes;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflator = LayoutInflater.from(ctx);
        View view = inflator.inflate(resource,null);
        TextView name = (TextView)view.findViewById(R.id.dishName);
        TextView type = (TextView)view.findViewById(R.id.dishType);
        Dish dish = dishList.get(position);
        name.setText(dish.getName());
        type.setText(dish.getType());
        return view;

    }
}
