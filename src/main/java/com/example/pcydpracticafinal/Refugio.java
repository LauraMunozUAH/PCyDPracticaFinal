package com.example.pcydpracticafinal;

import javafx.scene.control.TextField;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Refugio {
    ApocalipsisLogs logger = ApocalipsisLogs.getInstancia();
    private int comida = 0;
    ListaThreads Hcomedor, Hdescanso, Hzonacomun;
    Tunel[] tuneles;
    private final Lock lockComida = new ReentrantLock();
    private final Condition hayComida = lockComida.newCondition();
    private final CyclicBarrier tunel1 = new CyclicBarrier(3); // Para formar grupos de 3
    private final CyclicBarrier tunel2 = new CyclicBarrier(3);
    private final CyclicBarrier tunel3 = new CyclicBarrier(3);
    private final CyclicBarrier tunel4 = new CyclicBarrier(3);


    public Refugio (TextField TextComedor, TextField TextDescanso, TextField TextZcomun, Tunel[] tuneles) {
        Hcomedor = new ListaThreads(TextComedor);
        Hdescanso = new ListaThreads(TextDescanso);
        Hzonacomun = new ListaThreads(TextZcomun);
        this.tuneles = tuneles;
    }

    public void incrementarComida(Humano humano, int cantidad) {
        lockComida.lock();
        try {
            comida += cantidad;
            hayComida.signalAll();
            System.out.println("El humano " + humano.getID() + " ha dejado dos piezas de comida.");
            logger.registrarEvento("El humano " + humano.getID() + " ha dejado " + cantidad + " unidades de comida. Total en refugio: " + comida);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockComida.unlock();
        }
    }
    public void accederComedor(Humano humano, int cantidad) {
        lockComida.lock();
        logger.registrarEvento("El humano " + humano.getID() + " intenta acceder al comedor.");
        entrarComedorHumano(humano);
        try {
            while (comida == 0) {
                hayComida.await();
                System.out.println("El humano " + humano.getID() + " está esperando a que haya comida.");
                logger.registrarEvento("El humano " + humano.getID() + " está esperando comida.");
            }
            comida -= cantidad;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lockComida.unlock();
            try {
                System.out.println("El humano " + humano.getID() + " está comiendo.");
                logger.registrarEvento("El humano " + humano.getID() + " está comiendo. Queda comida: " + comida);
                humano.sleep((int) (3000 + Math.random() * 2000));
                System.out.println("El humano " + humano.getID() + " sale del comedor.");
                logger.registrarEvento("El humano " + humano.getID() + " sale del comedor.");
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
            logger.registrarEvento("El humano " + humano.getID() + " entra a descansar por " + tiempo + " ms.");
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
            logger.registrarEvento("El humano " + humano.getID() + " entra a la zona común.");
            int tiempo = (int) (1000 + Math.random() * 1000);
            humano.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            salirZonaComunHumano(humano);
            System.out.println("El humano " + humano.getID() + " se dirige al túnel.");
            logger.registrarEvento("El humano " + humano.getID() + " se dirige al túnel tras la zona común.");
        }
    }

    private synchronized void entrarZonaComunHumano(Humano humano) {
        Hzonacomun.meter(humano);
    }
    private synchronized void salirZonaComunHumano(Humano humano) {
        Hzonacomun.sacar(humano);
    }
    public void accederTunel(int tunel, Humano humano){
        try {
            switch (tunel){
                case (1):
                    tunel1.await();
                    System.out.println("Se ha formado un grupo de 3 humanos para salir al exterior por el tunel 1.");
                    tuneles[tunel-1].accederTunel(humano, true);//true, porque salimos al exterior
                    break;
                case (2):
                    tunel2.await();
                    System.out.println("Se ha formado un grupo de 3 humanos para salir al exterior por el tunel 2.");
                    tuneles[tunel-1].accederTunel(humano, true);
                    break;
                case (3):
                    tunel3.await();
                    System.out.println("Se ha formado un grupo de 3 humanos para salir al exterior por el tunel 3.");
                    tuneles[tunel-1].accederTunel(humano, true);
                    break;
                case (4):
                    tunel4.await();
                    System.out.println("Se ha formado un grupo de 3 humanos para salir al exterior  por el tunel 4.");
                    tuneles[tunel-1].accederTunel(humano, true);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
    }

    }



    // Funciones utilizadas para el RMI

    public int getCantHumanosRef() {
        return Hcomedor.getLista().size() + Hdescanso.getLista().size() + Hzonacomun.getLista().size();
    }

    public int[] getCantHumanosTuneles() {
        int[] humanosTuneles = new int[4];
        for (int i = 0; i < 4; i++) {
            Tunel tunel = tuneles[i];
            humanosTuneles[i] = tunel.getCantHumanosTunel();
        }
        return humanosTuneles;
    }
}
