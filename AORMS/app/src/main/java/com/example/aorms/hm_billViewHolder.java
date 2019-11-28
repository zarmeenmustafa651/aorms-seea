package com.example.aorms;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class hm_billViewHolder extends RecyclerView.ViewHolder {
    public TextView orderid;
    public TextView tableid;
    public TextView bill;
    public TextView status;
    public Button paidbtn;


    public hm_billViewHolder(View itemView,final hm_billAdapter.OnItemClickListener listener) {
        super(itemView);
        orderid=(TextView)itemView.findViewById(R.id.setorderid);
        tableid = (TextView) itemView.findViewById(R.id.settableid);
        bill=(TextView)itemView.findViewById(R.id.setbillid);
        status= (TextView) itemView.findViewById(R.id.setbillstatus);
        paidbtn=(Button)itemView.findViewById(R.id.billstatusbtn);
        paidbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onpaidbtnClick(position);

                    }
                }
            }
        });
   }

    public Button getPaidbtn() {
        return paidbtn;
    }

    public void setPaidbtn(Button paidbtn) {
        this.paidbtn = paidbtn;
    }

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

    public TextView getBill() {
        return bill;
    }

    public void setBill(TextView bill) {
        this.bill = bill;
    }

    public TextView getStatus() {
        return status;
    }

    public void setStatus(TextView status) {
        this.status = status;
    }
}
