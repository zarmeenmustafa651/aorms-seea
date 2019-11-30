package com.example.aorms;

public class Dish {
    String name;
    String type;
    int price;
    int time;

    public Dish() {
    }

    public Dish(String name, String type, int price, int time) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
