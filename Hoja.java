package com.mycompany.proyecto_3;

public class Hoja {
    private Celda[][] celdas;
    private int filas;
    private int columnas;

    public Hoja(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        celdas = new Celda[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda(null);
            }
        }
    }

    public void setValor(int fila, int columna, Object valor) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            celdas[fila][columna].setValor(valor);
        }
    }

    public Object getValor(int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            return celdas[fila][columna].getValor();
        }
        return null;
    }

    // Método para evaluar una fórmula en una celda
    public Object evaluarFormula(String formula) {
    if (formula.startsWith("=")) {
        try {
            String[] partes = formula.substring(1).split("\\+|\\*");
            String operacion = formula.substring(1).contains("+") ? "+" : "*";
            String[] celda1 = partes[0].split(":");
            String[] celda2 = partes[1].split(":");

            int fila1 = Integer.parseInt(celda1[0]);
            int columna1 = Integer.parseInt(celda1[1]);
            int fila2 = Integer.parseInt(celda2[0]);
            int columna2 = Integer.parseInt(celda2[1]);

            Object val1 = getValor(fila1, columna1);
            Object val2 = getValor(fila2, columna2);

            if (val1 instanceof Number && val2 instanceof Number) {
                double valor1 = ((Number) val1).doubleValue();
                double valor2 = ((Number) val2).doubleValue();
                if (operacion.equals("+")) {
                    return valor1 + valor2;
                } else if (operacion.equals("*")) {
                    return valor1 * valor2;
                }
            }
        } catch (Exception e) {
            return "Error";
        }
    }
    return formula;
   }
}