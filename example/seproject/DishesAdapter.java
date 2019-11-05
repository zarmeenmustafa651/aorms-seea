package com.example.seproject;

import android.provider.ContactsContract;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.MyViewHolder> {
    private List <Dish> dishes;
    private int itemLayout;

    public DishesAdapter(List <Dish> dishes, int itemLayout) {
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
            dishname = view.findViewById(R.id.textView);
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
    public void onBindViewHolder(final MyViewHolder myViewHolder, int position) {
        Dish dish = dishes.get(position);
        if (dishes != null && myViewHolder != null){
            myViewHolder.dishname.setText(dish.name);
            myViewHolder.dishimg.setImageDrawable(dish.img);
            myViewHolder.dishnum.setText("1");
            myViewHolder.dishminus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int dishnum_val = Integer.parseInt(myViewHolder.dishnum.getText().toString());
                    if (dishnum_val != 0){
                        dishnum_val--;
                        myViewHolder.dishnum.setText(String.valueOf(dishnum_val));
                    }
                }
            });
            myViewHolder.dishplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int dishnum_val = Integer.parseInt(myViewHolder.dishnum.getText().toString());
                    dishnum_val++;
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
