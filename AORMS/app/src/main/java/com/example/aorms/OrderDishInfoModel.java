package com.example.aorms;

import com.google.firebase.database.IgnoreExtraProperties;

import java.sql.Time;
import java.sql.Timestamp;

@IgnoreExtraProperties
public class OrderDishInfoModel {


    int dish_id;
    int priority;
    String dish_status;
    int dish_prep_time;
    int start_time;
    public OrderDishInfoModel(){}

    public OrderDishInfoModel(int dish_id, int priority, String dish_status, int dish_prep_time, int start_time) {
        this.dish_id = dish_id;
        this.priority = priority;
        this.dish_status = dish_status;
        this.dish_prep_time = dish_prep_time;
        this.start_time = start_time;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDish_status() {
        return dish_status;
    }

    public void setDish_status(String dish_status) {
        this.dish_status = dish_status;
    }

    public int getDish_prep_time() {
        return dish_prep_time;
    }

    public void setDish_prep_time(int dish_prep_time) {
        this.dish_prep_time = dish_prep_time;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }
}