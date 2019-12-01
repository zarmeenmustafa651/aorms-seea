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

public class ChefAdapter extends RecyclerView.Adapter<ChefAdapter.ChefViewHolder> {
    @NonNull
    @Override
    public ChefAdapter.ChefViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chef,parent,false);
        return new ChefAdapter.ChefViewHolder(v,mOnChefListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ChefAdapter.ChefViewHolder holder, int position) {
        Chef chef = chefs.get(position);
        holder.name.setText(chef.getName());
        holder.specialty.setText(chef.getSpecialty());
    }
    private Context mContext;
    private ArrayList<Chef> chefs = new ArrayList<>();
    private OnChefListener mOnChefListener;
    public ChefAdapter(Context context,ArrayList<Chef> chefs, OnChefListener onChefListener) {
        this.chefs = chefs;
        mOnChefListener = onChefListener;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return chefs.size();
    }
    public class ChefViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name,specialty;
        OnChefListener chefListener;
        Button changeThreshold,viewQueue;
        public ChefViewHolder(View viewItem, OnChefListener onChefListener){
            super(viewItem);
            name = (TextView) itemView.findViewById(R.id.chefName);
            specialty = (TextView) itemView.findViewById(R.id.chefSpecialty);
            chefListener = onChefListener;
            viewItem.setOnClickListener(this);
            changeThreshold = (Button) itemView.findViewById(R.id.editthresholdbutton);
            viewQueue = (Button)itemView.findViewById(R.id.viewqueuebutton);
            changeThreshold.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if(mOnChefListener!= null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            mOnChefListener.OnChangeThreshold(position);
                        }
                    }
                }
            });
            viewQueue.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if(mOnChefListener!= null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            mOnChefListener.OnViewQueue(position);
                        }
                    }
                }
            });

        }

        @Override
        public void onClick(View view) {
            chefListener.OnChefClick(getAdapterPosition());
        }

    }
    public interface OnChefListener{
        void OnChefClick(int position);
        void OnViewQueue(int position);
        void OnChangeThreshold(int position);
    }

}
