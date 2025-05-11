package com.example.pcydpracticafinal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Humano extends Thread {
    private final String id;
    private final Refugio refugio;
    private final ZonaRiesgo zonasRiesgo;
    private final Tunel[] tuneles;
    private boolean marcado = false;
    boolean muerto= false;
    private boolean esAtacado = false;
    private Paso paso;

    Lock cerrojo = new ReentrantLock();
    Condition cond = cerrojo.newCondition();

    public Humano(String id, Refugio refugio, ZonaRiesgo zonasRiesgo, Tunel[] tuneles, Paso paso) {
        super.setName(id);
        this.id = id;
        this.refugio = refugio;
        this.zonasRiesgo = zonasRiesgo;
        this.tuneles = tuneles;
        this.paso = paso;
    }
    public String getID() {
        return id;
    }


    public synchronized void zombiAtaca(Zombi zombiAtacante) { //Si marcado es true el humano se salva, si es false se muere.
        int probabilidad = (int) (1 + Math.random() * 2);
        if (probabilidad != 1) { // Se salva en 2/3 de los casos
            marcado = true;  // Ha sido atacado, pero ha sobrevivido
        } else {
            muerto = true;
            zombiAtacante.setMuertes();//Aumenta el número de muertes del zombi atacante.
        }
    }

    @Override
    public void run() {
        try {
            while (!muerto){
                paso.mirar();
                refugio.accederZonaComun(this);
                int tunelacc =  (int) (1 + Math.random() * 3);
                refugio.accederTunel(tunelacc, this);
                //tuneles[tunelacc].accederTunel(this,true); //true, porque salimos al exterior

                try {
                    zonasRiesgo.getSubAreas().get(tunelacc).entrarZonaRHumano(this);
                    System.out.println("El humano " + id + " está buscando comida en la zona de riesgo" + tunelacc);
                    int tiempoZR = (int) (3000 + Math.random() * 2000);
                    sleep(tiempoZR);
                } catch (InterruptedException e) {
                    esAtacado = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                paso.mirar();
                cerrojo.lock();
                try {
                    while(esAtacado) {
                        cond.await();
                    }
                    if (muerto) { //Si lo mata
                        crearZombi(tunelacc);

                    } else { //Si no lo mata

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
                            marcado = false;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cerrojo.unlock();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean getEsAtacado() {
        return esAtacado;
    }

    public void setEsAtacado() {
        cerrojo.lock();
        try {
            esAtacado = false;
            cond.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrojo.unlock();
        }

    }

    private void crearZombi(int area) {

        String IDZombi = "Z" + id.substring(1);
        zonasRiesgo.getSubAreas().get(area).salirZonaRHumano(this);
        Zombi zombi = new Zombi(IDZombi, zonasRiesgo, area, paso);
        zombi.start();
    }
}
