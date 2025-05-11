package com.example.pcydpracticafinal;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Servidor {
    public static void main(String[] args) {
        try {
            InfoRemoto info = new InfoRemoto(refugio, zonasRiesgo, listaZombis);
            LocateRegistry.createRegistry(1099);

            Naming.rebind("rmi://localhost/InfoRemota", info);
            System.out.println("Servidor RMI listo.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
