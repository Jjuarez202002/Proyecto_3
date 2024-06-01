package com.mycompany.proyecto_3;

import java.util.LinkedList;

public class TablaHash {
    private class Entrada {
        String clave;
        String valor;

        Entrada(String clave, String valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    private LinkedList<Entrada>[] tabla;

    public TablaHash(int capacidad) {
        tabla = new LinkedList[capacidad];
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    public void poner(String clave, String valor) {
        int indice = obtenerIndice(clave);
        for (Entrada entrada : tabla[indice]) {
            if (entrada.clave.equals(clave)) {
                entrada.valor = valor;
                return;
            }
        }
        tabla[indice].add(new Entrada(clave, valor));
    }

    public String obtener(String clave) {
        int indice = obtenerIndice(clave);
        for (Entrada entrada : tabla[indice]) {
            if (entrada.clave.equals(clave)) {
                return entrada.valor;
            }
        }
        return null;
    }

    private int obtenerIndice(String clave) {
        return Math.abs(clave.hashCode()) % tabla.length;
    }
}