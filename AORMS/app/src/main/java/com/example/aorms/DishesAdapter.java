package com.example.aorms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.MyViewHolder> {
    private List <DishExtender> dishes;
    private int itemLayout;

    public DishesAdapter(List <DishExtender> dishes, int itemLayout) {
        this.dishes = dishes;
        this.itemLayout = itemLayout;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dishname;
        ImageView dishimg;
        CheckBox dishchk;
        EditText dishnum;
        Button dishminus;
        Button dishplus;

        public MyViewHolder(View view) {
            super(view);
            dishname = view.findViewById(R.id.tvdishselection);
            dishimg = view.findViewById(R.id.imageView);
            dishchk = view.findViewById(R.id.checkBox);
            dishnum = view.findViewById(R.id.editText2);
            dishminus = view.findViewById(R.id.button2);
            dishplus = view.findViewById(R.id.button3);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View dishView = LayoutInflater.from(parent.getContext())
                .inflate(itemLayout, parent, false);
        return new MyViewHolder(dishView);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int position) {
        DishExtender dish = dishes.get(position);
        if (dishes != null && myViewHolder != null){
            myViewHolder.dishname.setText(dish.name);
            myViewHolder.dishnum.setText(String.valueOf(dishes.get(position).getCount()));
            myViewHolder.dishchk.setChecked(dishes.get(position).getSelected());
            myViewHolder.dishchk.setTag(position);
            myViewHolder.dishplus.setTag(position);
            myViewHolder.dishminus.setTag(position);
            myViewHolder.dishchk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Integer pos = (Integer) myViewHolder.dishchk.getTag();
                    if (dishes.get(pos).getSelected()) {
                        dishes.get(pos).setSelected(false);
                    } else {
                        dishes.get(pos).setSelected(true);
                    }
                }
            });

            myViewHolder.dishminus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (Integer) myViewHolder.dishminus.getTag();
                    int dishnum_val = Integer.parseInt(myViewHolder.dishnum.getText().toString());
                    if (dishnum_val != 0){
                        dishnum_val--;
                        dishes.get(pos).setCount(dishnum_val);
                        myViewHolder.dishnum.setText(String.valueOf(dishnum_val));
                    }
                }
            });
            myViewHolder.dishplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (Integer) myViewHolder.dishplus.getTag();
                    int dishnum_val = Integer.parseInt(myViewHolder.dishnum.getText().toString());
                    dishnum_val++;
                    dishes.get(pos).setCount(dishnum_val);
                    myViewHolder.dishnum.setText(String.valueOf(dishnum_val));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(dishes != null)
            return dishes.size();
        else
            return 0;
    }
}