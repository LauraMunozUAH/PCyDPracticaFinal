package com.example.pcydpracticafinal;

import java.util.ArrayList;
import java.util.List;

public class ZonaRiesgo {
    private final List<Humano> humanos = new ArrayList<>();
    private final List<Zombi> zombis = new ArrayList<>();

    public synchronized void entrarZonaRHumano(Humano humano) {
        humanos.add(humano);
    }
    public synchronized void entrarZonaRZombi(Zombi zombi) {
        zombis.add(zombi);
    }


}
