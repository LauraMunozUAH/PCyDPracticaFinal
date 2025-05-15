package com.example.pcydpracticafinal;

import java.util.ArrayList;
import java.util.List;

public class ZonaRiesgo {

    private final List<SubAreaInsegura> subAreas = new ArrayList<>();
    private final ApocalipsisLogs logger = ApocalipsisLogs.getInstancia();

    public ZonaRiesgo(){
        for (int i=0; i < 4; i++) {
            subAreas.add(new SubAreaInsegura());
        }
        logger.registrarEvento("Zona de riesgo inicializada con 4 subáreas.");
    }
    public List<SubAreaInsegura> getSubAreas() {
        return subAreas;
    }

    // Funciones utilizadas para el RMI

    public int[] getHumanosZonasR() {
        int[] humanosZonasR = new int[4];
        for (int i = 0; i < 4; i++) {
            SubAreaInsegura subArea = subAreas.get(i);
            humanosZonasR[i] = subArea.getListaHumanos().getLista().size();
        }
        logger.registrarEvento("Número de humanos en zonas de riesgo consultado.");
        return humanosZonasR;
    }

    public int[] getZombisZonasR() {
        int[] zombisZonasR = new int[4];
        for (int i = 0; i < 4; i++) {
            SubAreaInsegura subArea = subAreas.get(i);
            zombisZonasR[i] = subArea.getListaZombis().getLista().size();
        }
        logger.registrarEvento("Número de zombis en zonas de riesgo consultado.");
        return zombisZonasR;
    }
}
