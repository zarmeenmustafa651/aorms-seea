package com.example.aorms;

import java.sql.Time;

public class Dish implements java.io.Serializable{
    String name;
    String type;
    Time est;
    public Dish(String n, String t){
        this.name = n;
        this.type = t;
    }

    public String getName() {
        return name;
    }


    public String getType() {
        return type;
    }
}
