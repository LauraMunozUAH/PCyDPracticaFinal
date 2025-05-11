package com.example.pcydpracticafinal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class InfoRemoto extends UnicastRemoteObject implements InterfaceInfoRemoto {

    private final Refugio refugio;
    private final ZonaRiesgo zonasRiesgo;
    private final List<Zombi> zombis;
    private boolean pausado = false;

    public InfoRemoto(Refugio refugio, ZonaRiesgo zonasRiesgo, List<Zombi> zombis) throws RemoteException{
        this.refugio = refugio;
        this.zonasRiesgo = zonasRiesgo;
        this.zombis = zombis;
    }

    @Override
    public int getHumanosRefugio() throws RemoteException {
        return  refugio.getCantHumanosRef();
    }

    @Override
    public int[] getHumanosTuneles() throws RemoteException {
        return refugio.getCantHumanosTuneles();
    }

    @Override
    public int[] getHumanosZonasRiesgo() throws RemoteException {
        return zonasRiesgo.getHumanosZonasR();
    }

    @Override
    public int[] getZombisZonasRiesgo() throws RemoteException {
        return zonasRiesgo.getZombisZonasR();
    }

    @Override
    public List<String> getZombisLetales() throws RemoteException {

    }

    @Override
    public synchronized void pausar() throws RemoteException {
        pausado = true;
    }

    @Override
    public synchronized void reanudar() throws RemoteException {
        pausado = false;
        notifyAll();
    }

    public synchronized boolean isPausado() {
        return pausado;
    }
}
