package com.example.aorms;

public class UpdateAndSpecialOrder {
    String orderId;

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
