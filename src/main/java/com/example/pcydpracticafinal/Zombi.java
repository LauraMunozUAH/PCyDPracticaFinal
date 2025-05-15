package com.example.pcydpracticafinal;

import java.util.ArrayList;
import java.util.List;


public class Zombi extends Thread {
    private final ApocalipsisLogs logger = ApocalipsisLogs.getInstancia();
    private final String id;
    private final ZonaRiesgo zonasRiesgo;
    private int muertes = 0;
    private int area;
    private Paso paso;

    public Zombi(String id, ZonaRiesgo zonasRiesgo, int area, Paso paso) {
        super.setName(id);
        this.id = id;
        this.zonasRiesgo = zonasRiesgo;
        this.area = area;
        this.paso = paso;
    }

    @Override
    public void run() {
        try {
            while (true) {
                paso.mirar();
                SubAreaInsegura areaSeleccionada = zonasRiesgo.getSubAreas().get(area);
                logger.registrarEvento("El zombi " + id + " entra en la zona de riesgo " + area + ".");
                areaSeleccionada.entrarZonaRZombi(this);
                logger.registrarEvento("Zombi " + id + " busca humanos en la zona de riesgo " + area + 1);
                paso.mirar();
                ArrayList<Thread> humanos = areaSeleccionada.getListaHumanos().getCopiaLista();

                if (!humanos.isEmpty()) {

                    int posicionAtacado = (int) (Math.random() * (humanos.size() - 1));
                    Humano humanoAtacado = (Humano) humanos.get(posicionAtacado);

                    paso.mirar();
                    if (!humanoAtacado.getEsAtacado()){
                        System.out.println("El zombi " + id + " ataca al humano " + humanoAtacado.getName() + " (número de muertes: " + muertes + ")");
                        logger.registrarEvento("Zombi " + id + " ataca al humano " + humanoAtacado.getName());
                        paso.mirar();
                        humanoAtacado.interrupt(); //¿Dónde lo pongo? ¿Aquí o dos líneas más abajo?
                        humanoAtacado.zombiAtaca(this); //Si devuelve true sobrevive, si devuelve false muere. Al ser llamado directamente desde zombi.
                        paso.mirar();
                        sleep((int) (500 + Math.random() * 1000));
                        humanoAtacado.setEsAtacado();
                    }

                }
                paso.mirar();
                System.out.println("Zombi " + id + " está buscando comida.");
                sleep((int) (2000 + Math.random() * 1000));
                paso.mirar();
                areaSeleccionada.salirZonaRZombi(this);
                area =  (int) (1 + Math.random() * 3);
                logger.registrarEvento("El zombi " + id + " sale de la zona de riesgo " + area + ".");

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getMuertes() {
        return muertes;
    }

    public void setMuertes(){
        this.muertes += 1;
    }

}
