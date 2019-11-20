package com.example.aorms;

import com.google.firebase.database.IgnoreExtraProperties;

import java.sql.Time;
import java.util.List;
@IgnoreExtraProperties
public class Order {
    int order_id;
    Time ordertime;
    String status;
    int table_id;
    List<OrderDetail> OrderPlaced;

    public Order(int order_id, Time ordertime, String status, int table_id, List<OrderDetail> orderPlaced) {
        this.order_id = order_id;
        this.ordertime = ordertime;
        this.status = status;
        this.table_id = table_id;
        OrderPlaced = orderPlaced;
    }
}
