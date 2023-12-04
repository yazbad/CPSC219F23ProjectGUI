package calorietracker.core.data;

/**
 * Represents a food item with a name and a calorie count.
 * Implements Comparable to allow sorting by calorie count.
 */
public class FoodItem implements Comparable<FoodItem>{
    private String name; // The name of the food item
    private int calorieCount; // The calorie count of the food item

    /**
     * Constructs a new calorietracker.core.data.FoodItem with the specified name and calorie count.
     *
     * @param name The name of the food item.
     * @param calorieCount The calorie count of the food item.
     */
    public FoodItem(String name, int calorieCount) {
        this.name = name;
        this.calorieCount = calorieCount;
    }

    /**
     * Gets the name of the food item.
     *
     * @return The name of the food item.
     */
    public String getName() {

        return name;
    }

    /**
     * Gets the calorie count of the food item.
     *
     * @return The calorie count of the food item.
     */
    public int getCalorieCount() {

        return calorieCount;
    }

    /**
     * Returns a string representation of the food item, including its name and calorie count.
     *
     * @return A string representation of the food item.
     */
    @Override
    public String toString() {

        return name + ": " + calorieCount + " calories";
    }

    /**
     * Compares this food item with another food item for order based on calorie count.
     *
     * @param other The other food item to compare against.
     * @return A negative integer, zero, or a positive integer as this food item
     *         is less than, equal to, or greater than the specified food item.
     */
    @Override
    public int compareTo(FoodItem other) {
        // Compare calorieCount values
        return Integer.compare(this.calorieCount, other.calorieCount);
    }

    /**
     * Indicates whether some other object is "equal to" this one, based on name and calorie count.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check for reference equality
        if (o == null || getClass() != o.getClass()) return false; // Ensure correct type and non-null

        FoodItem foodItem = (FoodItem) o; // Typecast to calorietracker.core.data.FoodItem for comparison
        // Check for value equality based on calorieCount and name
        if (getCalorieCount() != foodItem.getCalorieCount()) return false;
        return getName() != null ? getName().equals(foodItem.getName()) : foodItem.getName() == null;
    }

    /**
     * Returns a hash code value for the food item, considering both name and calorie count.
     *
     * @return A hash code value for this food item.
     */
    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getCalorieCount(); // Use 31 as a multiplier for hash code calculation
        return result;
    }
}
