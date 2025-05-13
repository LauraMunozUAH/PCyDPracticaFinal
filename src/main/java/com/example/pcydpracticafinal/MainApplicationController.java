package com.example.pcydpracticafinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainApplicationController {
    private static final Logger log = LogManager.getLogger(MainApplicationController.class);
    @FXML
    public void onNuevaPartidaBotonClick() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Apocalipsis.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1283, 698);
            stage.setTitle("Bienvenido: ");
            stage.setScene(scene);
            ApocalipsisController p = fxmlLoader.getController();
            p.setStage(stage);
            //stage.setMaximized(true);
            stage.setResizable(false);
            stage.show();
            log.info("Inicio del arranque de la ventana de Nueva Partida");
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
