package com.example.pcydpracticafinal;

import java.util.ArrayList;
import java.util.List;

public class Zombi extends Thread {
    private final String id;
    private final ZonaRiesgo zonasRiesgo;
    private int muertes = 0;
    private int area;

    public Zombi(String id, ZonaRiesgo zonasRiesgo, int area) {
        super.setName(id);
        this.id = id;
        this.zonasRiesgo = zonasRiesgo;
        this.area = area;
    }

    @Override
    public void run() {
        try {
            while (true) {
                SubAreaInsegura areaSeleccionada = zonasRiesgo.getSubAreas().get(area);
                areaSeleccionada.entrarZonaRZombi(this);
                ArrayList<Thread> humanos = areaSeleccionada.getListaHumanos().getCopiaLista();

                if (!humanos.isEmpty()) {
                    int posicionAtacado = (int) (Math.random() * (humanos.size() - 1));
                    Humano humanoAtacado = (Humano) humanos.get(posicionAtacado);
                    System.out.println("El zombi " + id + " ataca al humano " + humanoAtacado.getName() + " (número de muertes: " + muertes + ")");
                    boolean sobrevive = humanoAtacado.atacado(); //Si devuelve true sobrevive, si devuelve false muere. Al ser llamado directamente desde zombi.
                    sleep((int) (500 + Math.random() * 1000));

                    if (!sobrevive) {
                        zonasRiesgo.convertirHaZ(this,humanoAtacado,area);
                    }

                }
                System.out.println("Zombi " + id + " está buscando comida.");
                sleep((int) (2000 + Math.random() * 1000));
                areaSeleccionada.salirZonaRZombi(this);
                area =  (int) (1 + Math.random() * 3);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setMuertes(){
        this.muertes+=1;
    }
}
