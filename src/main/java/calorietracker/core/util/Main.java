package calorietracker.core.util;

public class Main {
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
