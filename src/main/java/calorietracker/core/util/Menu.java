package calorietracker.core.util;

import calorietracker.core.data.ExerciseSession;
import calorietracker.core.data.FoodItem;
import calorietracker.core.data.Meal;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;
// Yazeed Badr, Kirthik
// 2023-11-09
// Tutorial 5
/**
 * A simple calorie tracker menu program that allows users to add food items,
 * meals, and exercise sessions, and perform various operations related to
 * calorie tracking.
 */

public class Menu {
    // Lists to store different types of items the user can track
    public List<FoodItem> foodItems;
    private List<Meal> meals;
    public List<ExerciseSession> exerciseSessions;
    private int calorieGoal; // The user's daily calorie goal
    private Scanner scanner; // Scanner to read user input

    /**
     * Constructor initializes the collections and variables needed for the menu.
     */
    public Menu() {
        foodItems = new ArrayList<>();
        meals = new ArrayList<>();
        exerciseSessions = new ArrayList<>();
        calorieGoal = -1; // Default value indicating no goal set
        scanner = new Scanner(System.in);
    }

    /**
     * The main loop for the menu, which displays options and processes user input.
     */
    public void runMenu() {
        boolean quit = false;
        while (!quit) {
            for (int i = 0; i < 35; i++) {
                System.out.print('-');
            }
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Food Item");
            System.out.println("2. Add calorietracker.core.data.Meal");
            System.out.println("3. Add Exercise Session");
            System.out.println("4. Print All Food Items");
            System.out.println("5. Print All Meals");
            System.out.println("6. Print All Exercise Sessions");
            System.out.println("7. Calculate Total Caloric Intake for All Food Items");
            System.out.println("8. Calculate Total Caloric Burns for All Exercise Sessions");
            System.out.println("9. List Food Items Exceeding a Calorie Threshold");
            System.out.println("10. List Food Items Under a Calorie Threshold");
            System.out.println("11. Set Calorie Goal");
            System.out.println("12. Check Calorie Goal");
            System.out.println("13. Save Data to CSV file");
            System.out.println("14. Load Data from CSV File");
            System.out.println("0. Exit");
            for (int i = 0; i < 35; i++) {
                System.out.print('-');
            }
            System.out.print("\n");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addFoodItem();
                    break;
                case 2:
                    addMeal();
                    break;
                case 3:
                    addExerciseSession();
                    break;
                case 4:
                    printAllFoodItems();
                    break;
                case 5:
                    printAllMeals();
                    break;
                case 6:
                    printAllExerciseSessions();
                    break;
                case 7:
                    calculateTotalCalories();
                    break;
                case 8:
                    calculateTotalCaloriesBurned();
                    break;
                case 9:
                    listFoodItemsExceedingCalories();
                    break;
                case 10:
                    listFoodItemsUnderCalories();
                    break;
                case 11:
                    setCalorieGoal();
                    break;
                case 12:
                    checkCalorieGoal();
                    break;
                case 13:
                    saveDataToCSV();
                    break;
                case 14:
                    System.out.println("Enter the CSV file name to load data from:");
                    String loadFileName = scanner.nextLine();
                    loadDataFromCSV(loadFileName);
                    break;
                case 0:
                    quit = true;
                    System.out.println("Exiting menu, thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Method to read the Data and save it in a CSV file
     */
    public void saveDataToCSV() {
        System.out.println("Save Data to CSV File");
        System.out.println("Enter the file name (including the extension):");
        String fileName = scanner.nextLine();

        try (PrintWriter writer = new PrintWriter(fileName)) { // Try-with-resources to ensure PrintWriter is closed
            // Writing a header line to the CSV file.
            writer.println("Type, Name, Calorie Count/Calorie Burned, Associated Food Items");

            // First write all calorietracker.core.data.FoodItem records.
            for (FoodItem foodItem : foodItems) {
                writer.println("calorietracker.core.data.FoodItem, " + foodItem.getName() + ", " + foodItem.getCalorieCount());
            }

            // Then write all calorietracker.core.data.ExerciseSession records.
            for (ExerciseSession exerciseSession : exerciseSessions) {
                writer.println("calorietracker.core.data.ExerciseSession, " + exerciseSession.getName() + ", " + exerciseSession.getCalorieBurned());
            }

            // Finally, write all calorietracker.core.data.Meal records.
            for (Meal meal : meals) {
                // Calculate the total calorie count for the meal
                int mealCalories = meal.getFoodItems().stream().mapToInt(FoodItem::getCalorieCount).sum();
                // Write the meal data starting with meal name and total calories
                writer.print("calorietracker.core.data.Meal, " + meal.getName() + ", " + mealCalories);
                // Append each associated food item name to the CSV line
                for (FoodItem foodItem : meal.getFoodItems()) {
                    writer.print(", " + foodItem.getName());
                }
                // End the line for the current meal record
                writer.println();
            }
            // Inform the user that the data was saved successfully
            System.out.println("Data saved successfully to " + fileName);
        } catch (FileNotFoundException e) {  // Catch block to handle if the file path is invalid
            // Inform the user if there was an issue saving the data
            System.out.println("Error saving data to the file: " + fileName);
        }
    }

    /**
     * Loads data from a CSV file specified by the user.
     *
     * @param fileName The name of the file to load data from.
     */
    public void loadDataFromCSV(String fileName) {
        for (int i = 0; i < 35; i++) {
            System.out.print('-');
        }
        System.out.println("\nLoad Data from CSV File");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean headerSkipped = false; // Flag to skip the header line


            while ((line = br.readLine()) != null) { // Read the file line by line
                if (!headerSkipped) { // Check if the header has been skipped
                    headerSkipped = true; // Skip the header line
                    continue;
                }

                String[] parts = line.split(","); // Split the line by comma to get the data parts

                if (parts.length >= 3) {  // Check if the line has at least 3 parts (type, name, value)
                    String type = parts[0].trim(); // Trim to remove any leading/trailing spaces
                    String name = parts[1].trim(); // Trim to remove any leading/trailing spaces
                    int value = Integer.parseInt(parts[2].trim());  // Parse the value as an integer

                    switch (type) {
                        case "calorietracker.core.data.FoodItem":
                            foodItems.add(new FoodItem(name, value)); // Add a new food item
                            break;
                        case "calorietracker.core.data.ExerciseSession":
                            exerciseSessions.add(new ExerciseSession(name, value));  // Add a new exercise session
                            break;
                        case "calorietracker.core.data.Meal":
                            Meal meal = new Meal(name);
                            for (int i = 3; i < parts.length; i++) { // Start from index 3 to get associated food items
                                String foodItemName = parts[i].trim(); // Trim the food item name
                                // Find the food item by name and add it to the meal
                                foodItems.stream()
                                        .filter(item -> item.getName().equals(foodItemName))
                                        .findFirst().ifPresent(meal::addFoodItem);
                            }
                            meals.add(meal); // Add the complete meal
                            break;
                    }
                }
            }


            System.out.println("Data loaded successfully from " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName); // Handle the case where the file doesn't exist
        } catch (NumberFormatException e) {
            System.out.println("Error reading data from the file. Ensure the file format is correct."); // Handle parsing errors
        } catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage()); // Handle other IO errors
        }
    }

