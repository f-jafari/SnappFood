package Model;

import java.util.ArrayList;

public class Cafe extends Place{

    public static ArrayList<Cafe> cafes = new ArrayList<>();


    private ArrayList<FoodCategory> foodCategories = new ArrayList<>();

    public ArrayList<FoodCategory> getFoodCategories() {
        return foodCategories;
    }

    public void setFoodCategories(ArrayList<FoodCategory> foodCategories) {
        this.foodCategories = foodCategories;
    }
}
