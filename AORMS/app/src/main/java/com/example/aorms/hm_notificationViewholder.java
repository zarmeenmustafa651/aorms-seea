package com.example.aorms;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class hm_notificationViewholder extends RecyclerView.ViewHolder  {
    public TextView orderid;
    public TextView tableid;
    public TextView status;

    public TextView getOrderid() {
        return orderid;
    }

    public void setOrderid(TextView orderid) {
        this.orderid = orderid;
    }

    public TextView getTableid() {
        return tableid;
    }

    public void setTableid(TextView tableid) {
        this.tableid = tableid;
    }

    public TextView getStatus() {
        return status;
    }

    public void setStatus(TextView status) {
        this.status = status;
    }

    public hm_notificationViewholder(@NonNull View itemView) {
        super(itemView);
        orderid=(TextView)itemView.findViewById(R.id.orderid);
        tableid = (TextView) itemView.findViewById(R.id.tableid);
        status= (TextView) itemView.findViewById(R.id.status);
    }
}
