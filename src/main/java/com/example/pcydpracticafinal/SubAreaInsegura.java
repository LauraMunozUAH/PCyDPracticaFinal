package com.example.pcydpracticafinal;

import javax.swing.*;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class SubAreaInsegura {
    //private final List<Humano> humanos = new ArrayList<>();
    ListaThreads humanos, zombis;
    //private final List<Zombi> zombis = new ArrayList<>();
    private final ApocalipsisLogs logger = ApocalipsisLogs.getInstancia();

    public void setTextField(TextField TextHumanos, TextField TextZombis) {
        humanos = new ListaThreads(TextHumanos);
        zombis = new ListaThreads(TextZombis);
    }
    public synchronized void entrarZonaRHumano(Humano humano) {
        humanos.meter(humano);
        logger.registrarEvento("Humano " + humano.getName() + " entra en la zona insegura.");
    }
    public synchronized void salirZonaRHumano(Humano humano) {
        humanos.sacar(humano);
        logger.registrarEvento("Humano " + humano.getName() + " sale de la zona insegura.");
    }
    public synchronized void entrarZonaRZombi(Zombi zombi) {
        zombis.meter(zombi);
        logger.registrarEvento("Zombi " + zombi.getName() + " entra en la zona insegura.");
    }
    public synchronized void salirZonaRZombi(Zombi zombi) {
        zombis.sacar(zombi);
        logger.registrarEvento("Zombi " + zombi.getName() + " sale de la zona insegura.");
    }
    public ListaThreads getListaHumanos() {
        return humanos;
    }
    public ListaThreads getListaZombis() { return zombis;}
}