    /**
     * Prints all food items currently stored in the system.
     */
    public void printAllFoodItems() {
        for (int i = 0; i < 35; i++) {
            System.out.print('-');
        }
        System.out.print("\n");
        // Print the section title for food items
        System.out.println("FOOD ITEMS:");
        // Sort the foodItems list before printing
        Collections.sort(foodItems);
        // Iterate over the sorted list of food items
        for (FoodItem item : foodItems) {
            // Print each food item
            System.out.println(item);
        }
    }

    /**
     * Prints all meals currently stored in the system.
     */
    public void printAllMeals() {
        for (int i = 0; i < 35; i++) {
            System.out.print('-');
        }
        System.out.print("\n");
        // Print the section title for meals
        System.out.println("MEALS:");
        // Iterate over the list of meals
        for (Meal meal : meals) {
            System.out.println(meal);
        }
    }

    /**
     * Prints all exercise sessions currently stored in the system.
     */
    public void printAllExerciseSessions() {
        for (int i = 0; i < 35; i++) {
            System.out.print('-');
        }
        System.out.print("\n");
        System.out.println("EXERCISE SESSIONS:");
        // Sort the exerciseSessions list before printing by calorie burned in ascending order
        Collections.sort(exerciseSessions, Comparator.comparingInt(ExerciseSession::getCalorieBurned));
        // Iterate over the sorted list of exercise sessions
        for (ExerciseSession session : exerciseSessions) {
            System.out.println(session); // Print each exercise session
        }
    }

