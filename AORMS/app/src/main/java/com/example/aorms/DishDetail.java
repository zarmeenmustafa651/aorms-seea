package com.example.aorms;

public class DishDetail {

    int dish_Id;
    String ingredient_name;
    int quantity;

    public DishDetail()
    {}

    public DishDetail(int dish_Id, String ingredient_name, int quantity) {
        this.dish_Id = dish_Id;
        this.ingredient_name = ingredient_name;
        this.quantity = quantity;
    }

    public int getDish_Id() {
        return dish_Id;
    }

    public void setDish_Id(int dish_Id) {
        this.dish_Id = dish_Id;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_id) {
        this.ingredient_name = ingredient_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
