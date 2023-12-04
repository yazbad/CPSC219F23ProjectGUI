package calorietracker.core.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CalorieController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}