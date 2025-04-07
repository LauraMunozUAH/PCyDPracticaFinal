package com.example.pcydpracticafinal;

import javafx.scene.control.TextField;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Refugio {

    private int comida = 0;
    ListaThreads Hcomedor, Hdescanso, Hzonacomun;
    private final Lock lockComida = new ReentrantLock();
    private final Condition hayComida = lockComida.newCondition();

    public Refugio (TextField TextComedor, TextField TextDescanso, TextField TextZcomun) {
        Hcomedor = new ListaThreads(TextComedor);
        Hdescanso = new ListaThreads(TextDescanso);
        Hzonacomun = new ListaThreads(TextZcomun);
    }

    public void incrementarComida(Humano humano, int cantidad) {
        lockComida.lock();
        try {
            comida += cantidad;
            hayComida.signalAll();
            System.out.println("El humano " + humano.getID() + " ha dejado dos piezas de comida.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockComida.unlock();
        }
    }
    public void accederComedor(Humano humano, int cantidad) {
        lockComida.lock();
        entrarComedorHumano(humano);
        try {
            while (comida == 0) {
                hayComida.await();
                System.out.println("El humano " + humano.getID() + " está esperando a que haya comida.");
            }
            comida -= cantidad;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lockComida.unlock();
            try {
                System.out.println("El humano " + humano.getID() + " está comiendo.");
                humano.sleep((int) (3000 + Math.random() * 2000));
                System.out.println("El humano " + humano.getID() + " sale del comedor.");
                salirComedorHumano(humano);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private synchronized void entrarComedorHumano(Humano humano) {
        Hcomedor.meter(humano);
    }
    private synchronized void salirComedorHumano(Humano humano) {
        Hcomedor.sacar(humano);
    }

    public void accederZonaDescanso(Humano humano, int tiempo) {
        try {
            entrarDescansoHumano(humano);
            System.out.println("El humano " + humano.getID() + " está descansando.");
            humano.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            salirDescansoHumano(humano);
        }
    }
    private synchronized void entrarDescansoHumano(Humano humano) {
        Hdescanso.meter(humano);
    }
    private synchronized void salirDescansoHumano(Humano humano) {
        Hdescanso.sacar(humano);
    }

    public void accederZonaComun(Humano humano) {
        try {
            entrarZonaComunHumano(humano);
            System.out.println("El humano " + humano.getID() + " está en la zona común.");
            int tiempo = (int) (1000 + Math.random() * 1000);
            humano.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            salirZonaComunHumano(humano);
            System.out.println("El humano " + humano.getID() + " se dirige al túnel.");
        }
    }

    private synchronized void entrarZonaComunHumano(Humano humano) {
        Hzonacomun.meter(humano);
    }
    private synchronized void salirZonaComunHumano(Humano humano) {
        Hzonacomun.sacar(humano);
    }

}
