package com.example.aorms;

public class AddDishIngredients {
    String name;
    int Quantity;

    public AddDishIngredients() {
    }

    public AddDishIngredients(String name, int quantity) {
        this.name = name;
        Quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}

