package com.example.pcydpracticafinal;

import javax.swing.*;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class SubAreaInsegura {
    //private final List<Humano> humanos = new ArrayList<>();
    ListaThreads humanos, zombis;
    //private final List<Zombi> zombis = new ArrayList<>();

    public void setTextField(TextField TextHumanos, TextField TextZombis) {
        humanos = new ListaThreads(TextHumanos);
        zombis = new ListaThreads(TextZombis);
    }
    public synchronized void entrarZonaRHumano(Humano humano) {
        humanos.meter(humano);
    }
    public synchronized void salirZonaRHumano(Humano humano) {
        humanos.sacar(humano);
    }
    public synchronized void entrarZonaRZombi(Zombi zombi) {
        zombis.meter(zombi);
    }
    public synchronized void salirZonaRZombi(Zombi zombi) {
        zombis.sacar(zombi);
    }
    public ListaThreads getListaHumanos() {
        return humanos;
    }
}
