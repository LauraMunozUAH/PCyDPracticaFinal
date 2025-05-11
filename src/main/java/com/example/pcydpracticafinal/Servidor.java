package com.example.pcydpracticafinal;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Servidor {
    public static void mainServidor(Refugio refugio, ZonaRiesgo zonaRiesgo, Paso paso) {
        try {
            InfoRemoto info = new InfoRemoto(refugio, zonaRiesgo, paso);
            LocateRegistry.createRegistry(2000);

            Naming.rebind("rmi://localhost/InfoRemoto", info);
            System.out.println("Servidor RMI listo.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
