package com.example.pcydpracticafinal;

public class Humano extends Thread {
    private final String id;
    private final Refugio refugio;
    private final ZonaRiesgo[] zonasRiesgo;
    private final Tunel[] tuneles;
    private boolean marcado = false;

    public Humano(String id, Refugio refugio, ZonaRiesgo[] zonasRiesgo, Tunel[] tuneles) {
        this.id = id;
        this.refugio = refugio;
        this.zonasRiesgo = zonasRiesgo;
        this.tuneles = tuneles;
    }


    public String getID() {
        return id;
    }

    @Override
    public void run() {
        try {
            while (true){
                refugio.accederZonaComun(this);
                int tunelacc =  (int) (1 + Math.random() * 3);
                tuneles[tunelacc].accederTunel(this,true); //true, porque salimos

                zonasRiesgo[tunelacc].entrarZonaRHumano(this);
                System.out.println("El humano " + id + " está buscando comida en la zona de riesgo" + tunelacc);
                int tiempoZR = (int) (3000 + Math.random() * 2000);
                sleep(tiempoZR);


            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
