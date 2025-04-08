package com.example.pcydpracticafinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplicationController {
    @FXML
    public void onNuevaPartidaBotonClick() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Apocalipsis.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 700, 400);
            stage.setTitle("Bienvenido: ");
            stage.setScene(scene);
            ApocalipsisController p = fxmlLoader.getController();
            p.setStage(stage);
            stage.setMaximized(true);
            stage.setResizable(false);
            stage.show();
            //log.info("Inicio del arranque de la ventana de Nueva Partida");
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
