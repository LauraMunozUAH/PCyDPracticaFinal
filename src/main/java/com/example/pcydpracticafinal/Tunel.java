package com.example.pcydpracticafinal;

import javafx.scene.control.TextField;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tunel {
    private final int id;
    private final Semaphore semTunel = new Semaphore(1); // Solo 1 humano en el túnel a la vez
    private final CyclicBarrier barreraSalida = new CyclicBarrier(3); // Para formar grupos de 3
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
            System.out.println("El humano "+ humano.getName()+ " está esperando para salir del túnel "+ id);
            try {
                barreraSalida.await();

            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Se ha formado un grupo de 3 humanos para salir al exterior.");
            atravesarTunel(humano, salida);
        } else {
            entrarListaEsperandoEntrar(humano);
            System.out.println("El humano " + humano.getID() + " está en la cola para entrar.");
            atravesarTunel(humano, salida);
        }
    }

    private void atravesarTunel(Humano humano, boolean salida) {
        lock.lock();
        try {
            while (salida && !HesperandoEntrar.getLista().isEmpty()){
                puedeCruzar.await();
            }
            //semTunel.acquire();

            if (salida) {
                salirListaEsperandoSalir(humano);
            } else {
                salirListaEsperandoEntrar(humano);
            }
            entrarListaTunel(humano);
            System.out.println("El humano " + humano.getID() + " está atravesando el túnel " + id);
            humano.sleep(1000);
            salirListaTunel(humano);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
            //semTunel.release();
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
}
