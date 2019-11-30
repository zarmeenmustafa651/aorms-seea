package com.example.aorms;

public class UpdateIngredient {
    String key;
    DishDetail dishDetail;
    Ingredient ingredientDetail;

    public UpdateIngredient(String key, DishDetail dishDetail, Ingredient ingredientDetail) {
        this.key = key;
        this.dishDetail = dishDetail;
        this.ingredientDetail = ingredientDetail;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public DishDetail getDishDetail() {
        return dishDetail;
    }

    public void setDishDetail(DishDetail dishDetail) {
        this.dishDetail = dishDetail;
    }

    public Ingredient getIngredientDetail() {
        return ingredientDetail;
    }

    public void setIngredientDetail(Ingredient ingredientDetail) {
        this.ingredientDetail = ingredientDetail;
    }
}
