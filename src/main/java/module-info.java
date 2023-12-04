module calorietracker.calorietracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens calorietracker.core to javafx.fxml;
    exports calorietracker.core.data;
    exports calorietracker.core.util;
    opens calorietracker.core.util to javafx.fxml;
    exports calorietracker.core.GUI;
    opens calorietracker.core.GUI to javafx.fxml;
}