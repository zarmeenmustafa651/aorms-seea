package com.example.aorms;

public class SpecialOrder {
    private String dishName;
    private int imgId;
    private int quantity;
    public SpecialOrder(String dishName, int imgId , int quantity) {
        this.dishName= dishName;
        this.imgId = imgId;
        this.quantity=quantity;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

