package com.example.aorms;

import android.view.GestureDetector;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KM_ViewHolder extends RecyclerView.ViewHolder {

    TextView order_NO;

    public KM_ViewHolder(@NonNull View itemView) {
        super(itemView);
        order_NO=itemView.findViewById(R.id.Ono_km);
    }
}
