package com.example.aorms;

public class UpdateAndSpecialOrder {
    String orderId;
    public UpdateAndSpecialOrder(){}
    public UpdateAndSpecialOrder(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
