package calorietracker.core.data;
import calorietracker.core.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;


public class CalorieTrackerTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {

        System.setOut(new PrintStream(outputStream));
    }
    @Test
    public void testFoodItemEquals() {
        FoodItem foodItem1 = new FoodItem("Apple", 50);
        FoodItem foodItem2 = new FoodItem("Apple", 50);
        FoodItem foodItem3 = new FoodItem("Banana", 100);

        assertEquals(foodItem1, foodItem2);
        assertNotEquals(foodItem1, foodItem3);
    }

    @Test
    public void testExerciseSessionEquals() {
        ExerciseSession session1 = new ExerciseSession("Running", 200);
        ExerciseSession session2 = new ExerciseSession("Running", 200);
        ExerciseSession session3 = new ExerciseSession("Swimming", 300);

        assertTrue(session1.equals(session2));
        assertFalse(session1.equals(session3));
    }

    @Test
    public void testMealAddFoodItem() {
        Meal meal = new Meal("Breakfast");
        FoodItem foodItem1 = new FoodItem("Cereal", 150);
        FoodItem foodItem2 = new FoodItem("Milk", 50);

        meal.addFoodItem(foodItem1);
        meal.addFoodItem(foodItem2);

        assertTrue(meal.getFoodItems().contains(foodItem1));
        assertTrue(meal.getFoodItems().contains(foodItem2));
    }

    @Test
    public void testMealEquals() {
        Meal meal1 = new Meal("Breakfast");
        Meal meal2 = new Meal("Breakfast");
        Meal meal3 = new Meal("Lunch");

        FoodItem foodItem1 = new FoodItem("Cereal", 150);
        FoodItem foodItem2 = new FoodItem("Milk", 50);

        meal1.addFoodItem(foodItem1);
        meal1.addFoodItem(foodItem2);

        meal2.addFoodItem(foodItem1);
        meal2.addFoodItem(foodItem2);

        assertTrue(meal1.equals(meal2));
        assertFalse(meal1.equals(meal3));
        assertFalse(meal2.equals(meal3));
    }


    @Test
    public void testSetCalorieGoal() {
        Menu menu = new Menu();
        int calorieGoal = 2000;
        menu.setCalorieGoal(calorieGoal);

        // After setting the calorie goal, you can check if it has been set correctly
        int retrievedCalorieGoal = menu.getCalorieGoal();

        assertEquals(calorieGoal, retrievedCalorieGoal);
    }
    @Test
    public void testCheckCalorieGoalExceed() {
        Menu menu = new Menu();
        int calorieGoal = 20;
        menu.setCalorieGoal(calorieGoal);

        FoodItem foodItem1 = new FoodItem("Apple", 50);
        FoodItem foodItem2 = new FoodItem("Banana", 100);
        menu.foodItems.add(foodItem1);
        menu.foodItems.add(foodItem2);

        ExerciseSession exerciseSession1 = new ExerciseSession("Running", 30);
        menu.exerciseSessions.add(exerciseSession1);

        int totalCalories = menu.calculateTotalCalories();
        int totalCaloriesBurned = menu.calculateTotalCaloriesBurned();
        int netCalories = totalCalories - totalCaloriesBurned;

        assertTrue(netCalories > calorieGoal);
    }
    @Test
    public void testCheckCalorieGoalWithin() {
        Menu menu = new Menu();
        int calorieGoal = 2000;
        menu.setCalorieGoal(calorieGoal);

        FoodItem foodItem1 = new FoodItem("Apple", 50);
        FoodItem foodItem2 = new FoodItem("Banana", 100);
        menu.foodItems.add(foodItem1);
        menu.foodItems.add(foodItem2);

        ExerciseSession exerciseSession1 = new ExerciseSession("Running", 200);
        menu.exerciseSessions.add(exerciseSession1);

        int totalCalories = menu.calculateTotalCalories();
        int totalCaloriesBurned = menu.calculateTotalCaloriesBurned();
        int netCalories = totalCalories - totalCaloriesBurned;

        assertTrue(netCalories <= calorieGoal);
    }
}