package com.example.aorms;

public class Dish {
    String name;
    String type;
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