    /**
     * Method to add a food item to the foodItems ArrayList
     */
    public void addFoodItem() {
        for (int i = 0; i < 35; i++) {
            System.out.print('-');
        }
        System.out.print("\n");
        // Prompt the user to enter the name of the new food item
        System.out.println("Enter food item name:");
        String name = scanner.nextLine();
        // Prompt the user to enter the calorie count for the food item
        System.out.println("Enter calorie count:");
        int calories = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline character
        // Create a new calorietracker.core.data.FoodItem object with the provided name and calorie count
        FoodItem foodItem = new FoodItem(name, calories);
        foodItems.add(foodItem); // Add the new food item to the list of food items
        // Confirm to the user that the food item has been added successfully
        System.out.println("Food item added successfully!");
    }
    /**
     * Method to add an exercise session to the exerciseSessions ArrayList
     */
    public void addExerciseSession() {
        for (int i = 0; i < 35; i++) {
            System.out.print('-');
        }
        System.out.print("\n");
        // Prompt the user to enter the name of the new exercise session
        System.out.println("Enter exercise session name:");
        String name = scanner.nextLine();
        // Prompt the user to enter the number of calories burned in this session
        System.out.println("Enter calories burned:");
        int caloriesBurned = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline character

        // Create a new calorietracker.core.data.ExerciseSession object with the provided name and calories burned
        ExerciseSession session = new ExerciseSession(name, caloriesBurned);
        exerciseSessions.add(session); // Add the new session to the list of exercise sessions

        // Confirm to the user that the exercise session has been added successfully
        System.out.println("Exercise session added successfully!");
    }

    /**
     * Method to add a meal, including multiple food items, to the meals ArrayList
     */
    public void addMeal() {
        for (int i = 0; i < 35; i++) {
            System.out.print('-');
        }
        System.out.print("\n");
        // Prompt the user to enter the name of the new meal
        System.out.println("Enter meal name:");
        String name = scanner.nextLine();
        Meal meal = new Meal(name); // Create a new meal object with the given name

        // Ask the user for the number of food items to add to this meal
        System.out.println("How many food items to add to the meal?");
        int itemCount = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline character

        int itemsAdded = 0; // Keep track of successfully added items

        // Loop until the user has added the desired number of food items to the meal
        while (itemsAdded < itemCount) {
            System.out.println("Enter food item name:");
            String itemName = scanner.nextLine();

            // Find the food item by name
            FoodItem itemToAdd = foodItems.stream()
                    .filter(foodItem -> foodItem.getName().equalsIgnoreCase(itemName))
                    .findFirst()
                    .orElse(null); // Use orElse(null) to handle case where item is not found

            if (itemToAdd != null) { // Check if the food item was found
                meal.addFoodItem(itemToAdd); // Add the found food item to the meal
                itemsAdded++; // Only increment if the food item was successfully added
            } else {
                // Notify the user if the food item name entered was not found
                System.out.println("Food item not found. Please try again.");
            }
        }

        meals.add(meal); // Add the completed meal to the list of meals
        System.out.println("calorietracker.core.data.Meal added successfully!");  // Confirm successful addition to the user
    }

    /**
     * Calculates the total calories burned from all exercise sessions and prints the result.
     */
    public int calculateTotalCalories() {
        for (int i = 0; i < 35; i++) {
            System.out.print('-');
        }
        System.out.print("\n");
        // Calculate the total calories by summing up the calorie count from each food item
        int totalCalories = foodItems.stream().mapToInt(FoodItem::getCalorieCount).sum();
        // Output the total calorie count to the console
        System.out.println("Total caloric intake from all food items: " + totalCalories);
        // Return the calculated total calorie count
        return totalCalories;
    }

    /**
     * Calculates the total calories burned from all exercise sessions and prints the result.
     *
     * @return Total calorie burn from all exercise sessions.
     */
    public int calculateTotalCaloriesBurned() {
        for (int i = 0; i < 35; i++) {
            System.out.print('-');
        }
        System.out.print("\n");
        // Calculate the total calories burned by summing up the calories burned from each exercise session
        int totalCaloriesBurned = exerciseSessions.stream().mapToInt(ExerciseSession::getCalorieBurned).sum();
        // Output the total calories burned to the console
        System.out.println("Total caloric burn from all exercise sessions: " + totalCaloriesBurned);
        // Return the calculated total calories burned count
        return totalCaloriesBurned;
    }

