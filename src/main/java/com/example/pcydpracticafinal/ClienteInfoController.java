package com.example.pcydpracticafinal;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ClienteInfoController {

    private InterfaceInfoRemoto info;
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
    private TextField ZombisZona2;
    @FXML
    private TextField ZombisZona3;
    @FXML
    private TextField ZombisZona4;
    @FXML
    private Button botonPausar;
    private TextField[] Listatuneles;
    private TextField[] ListahumanosZonasR;
    private TextField[] ListazombisZonasR;
    private boolean pausado = false;

    @FXML
    public void initialize() {

        Listatuneles = new TextField[] { ListaTunel1, ListaTunel2, ListaTunel3, ListaTunel4 };
        ListahumanosZonasR = new TextField[] { HumanosZona1, HumanosZona2, HumanosZona3, HumanosZona4 };
        ListazombisZonasR = new TextField[] { ZombisZona1, ZombisZona2, ZombisZona3, ZombisZona4};
        try {
            InterfaceInfoRemoto info = (InterfaceInfoRemoto) Naming.lookup("rmi://localhost/InfoRemoto");

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

    private void actualizarVista() {
        try {
            ListaRefugio.setText(String.valueOf(info.getHumanosRefugio()));

            int[] tuneles = info.getHumanosTuneles();
            int[] humanoszonasR = info.getHumanosZonasRiesgo();
            int[] zombiszonasR = info.getZombisZonasRiesgo();

            for (int i = 0; i < 4; i++) {
                Listatuneles[i].setText(String.valueOf(tuneles[i]));
                ListahumanosZonasR[i].setText(String.valueOf(humanoszonasR[i]));
                ListazombisZonasR[i].setText(String.valueOf(zombiszonasR[i]));
            }

            ZombisLetales.setText(String.join("\n", info.getZombisLetales()));

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onPausarReanudar() {
        try {
            if (pausado) {
                info.reanudar();
                botonPausar.setText("Detener ejecución");
            } else {
                info.pausar();
                botonPausar.setText("Reanudar ejecución");
            }
            pausado = !pausado;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
