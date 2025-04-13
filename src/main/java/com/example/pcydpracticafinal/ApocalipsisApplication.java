package com.example.pcydpracticafinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApocalipsisApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ApocalipsisApplication.class.getResource("Apocalipsis.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1283, 698);
        stage.setTitle("Apocalipsis Zombi");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}