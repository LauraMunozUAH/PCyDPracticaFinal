package com.example.pcydpracticafinal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SubAreaInsegura {
    //private final List<Humano> humanos = new ArrayList<>();
    ListaThreads humanos, zombis;
    //private final List<Zombi> zombis = new ArrayList<>();

    public void setTextField(JTextField TextHumanos, JTextField TextZombis) {
        humanos = new ListaThreads(TextHumanos);
        zombis = new ListaThreads(TextZombis);
    }
    public synchronized void entrarZonaRHumano(Humano humano) {
        humanos.add(humano);
    }
    public synchronized void salirZonaRHumano(Humano humano) {
        humanos.remove(humano);
    }
    public synchronized void entrarZonaRZombi(Zombi zombi) {
        zombis.add(zombi);
    }
    public synchronized void salirZonaRZombi(Zombi zombi) {
        zombis.remove(zombi);
    }
    public List<Humano> getListaHumanos() {
        return humanos;
    }
}
