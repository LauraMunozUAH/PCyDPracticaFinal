package com.example.pcydpracticafinal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class InfoRemoto extends UnicastRemoteObject implements InterfaceInfoRemoto {

    private final ApocalipsisLogs logger = ApocalipsisLogs.getInstancia();
    private final Refugio refugio;
    private final ZonaRiesgo zonaRiesgo;
    private boolean pausado = false;
    private Paso paso;

    public InfoRemoto(Refugio refugio, ZonaRiesgo zonaRiesgo, Paso paso) throws RemoteException{
        this.refugio = refugio;
        this.zonaRiesgo = zonaRiesgo;
        this.paso = paso;
    }

    @Override
    public int getHumanosRefugio() throws RemoteException {
        int numero = refugio.getCantHumanosRef();
        logger.registrarEvento("Consulta de humanos en el refugio: " + numero);
        return numero;
    }

    @Override
    public int[] getHumanosTuneles() throws RemoteException {
        logger.registrarEvento("Consulta de humanos en los túneles.");
        return refugio.getCantHumanosTuneles();
    }

    @Override
    public int[] getHumanosZonasRiesgo() throws RemoteException {
        logger.registrarEvento("Consulta de humanos en zonas de riesgo.");
        return zonaRiesgo.getHumanosZonasR();
    }

    @Override
    public int[] getZombisZonasRiesgo() throws RemoteException {
        logger.registrarEvento("Consulta de zombis en zonas de riesgo.");
        return zonaRiesgo.getZombisZonasR();
    }

    @Override
    public List<String> getZombisLetales() throws RemoteException {
        logger.registrarEvento("Consulta de zombis más letales.");
        List<Zombi> zombisTotales = new ArrayList<>();
        List<SubAreaInsegura> subAreas = zonaRiesgo.getSubAreas();
        for (int i = 0; i < 4; i++) {
            ArrayList<Thread> lista = subAreas.get(i).getListaZombis().getCopiaLista();

            for (Thread t : lista) {
                zombisTotales.add((Zombi) t);
            }
        }

        // 2. Ordenar manualmente (burbuja, selección o cualquier ordenamiento simple)
        for (int i = 0; i < zombisTotales.size(); i++) {
            for (int j = i + 1; j < zombisTotales.size(); j++) {
                if (zombisTotales.get(j).getMuertes() > zombisTotales.get(i).getMuertes()) {
                    Zombi temp = zombisTotales.get(i);
                    zombisTotales.set(i, zombisTotales.get(j));
                    zombisTotales.set(j, temp);
                }
            }
        }

        List<String> resultado = new ArrayList<>();
        // 3. Tomar los 3 primeros o menos si hay menos zombis
        for (int i = 0; i < 3 && i < zombisTotales.size(); i++) {
            Zombi z = zombisTotales.get(i);
            resultado.add(z.getName() + ": " + z.getMuertes() + " muertes; ");
        }

        return resultado;
    }

    @Override
    public synchronized void pausar() throws RemoteException {
        pausado = true;
        paso.cerrar();
        logger.registrarEvento("Simulación pausada.");
    }

    @Override
    public synchronized void reanudar() throws RemoteException {
        pausado = false;
        paso.abrir();
        notifyAll();
        logger.registrarEvento("Simulación reanudada.");
    }

}
