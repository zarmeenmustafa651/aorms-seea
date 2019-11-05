package com.example.seproject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HM_ViewHolder extends RecyclerView.ViewHolder {

    TextView Table_No;
    TextView Order_No;

    public HM_ViewHolder(@NonNull View itemView) {
        super(itemView);
        Table_No=itemView.findViewById(R.id.tno_hm);
        Order_No=itemView.findViewById(R.id.Ono_hm);

    }
}
