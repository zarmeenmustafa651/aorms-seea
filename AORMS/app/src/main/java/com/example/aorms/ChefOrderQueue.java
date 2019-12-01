package com.example.aorms;

import java.sql.Time;
import java.util.Date;

public class ChefOrderQueue {

    String order_id;
    int dish_id;
    String name;
    String dish_type;
    int dishPosition;
    int orderPosition;
    int priority;
    String status; //( "waiting" , "being_cook" , "cooked")
    int total_time;
    int estimated_time;
    java.util.Date start_time; // Only When status change

    public ChefOrderQueue() {
    }

    public ChefOrderQueue(String order_id, int dish_id, String name,String dish_type, int dishPosition,int orderPosition,String status, int total_time, int estimated_time, Time start_time) {
        this.order_id = order_id;
        this.dish_id = dish_id;
        this.name = name;
        this.dish_type = dish_type;
        this.dishPosition=dishPosition;
        this.orderPosition = orderPosition;
        this.status = status;
        this.total_time = total_time;
        this.estimated_time = estimated_time;
        this.start_time = start_time;
    }
    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal_time() {
        return total_time;
    }

    public void setTotal_time(int total_time) {
        this.total_time = total_time;
    }

    public int getEstimated_time() {
        return estimated_time;
    }

    public void setEstimated_time(int estimated_time) {
        this.estimated_time = estimated_time;
    }

    public java.util.Date getStart_time() {
        return start_time;
    }

    public void setStart_time(java.util.Date start_time) {
        this.start_time = start_time;
    }

    public String getDish_type() { return dish_type; }

    public void setDish_type(String dish_type) { this.dish_type = dish_type; }

    public int getPriority() { return priority; }

    public void setPriority(int priority) { this.priority = priority; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getDishPosition() { return dishPosition; }

    public void setDishPosition(int dishPosition) { this.dishPosition = dishPosition; }

    public int getOrderPosition() { return orderPosition; }

    public void setOrderPosition(int orderPosition) { this.orderPosition = orderPosition; }
}