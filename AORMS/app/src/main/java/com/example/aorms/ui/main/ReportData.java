package com.example.aorms.ui.main;

import java.util.Date;

public class ReportData  implements java.io.Serializable{
    float bill;
    Date d1;


    public ReportData(float bill, Date d1) {
        this.bill = bill;
        this.d1 = d1;

    }
    public float getbill() {
    return bill;
}

    public void setbill(float bill) {
    this.bill = bill;
}

    public Date getdate() {
    return d1;
}

}
