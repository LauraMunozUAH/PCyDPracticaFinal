package com.example.pcydpracticafinal;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ApocalipsisController {

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
    private Button botonPausar;
    @FXML
    private Button botonJugar;
    @FXML
    private Button botonTerminar;
    private Stage stage;
    private boolean partidapausa;


    @FXML
    void initialize(URL url, ResourceBundle resourceBundle) {

        //log.debug("Inicialización en ejecución del controlador de parámetros\n");

        botonJugar.setDisable(true);
        botonPausar.setDisable(true);

        assert ListaDescanso != null : "fx:id=\"ListaDescanso\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert ListaComedor != null : "fx:id=\"ListaComedor\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert ListaZonaComun != null : "fx:id=\"ListaZonaComun\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert SalidaTunel1 != null : "fx:id=\"SalidaTunel1\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert EntradaTunel1 != null : "fx:id=\"EntradaTunel1\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert Tunel1 != null : "fx:id=\"Tunel1\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert SalidaTunel2 != null : "fx:id=\"SalidaTunel2\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert EntradaTunel2 != null : "fx:id=\"EntradaTunel2\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert Tunel2 != null : "fx:id=\"Tunel2\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert SalidaTunel3 != null : "fx:id=\"SalidaTunel3\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert EntradaTunel3 != null : "fx:id=\"EntradaTunel3\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert Tunel3 != null : "fx:id=\"Tunel3\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert SalidaTunel4 != null : "fx:id=\"SalidaTunel31\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert EntradaTunel4 != null : "fx:id=\"EntradaTunel31\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert Tunel4 != null : "fx:id=\"Tunel31\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert ZombisZona1 != null : "fx:id=\"ZombisZona1\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert HumanosZona1 != null : "fx:id=\"HumanosZona1\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert ZombisZona2 != null : "fx:id=\"ZombisZona2\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert HumanosZona2 != null : "fx:id=\"HumanosZona2\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert ZombisZona3 != null : "fx:id=\"ZombisZona3\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert HumanosZona3 != null : "fx:id=\"HumanosZona3\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert ZombisZona4 != null : "fx:id=\"ZombisZona4\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert HumanosZona4 != null : "fx:id=\"HumanosZona4\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert botonPausar != null : "fx:id=\"Pausar\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert botonJugar != null : "fx:id=\"Jugar\" was not injected: check your FXML file 'Apocalipsis.fxml'.";
        assert botonTerminar != null : "fx:id=\"Terminar\" was not injected: check your FXML file 'Apocalipsis.fxml'.";

    }

    public static void main(String args[]) {
        Refugio refugio = new Refugio();
        ZonaRiesgo zonaRiesgo = new ZonaRiesgo();
        zonaRiesgo.getSubAreas().get(0).setTextField(HumanosZona1, ZonaZombis1);
        Tunel[] tuneles = { new Tunel(0), new Tunel(1), new Tunel(2), new Tunel(3) };

        int area =  (int) (1 + Math.random() * 3);
        Zombi pacienteCero = new Zombi("Z0000", zonaRiesgo, area);
        pacienteCero.start();

        for (int i = 1; i <= 10000; i++) {
            String id = String.format("H%04d", i);
            Humano h = new Humano(id, refugio, zonaRiesgo, tuneles);
            h.start();
            try {
                Thread.sleep(500 + (int) Math.random() *1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setStage(Stage s){
        this.stage = s;
    }

    @FXML
    protected void onJugarButtonClick() {
        try {
            //log.debug("Ha comenzado la partida");
            System.out.println("Hemos llegado al tablero");

            partidapausa = false;
            botonJugar.setDisable(true);
            botonPausar.setDisable(false);


        } catch (Exception e) {
            // Manejo de la excepción: muestra un mensaje de error al usuario
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Se produjo un error al iniciar el juego");
            alert.setContentText("Detalles del error: " + e.getMessage());
            alert.showAndWait();
        }

    }



    @FXML
    public void onBotonTerminarClick(ActionEvent event) {
        stage.close();
        //log.debug("Se ha apretado el botón cerrar.");
    }

    @FXML
    public void onPausarButtonClick() {
        try {
            if (partidapausa){
                reanudarpartida();
            } else {
                pausarpartida();
            }

        } catch (Exception e) {
            System.err.println("Error al pulsar botón pausar/reanudar.");
            //log.error("Error al pulsar botón pausar/reanudar.");
            e.printStackTrace();
        }
    }

    public void pausarpartida() {
        try {
            //log.debug("Partida pausada.");
            partidapausa = true;
            botonPausar.setText("Reanudar");
            botonTerminar.setDisable(false);

        } catch (Exception e) {
            //log.error("Error al pulsar botón pausar.");
            System.err.println("Error al pulsar botón pausar.");
            e.printStackTrace();
        }
    }

    public void reanudarpartida() {
        try {
            //log.debug("Partida reanudada.");
            partidapausa = false;
            botonPausar.setText("Pausar");
            botonTerminar.setDisable(false);

        } catch (Exception e) {
            //log.error("Error al pulsar botón reanudar.");
            System.err.println("Error al pulsar botón reanudar.");
            e.printStackTrace();
        }
    }


}