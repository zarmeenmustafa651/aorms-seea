package com.example.aorms;

public class Ingredient {
    String name;
    int quantity;
    int threashold;

    Ingredient()
    {

    }

    public Ingredient(String name, int quantity, int threashold) {
        this.name = name;
        this.quantity = quantity;
        this.threashold = threashold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThreashold() {
        return threashold;
    }

    public void setThreashold(int threashold) {
        this.threashold = threashold;
    }
}
