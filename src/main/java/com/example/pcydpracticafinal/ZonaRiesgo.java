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

}
