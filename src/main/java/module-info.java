module calorietracker.calorietracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens calorietracker.calorietracker to javafx.fxml;
    exports calorietracker.calorietracker;
}