package Model;

import java.util.ArrayList;

public class Cafe extends Place{


    private ArrayList<FoodCategory> foodCategories = new ArrayList<>();

    public ArrayList<FoodCategory> getFoodCategories() {
        return foodCategories;
    }

    public void setFoodCategories(ArrayList<FoodCategory> foodCategories) {
        this.foodCategories = foodCategories;
    }
}
