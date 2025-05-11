package com.example.pcydpracticafinal;

import java.rmi.RemoteException;
import java.util.List;

public interface InterfaceInfoRemoto {
    int getHumanosRefugio() throws RemoteException;
    int[] getHumanosTuneles() throws RemoteException;
    int[] getHumanosZonasRiesgo() throws RemoteException;
    int[] getZombisZonasRiesgo() throws RemoteException;
    List<String> getZombisLetales() throws RemoteException;
    void pausar() throws RemoteException;
    void reanudar() throws RemoteException;
}
