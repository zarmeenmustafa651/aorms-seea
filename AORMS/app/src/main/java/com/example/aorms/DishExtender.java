package com.example.aorms;

import java.io.Serializable;

public class DishExtender extends Dish implements Serializable {

     String id;
    boolean selected;
    int count;

    public DishExtender(Dish dish, String id) {
        super(id, dish.name, dish.type,dish.price, dish.time);
        this.selected = false;
        this.count = 1;
        this.id=id;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}