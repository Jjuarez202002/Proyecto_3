package com.mycompany.proyecto_3;

import java.util.ArrayList;
import java.util.List;

public class Libro {
    private List<Hoja> hojas;

    public Libro() {
        hojas = new ArrayList<>();
    }

    public void agregarHoja(int filas, int columnas) {
        hojas.add(new Hoja(filas, columnas));
    }

    public Hoja getHoja(int indice) {
        if (indice >= 0 && indice < hojas.size()) {
            return hojas.get(indice);
        }
        return null;
    }

    public int getCantidadHojas() {
        return hojas.size();
    }
}