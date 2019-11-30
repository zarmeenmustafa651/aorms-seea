package com.example.aorms;

import java.util.List;

public class DeleteDishes {
    String orderId;
    int dishKey;

    public DeleteDishes() {
    }

    public DeleteDishes(String orderId, int dishKey) {
        this.orderId = orderId;
        this.dishKey = dishKey;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getDishKey() {
        return dishKey;
    }

    public void setDishKey(int dishKey) {
        this.dishKey = dishKey;
    }
}
