package com.example.pcydpracticafinal;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ApocalipsisController implements Initializable {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField ListaDescanso;
    @FXML
    private TextField ListaComedor;
    @FXML
    private TextField ListaZonaComun;
    @FXML
    private TextField SalidaTunel1;
    @FXML
    private TextField EntradaTunel1;
    @FXML
    private TextField Tunel1;
    @FXML
    private TextField SalidaTunel2;
    @FXML
    private TextField EntradaTunel2;
    @FXML
    private TextField Tunel2;
    @FXML
    private TextField SalidaTunel3;
    @FXML
    private TextField EntradaTunel3;
    @FXML
    private TextField Tunel3;
    @FXML
    private TextField SalidaTunel4;
    @FXML
    private TextField EntradaTunel4;
    @FXML
    private TextField Tunel4;
    @FXML
    private TextField ZombisZona1;
    @FXML
    private TextField HumanosZona1;
    @FXML
    private TextField ZombisZona2;
    @FXML
    private TextField HumanosZona2;
    @FXML
    private TextField ZombisZona3;
    @FXML
    private TextField HumanosZona3;
    @FXML
    private TextField ZombisZona4;
    @FXML
    private TextField HumanosZona4;
    @FXML
    private Button botonInformacion;
    private Stage stage;
    private boolean partidapausa = false;
    private ApocalipsisLogs logger = ApocalipsisLogs.getInstancia();
    
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        logger.registrarEvento("Inicialización en ejecución del controlador de parámetros\n");

        assert EntradaTunel1 != null : "fx:id=\"EntradaTunel1\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert EntradaTunel2 != null : "fx:id=\"EntradaTunel2\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert EntradaTunel3 != null : "fx:id=\"EntradaTunel3\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert EntradaTunel4 != null : "fx:id=\"EntradaTunel4\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert HumanosZona1 != null : "fx:id=\"HumanosZona1\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert HumanosZona2 != null : "fx:id=\"HumanosZona2\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert HumanosZona3 != null : "fx:id=\"HumanosZona3\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert HumanosZona4 != null : "fx:id=\"HumanosZona4\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert ListaComedor != null : "fx:id=\"ListaComedor\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert ListaDescanso != null : "fx:id=\"ListaDescanso\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert ListaZonaComun != null : "fx:id=\"ListaZonaComun\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert SalidaTunel1 != null : "fx:id=\"SalidaTunel1\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert SalidaTunel2 != null : "fx:id=\"SalidaTunel2\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert SalidaTunel3 != null : "fx:id=\"SalidaTunel3\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert SalidaTunel4 != null : "fx:id=\"SalidaTunel4\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert Tunel1 != null : "fx:id=\"Tunel1\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert Tunel2 != null : "fx:id=\"Tunel2\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert Tunel3 != null : "fx:id=\"Tunel3\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert Tunel4 != null : "fx:id=\"Tunel4\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert ZombisZona1 != null : "fx:id=\"ZombisZona1\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert ZombisZona2 != null : "fx:id=\"ZombisZona2\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert ZombisZona3 != null : "fx:id=\"ZombisZona3\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert ZombisZona4 != null : "fx:id=\"ZombisZona4\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert botonInformacion != null : "fx:id=\"botonInformacion\" was not injected: check your FXML file 'Apocalipsis.fxml'.";

        new Thread(()-> {

            Paso paso = new Paso();
            ZonaRiesgo zonaRiesgo = new ZonaRiesgo();
            zonaRiesgo.getSubAreas().getFirst().setTextField(HumanosZona1, ZombisZona1);
            zonaRiesgo.getSubAreas().get(1).setTextField(HumanosZona2, ZombisZona2);
            zonaRiesgo.getSubAreas().get(2).setTextField(HumanosZona3, ZombisZona3);
            zonaRiesgo.getSubAreas().get(3).setTextField(HumanosZona4, ZombisZona4);

            Tunel[] tuneles = { new Tunel(0, SalidaTunel1, EntradaTunel1, Tunel1), new Tunel(1, SalidaTunel2, EntradaTunel2, Tunel2),
                    new Tunel(2, SalidaTunel3, EntradaTunel3, Tunel3), new Tunel(3, SalidaTunel4, EntradaTunel4, Tunel4) };

            Refugio refugio = new Refugio(ListaComedor, ListaDescanso, ListaZonaComun, tuneles);

            try {
                InfoRemoto info = new InfoRemoto(refugio, zonaRiesgo, paso);
                LocateRegistry.createRegistry(1099);

                Naming.rebind("rmi://localhost/InfoRemoto", info);
                logger.registrarEvento("Servidor RMI listo.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }

            int area =  (int) (1 + Math.random() * 3);
            Zombi pacienteCero = new Zombi("Z0000", zonaRiesgo, area, paso);
            pacienteCero.start();

            for (int i = 1; i <= 10; i++) {
                String id = String.format("H%04d", i);
                Humano h = new Humano(id, refugio, zonaRiesgo, tuneles, paso);
                h.start();
                try {
                    Thread.sleep(500 + (int) Math.random() * 1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void setStage(Stage s){
        this.stage = s;
    }

    @FXML
    public void onInformacionBotonClick() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Informacion.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 947, 674);
            stage.setTitle("Apocalipsis Zombi - Remoto ");
            stage.setScene(scene);
            ClienteInfoController p = fxmlLoader.getController();
            p.setStage(stage);
            //stage.setMaximized(true);
            stage.setResizable(false);
            stage.show();
            logger.registrarEvento("Inicio del arranque de la ventana de Nueva Partida\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}