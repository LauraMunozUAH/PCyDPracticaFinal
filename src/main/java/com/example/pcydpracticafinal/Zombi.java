package com.example.pcydpracticafinal;

import java.util.List;

public class Zombi extends Thread {
    private final String id;
    private final ZonaRiesgo[] zonasRiesgo;
    private boolean marcado = false;
    private int muertes = 0;

    public Zombi(String id, ZonaRiesgo[] zonasRiesgo) {
        this.id = id;
        this.zonasRiesgo = zonasRiesgo;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int zonaRacc =  (int) (1 + Math.random() * 3);
                zonasRiesgo[zonaRacc].entrarZonaRZombi(this);
                List<Humano> humanos = zonasRiesgo[zonaRacc].getListaHumanos();
                if (humanos.size() != 0) {
                    int posicionAtacado = (int) (Math.random() * (humanos.size() - 1));
                    Humano humanoAtacado = humanos.get(posicionAtacado);
                    System.out.println("El zombi " + id + "ataca al humano " + humanoAtacado.getID() + "(número de muertes: " + muertes + ")");
                    boolean sobrevive = humanoAtacado.atacado(); //Si devuelve true sobrevive, si devuelve false muere. Al ser llamado directamente desde zombi.
                    sleep((int) (500 + Math.random() * 1000));

                    if (!sobrevive) {
                        zonasRiesgo[zonaRacc].convertirHaZ(humanoAtacado);
                    }

                }
                System.out.println("Zombi " + id + " está buscando comida.");
                sleep((int) (2000 + Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
