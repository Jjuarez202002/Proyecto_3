package com.mycompany.proyecto_3;

public class Controlador {
    private Libro libro;
    private Vista vista;
    private TablaHash tablaHash;

    public void inicializar() {
        // Inicializa el modelo, la vista y la tabla hash
        libro = new Libro();
        vista = new Vista(this);
        tablaHash = new TablaHash(100); // Capacidad inicial de la tabla hash
    }

    // Método para crear una nueva hoja
    public void crearHoja(int filas, int columnas) {
        // Agrega una nueva hoja al libro y actualiza la vista
        libro.agregarHoja(filas, columnas);
        vista.agregarPestañaHoja(libro.getHoja(libro.getCantidadHojas() - 1));
    }

    // Método para obtener la cantidad de hojas en el libro
    public int getCantidadHojas() {
        return libro.getCantidadHojas();
    }

    // Método para agregar una entrada en la tabla hash
    public void agregarEntradaHash(String clave, String valor) {
        // Agrega una nueva entrada en la tabla hash
        tablaHash.poner(clave, valor);
    }

    // Método para obtener la hoja actual
    public Hoja getHojaActual() {
        if (libro.getCantidadHojas() > 0) {
            return libro.getHoja(libro.getCantidadHojas() - 1);
        }
        return null;
    }
}