package com.example.pcydpracticafinal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ApocalipsisController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}