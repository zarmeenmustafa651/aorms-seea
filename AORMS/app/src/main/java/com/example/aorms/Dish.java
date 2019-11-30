
package com.example.aorms;

import android.graphics.drawable.Drawable;

public class Dish {
    String name;
    Drawable img;
    String type;

    public Dish(String n, String t){
        this.name = n;
        this.type = t;
    }

    public Dish() {

    }

    public String getName() {
        return name;
    }


    public String getType() {
        return type;
    }
}