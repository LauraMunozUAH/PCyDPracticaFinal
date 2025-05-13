package com.example.pcydpracticafinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.io.IOException;

public class MainApplication extends Application {
    ApocalipsisLogs logger = ApocalipsisLogs.getInstancia();

    @Override
    public void start(Stage stage) throws IOException {
        logger.registrarEvento("Inicio del arranque de la ventana del menú inicial");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-inicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 340);
        stage.setTitle("Menú Inicial");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}