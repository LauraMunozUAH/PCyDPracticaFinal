package com.example.pcydpracticafinal;

public class Humano extends Thread {
    private final String id;
    private final Refugio refugio;
    private final ZonaRiesgo zonasRiesgo;
    private final Tunel[] tuneles;
    private boolean marcado = false;

    public Humano(String id, Refugio refugio, ZonaRiesgo zonasRiesgo, Tunel[] tuneles) {
        this.id = id;
        this.refugio = refugio;
        this.zonasRiesgo = zonasRiesgo;
        this.tuneles = tuneles;
    }


    public String getID() {
        return id;
    }


    public boolean atacado() { //Si devuelve true el humano se salva, si devuelve false se muere.
        int probabilidad = (int) (1 + Math.random() * 2);
        if (probabilidad != 1) { // Se salva en 2/3 de los casos
            marcado = true;  // Ha sido atacado, pero ha sobrevivido
        }
        return marcado;
    }

    @Override
    public void run() {
        try {
            while (true){
                refugio.accederZonaComun(this);
                int tunelacc =  (int) (1 + Math.random() * 3);
                tuneles[tunelacc].accederTunel(this,true); //true, porque salimos al exterior

                zonasRiesgo.getSubAreas().get(tunelacc).entrarZonaRHumano(this);
                System.out.println("El humano " + id + " está buscando comida en la zona de riesgo" + tunelacc);
                int tiempoZR = (int) (3000 + Math.random() * 2000);
                sleep(tiempoZR);

                zonasRiesgo.getSubAreas().get(tunelacc).salirZonaRHumano(this);
                tuneles[tunelacc].accederTunel(this, false); //false, porque volvemos del exterior

                if (!marcado) {
                    refugio.incrementarComida(this, 2);
                }
                int tiempo = (int) (2000 + Math.random() * 2000);
                refugio.accederZonaDescanso(this, tiempo);
                refugio.accederComedor(this, 1); // tratar de coger comida si hay y sino se espera a que haya

                if (marcado) {
                    int tiempo2 = (int) (3000 + Math.random() * 2000);
                    refugio.accederZonaDescanso(this, tiempo2);
                    System.out.println("El humano " + id + " se ha recuperado.");
                    marcado = false; /* PREGUNTAR */
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
