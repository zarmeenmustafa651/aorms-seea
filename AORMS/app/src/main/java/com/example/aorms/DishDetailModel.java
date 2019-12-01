package com.example.aorms;

public class DishDetailModel {
    int dish_Id;
    int ingredient_id;
    int quantity;


    public DishDetailModel() {}
    public DishDetailModel(int dish_Id, int ingredient_id, int quantity) {
        this.dish_Id = dish_Id;
        this.ingredient_id = ingredient_id;
        this.quantity = quantity;
    }

    public int getDish_Id() {
        return dish_Id;
    }

    public void setDish_Id(int dish_Id) {
        this.dish_Id = dish_Id;
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
