package com.example.pcydpracticafinal;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.rmi.Naming;
import java.util.ResourceBundle;

public class ClienteInfoController {

    private InfoRemoto info;
    private Stage stage;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField HumanosZona1;
    @FXML
    private TextField HumanosZona2;
    @FXML
    private TextField HumanosZona3;
    @FXML
    private TextField HumanosZona4;
    @FXML
    private TextField ListaRefugio;
    @FXML
    private TextField ListaTunel1;
    @FXML
    private TextField ListaTunel2;
    @FXML
    private TextField ListaTunel3;
    @FXML
    private TextField ListaTunel4;
    @FXML
    private TextField ZombisLetales;
    @FXML
    private TextField ZombisZona1;
    @FXML
    private AnchorPane ZombisZona2;
    @FXML
    private TextField ZombisZona3;
    @FXML
    private TextField ZombisZona4;
    @FXML
    private Button botonPausar;

    @FXML
    public void initialize() {
        try {
            InterfaceInfoRemoto info = (InterfaceInfoRemoto) Naming.lookup("rmi://localhost/InfoRemota");

            Thread actualizador = new Thread(() -> {
                while  (true) {
                    Platform.runLater(this::actualizarVista);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {}
                }
            });
            //actualizador.setDaemon(true);
            actualizador.start();

        }  catch (Exception e) {
            System.out.println("Excepción : " + e.getMessage());
            e.printStackTrace();
        }



        assert HumanosZona1 != null : "fx:id=\"HumanosZona1\" was not injected: check your FXML file 'Informacion.fxml'.";
        assert HumanosZona2 != null : "fx:id=\"HumanosZona2\" was not injected: check your FXML file 'Informacion.fxml'.";
        assert HumanosZona3 != null : "fx:id=\"HumanosZona3\" was not injected: check your FXML file 'Informacion.fxml'.";
        assert HumanosZona4 != null : "fx:id=\"HumanosZona4\" was not injected: check your FXML file 'Informacion.fxml'.";
        assert ListaRefugio != null : "fx:id=\"ListaRefugio\" was not injected: check your FXML file 'Informacion.fxml'.";
        assert ListaTunel1 != null : "fx:id=\"ListaTunel1\" was not injected: check your FXML file 'Informacion.fxml'.";
        assert ListaTunel2 != null : "fx:id=\"ListaTunel2\" was not injected: check your FXML file 'Informacion.fxml'.";
        assert ListaTunel3 != null : "fx:id=\"ListaTunel3\" was not injected: check your FXML file 'Informacion.fxml'.";
        assert ListaTunel4 != null : "fx:id=\"ListaTunel4\" was not injected: check your FXML file 'Informacion.fxml'.";
        assert ZombisLetales != null : "fx:id=\"ZombisLetales\" was not injected: check your FXML file 'Informacion.fxml'.";
        assert ZombisZona1 != null : "fx:id=\"ZombisZona1\" was not injected: check your FXML file 'Informacion.fxml'.";
        assert ZombisZona2 != null : "fx:id=\"ZombisZona2\" was not injected: check your FXML file 'Informacion.fxml'.";
        assert ZombisZona3 != null : "fx:id=\"ZombisZona3\" was not injected: check your FXML file 'Informacion.fxml'.";
        assert ZombisZona4 != null : "fx:id=\"ZombisZona4\" was not injected: check your FXML file 'Informacion.fxml'.";
        assert botonPausar != null : "fx:id=\"botonPausar\" was not injected: check your FXML file 'Informacion.fxml'.";

    }

    public void setStage(Stage s){
        this.stage = s;
    }
}