    /**
     * Lists all food items exceeding a certain calorie threshold specified by the user.
     */
    public void listFoodItemsExceedingCalories() {
        for (int i = 0; i < 35; i++) {
            System.out.print('-');
        }
        System.out.print("\n");
        System.out.println("Enter the calorie threshold:");
        int threshold = scanner.nextInt(); // Read the threshold value from the user
        scanner.nextLine(); // Consume newline
        // Filter and print food items exceeding the given threshold
        foodItems.stream()
                .filter(item -> item.getCalorieCount() > threshold)
                .forEach(item -> System.out.println(item));
    }

    /**
     * Lists all food items under a certain calorie threshold specified by the user.
     */
    public void listFoodItemsUnderCalories() {
        for (int i = 0; i < 35; i++) {
            System.out.print('-');
        }
        System.out.print("\n");
        System.out.println("Enter the calorie threshold:");
        int threshold = scanner.nextInt(); // Read the threshold value from the user
        scanner.nextLine(); // Consume newline

        // Filter and print food items under the given threshold
        foodItems.stream()
                .filter(item -> item.getCalorieCount() < threshold)
                .forEach(item -> System.out.println(item));
    }


    /**
     * Prompts the user to set a daily calorie goal.
     */
    public void setCalorieGoal() {
        for (int i = 0; i < 35; i++) {
            System.out.print('-');
        }
        System.out.print("\n");
        System.out.println("Enter your calorie goal for the day:");
        calorieGoal = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Calorie goal set to " + calorieGoal); // Confirm the new calorie goal to the user
    }

    /**
     * Sets the calorie goal to a specified value.
     *
     * @param calorieGoal The calorie goal to be set.
     */
    public void setCalorieGoal(int calorieGoal) {
        // Set the calorieGoal to the provided value
        this.calorieGoal = calorieGoal;
    }

    /**
     * Checks if the current net calorie intake meets the calorie goal and prints a message.
     */
    public void checkCalorieGoal() {
        for (int i = 0; i < 35; i++) {
            System.out.print('-');
        }
        System.out.print("\n");
        if (calorieGoal == -1) { // Check if the calorie goal has not been set
            System.out.println("No calorie goal set."); // Inform the user that no goal has been set
            return;
        }
        // Calculate the total calorie intake and the total calories burned
        int totalCalories = foodItems.stream().mapToInt(FoodItem::getCalorieCount).sum();
        int totalCaloriesBurned = exerciseSessions.stream().mapToInt(ExerciseSession::getCalorieBurned).sum();
        int netCalories = totalCalories - totalCaloriesBurned; // Net calories is intake minus burned

        // Print the user's net calorie intake and the calorie goal
        System.out.println("Your net calorie intake is: " + netCalories);
        System.out.println("Your calorie goal is: " + calorieGoal);

        // Check if the user has met their calorie goal and print an appropriate message
        if (netCalories > calorieGoal) {
            System.out.println("You have exceeded your calorie goal by " + (netCalories - calorieGoal) + " calories.");
        } else {
            System.out.println("You are within your calorie goal by " + (calorieGoal - netCalories) + " calories.");
        }
    }

    /**
     * Retrieves the current calorie goal.
     *
     * @return The current calorie goal.
     */
    public int getCalorieGoal() {
        // Return the current calorie goal
        return calorieGoal;
    }

    public static class Main {
        /**
         * The main method, which serves as the entry point for the Java application.
         *
         * @param args Command-line arguments passed to the application.
         */
        public static void main(String[] args) {
            Menu menu = new Menu(); // Create a new Menu object

            // Check if a filename was provided as a command-line argument
            if (args.length > 0) {
                String fileName = args[0]; // Get the filename from the command-line argument
                menu.loadDataFromCSV(fileName); // Load data from the CSV file into the Menu
                System.out.println("Loaded data from " + fileName);  // Print confirmation message
            }
            menu.runMenu(); // Start the menu-driven interface of the application
        }
    }
}

