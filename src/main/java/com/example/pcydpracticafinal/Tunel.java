package com.example.pcydpracticafinal;

import javafx.scene.control.TextField;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tunel {
    private final ApocalipsisLogs logger = ApocalipsisLogs.getInstancia();
    private final int id;
    //private final Semaphore semTunel = new Semaphore(1); // Solo 1 humano en el túnel a la vez
    private final Lock lock = new ReentrantLock();
    private final Condition puedeCruzar = lock.newCondition();

    // Listas para humanos esperando y en el túnel
    ListaThreads HesperandoSalir, HesperandoEntrar, HEnTunel;

    public Tunel(int id, TextField TextSalir, TextField TextEntrar, TextField TextEnTunel) {
        this.id = id;
        HesperandoSalir = new ListaThreads(TextSalir);
        HesperandoEntrar = new ListaThreads(TextEntrar);
        HEnTunel = new ListaThreads(TextEnTunel);
    }
    public void accederTunel(Humano humano, boolean salida){ //TRUE --> Salida del refugio, FALSE --> Entrada al refugio
        if(salida){
            entrarListaEsperandoSalir(humano);
            logger.registrarEvento("El humano "+ humano.getName()+ " está esperando para salir del túnel "+ id);
            atravesarTunel(humano, salida);
        } else {
            entrarListaEsperandoEntrar(humano);
            logger.registrarEvento("El humano " + humano.getID() + " está en la cola para entrar.");
            atravesarTunel(humano, salida);
        }
    }

    private void atravesarTunel(Humano humano, boolean salida) {
        lock.lock();
        try {
            while (salida && !HesperandoEntrar.getLista().isEmpty()){
                puedeCruzar.await();
            }

            if (salida) {
                salirListaEsperandoSalir(humano);
            } else {
                salirListaEsperandoEntrar(humano);
            }
            entrarListaTunel(humano);
            logger.registrarEvento("El humano " + humano.getID() + " está atravesando el túnel " + id);
            Thread.sleep(1000);
            salirListaTunel(humano);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (!salida) {
                puedeCruzar.signalAll();
            }
            lock.unlock();
        }
    }

    private synchronized void entrarListaEsperandoSalir(Humano humano) {
        HesperandoSalir.meter(humano);
    }
    private synchronized void salirListaEsperandoSalir(Humano humano) { // Salir de la lista de esperando para salir al exterior
        HesperandoSalir.sacar(humano);
    }
    private synchronized void entrarListaEsperandoEntrar(Humano humano) {
        HesperandoEntrar.meter(humano);
    }
    private synchronized void salirListaEsperandoEntrar(Humano humano) { // Salir de la lista de esperando para salir al exterior
        HesperandoEntrar.sacar(humano);
    }
    private synchronized void entrarListaTunel(Humano humano) {
        HEnTunel.meter(humano);
    }
    private synchronized void salirListaTunel(Humano humano) { // Salir de la lista de esperando para salir al exterior
        HEnTunel.sacar(humano);
    }

    // Funciones utilizadas para el RMI
    public int getCantHumanosTunel() {
        return HesperandoEntrar.getLista().size() + HesperandoSalir.getLista().size() + HEnTunel.getLista().size();
    }
}
