package calorietracker.core.data;

import java.util.*;

/**
 * Represents a meal which is a collection of food items.
 */
public class Meal {
    private String name;  // The name of the meal

    private List<FoodItem> foodItems; // The list of food items in the meal

    /**
     * Constructs a calorietracker.core.data.Meal with a given name.
     *
     * @param name The name of the meal.
     */
    public Meal(String name) {
        this.name = name; // Set the name of the meal
        this.foodItems = new ArrayList<>(); // Initialize the list of food items
    }

    /**
     * Gets the name of the meal.
     *
     * @return The name of the meal.
     */
    public String getName() {
        return name; // Return the name of the meal
    }

    /**
     * Adds a food item to the meal.
     *
     * @param foodItem The food item to be added.
     */
    public void addFoodItem(FoodItem foodItem) {
        foodItems.add(foodItem); // Add the food item to the list
    }

    /**
     * Gets the list of food items in the meal.
     *
     * @return The list of food items.
     */
    public List<FoodItem> getFoodItems() {

        return foodItems; // Return the list of food items
    }

    /**
     * Returns a string representation of the meal, which includes its name and the list of food items.
     *
     * @return A string representation of the meal.
     */
    @Override
    public String toString() {
        // Sort foodItems by calorie count
        Collections.sort(foodItems, new Comparator<FoodItem>() {
            @Override
            public int compare(FoodItem o1, FoodItem o2) {
                return Integer.compare(o1.getCalorieCount(), o2.getCalorieCount());
            }
        });

        StringBuilder sb = new StringBuilder(name + ": "); // Start with the meal name
        for (FoodItem item : foodItems) {  // For each food item in the meal
            sb.append(item.toString()).append(", "); // Append the food item's string representation
        }
        // Return the string, removing the final trailing comma and space
        return sb.substring(0, sb.length() - 2);
    }

    /**
     * Compares this meal with another object for equality based on the meal's name.
     *
     * @param o The object to compare this meal against.
     * @return true if the given object represents a meal equivalent to this meal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check for reference equality
        if (o == null || getClass() != o.getClass()) return false; // Check for null and ensure the object is a calorietracker.core.data.Meal

        Meal meal = (Meal) o; // Cast the object to calorietracker.core.data.Meal

        // Check for name equality
        return Objects.equals(name, meal.name);
    }
}
