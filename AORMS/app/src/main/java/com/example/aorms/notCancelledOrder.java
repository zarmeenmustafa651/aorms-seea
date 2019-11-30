package com.example.aorms;

import java.util.ArrayList;

public class notCancelledOrder {
    int order_position;
    ArrayList<Integer>dishPositions;

    public notCancelledOrder() { }

    public notCancelledOrder(int order_position, ArrayList<Integer> dishPositions) {
        this.order_position = order_position;
        this.dishPositions = dishPositions;
    }

    public int getOrder_position() { return order_position; }

    public void setOrder_position(int order_position) { this.order_position = order_position; }

    public ArrayList<Integer> getdishPositions() { return dishPositions; }

    public void setdishPositions(ArrayList<Integer> dishPositions) { this.dishPositions = dishPositions; }
}