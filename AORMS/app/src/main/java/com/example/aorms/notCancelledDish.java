package com.example.aorms;

public class notCancelledDish {
    int order_position;
    int dish_position;

    public notCancelledDish() {
    }

    public notCancelledDish(int order_position, int dish_position) {
        this.order_position = order_position;
        this.dish_position = dish_position;
    }

    public int getOrder_position() {
        return order_position;
    }

    public void setOrder_position(int order_position) {
        this.order_position = order_position;
    }

    public int getDish_position() {
        return dish_position;
    }

    public void setDish_position(int dish_position) {
        this.dish_position = dish_position;
    }
}
