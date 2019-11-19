package com.example.aorms;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class OrderDetail {
    int order_id;
    int dish_id;
    int employee_id;
    int order_amount;
    boolean order_bill_status;
    String order_type;
    int priority;
    int quantity;

    public OrderDetail(int order_id, int dish_id, int employee_id, int order_amount, boolean order_bill_status, String order_type, int priority, int quantity) {
        this.order_id = order_id;
        this.dish_id = dish_id;
        this.employee_id = employee_id;
        this.order_amount = order_amount;
        this.order_bill_status = order_bill_status;
        this.order_type = order_type;
        this.priority = priority;
        this.quantity = quantity;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(int order_amount) {
        this.order_amount = order_amount;
    }

    public boolean isOrder_bill_status() {
        return order_bill_status;
    }

    public void setOrder_bill_status(boolean order_bill_status) {
        this.order_bill_status = order_bill_status;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
