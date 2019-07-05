package com.bmi;

public class FoodModel {

    private String foodTime;
    private String foodDetails;

    public FoodModel(String foodTime, String foodDetails) {
        this.foodTime = foodTime;
        this.foodDetails = foodDetails;
    }

    public String getFoodDetails() {
        return foodDetails;
    }

    public void setFoodDetails(String foodDetails) {
        this.foodDetails = foodDetails;
    }

    public String getFoodTime() {
        return foodTime;
    }

    public void setFoodTime(String foodTime) {
        this.foodTime = foodTime;
    }

    public FoodModel() {
    }
}
