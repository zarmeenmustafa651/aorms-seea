package com.example.lenovo.myapplication;

public class Dish {
    String name;
    String est;
    String type;
    public Dish(String e, String n, String t){
        this.est = e;
        this.name = n;
        this.type = t;
    }

    public String getName() {
        return name;
    }

    public String getEst() {
        return est;
    }

    public String getType() {
        return type;
    }
}
