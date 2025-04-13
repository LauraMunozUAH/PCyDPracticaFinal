package com.example.pcydpracticafinal;

import java.util.ArrayList;
import java.util.List;

public class ZonaRiesgo {

    private final List<SubAreaInsegura> subAreas = new ArrayList<>();

    public ZonaRiesgo(){
        for (int i=0; i < 4; i++) {
            subAreas.add(new SubAreaInsegura());
        }
    }

    public List<SubAreaInsegura> getSubAreas() {
        return subAreas;
    }

    public void convertirHaZ(Zombi zombiAtacante,Humano humano, int area) {
        if (!humano.muerto){ //Para asegurarnos de que dos zombis no maten a un mismo humano.
            zombiAtacante.setMuertes();//Aumenta el número de conversiones del zombi atacante.
            String IDHumano = humano.getID();

            String IDZombi = "Z" + IDHumano.substring(1);
            humano.muerto=true;
            subAreas.get(area).salirZonaRHumano(humano);
            Zombi zombi = new Zombi(IDZombi, this, area);
            subAreas.get(area).entrarZonaRZombi(zombi);
            zombi.start();
        }
    }
}
