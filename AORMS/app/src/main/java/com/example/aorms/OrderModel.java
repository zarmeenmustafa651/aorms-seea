package com.example.aorms;

import com.google.firebase.database.IgnoreExtraProperties;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
@IgnoreExtraProperties
public class OrderModel {
    String order_id;
    String status;
    int table_id;
    int order_prep_time;
    float bill;
    List<OrderDishInfoModel> OrderPlaced;

    public OrderModel() {}
    public OrderModel(String order_id, String status, int table_id, int order_prep_time, float bill, List<OrderDishInfoModel> orderPlaced) {
        this.order_id = order_id;
        this.status = status;
        this.table_id = table_id;
        this.order_prep_time = order_prep_time;
        this.bill = bill;
        OrderPlaced = orderPlaced;
    }


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getOrder_prep_time() {
        return order_prep_time;
    }

    public void setOrder_prep_time(int order_prep_time) {
        this.order_prep_time = order_prep_time;
    }

    public float getBill() {
        return bill;
    }

    public void setBill(float bill) {
        this.bill = bill;
    }

    public List<OrderDishInfoModel> getOrderPlaced() {
        return OrderPlaced;
    }

    public void setOrderPlaced(List<OrderDishInfoModel> orderPlaced) {
        OrderPlaced = orderPlaced;
    }


}