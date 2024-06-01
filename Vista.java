package com.mycompany.proyecto_3;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vista {
    private JFrame marco;
    private JTabbedPane panelPestañas;
    private Controlador controlador;

    public Vista(Controlador controlador) {
        this.controlador = controlador;
        inicializar();
    }

    private void inicializar() {
        // Configuración del marco principal
        marco = new JFrame("Proyecto 3");
        marco.setBounds(100, 100, 800, 600);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Barra de menú
        JMenuBar barraMenu = new JMenuBar();
        marco.setJMenuBar(barraMenu);

        // Menú "Archivo"
        JMenu menuArchivo = new JMenu("Archivo");
        barraMenu.add(menuArchivo);

        // Opción "Nueva Hoja"
        JMenuItem itemNuevaHoja = new JMenuItem("Nueva Hoja");
        itemNuevaHoja.addActionListener(e -> controlador.crearHoja(10, 10));
        menuArchivo.add(itemNuevaHoja);

        // Opción "Tabla Hash"
        JMenuItem itemTablaHash = new JMenuItem("Tabla Hash");
        itemTablaHash.addActionListener(e -> mostrarDialogoTablaHash());
        menuArchivo.add(itemTablaHash);

        // Panel de pestañas
        panelPestañas = new JTabbedPane(JTabbedPane.TOP);
        marco.getContentPane().add(panelPestañas, BorderLayout.CENTER);

        marco.setVisible(true);
    }

    // Método para agregar una nueva pestaña de hoja
    public void agregarPestañaHoja(Hoja hoja) {
        DefaultTableModel modelo = new DefaultTableModel(hoja.getFilas(), hoja.getColumnas());
        JTable tabla = new JTable(modelo) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // Permitir la edición de las celdas
            }
        };

        tabla.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            String data = (String) tabla.getValueAt(row, column);
            if (data != null && data.startsWith("=")) {
                Object resultado = hoja.evaluarFormula(data);
                tabla.setValueAt(resultado, row, column);
            } else {
                hoja.setValor(row, column, data);
            }
        });

        // Agregar la tabla a la pestaña
        panelPestañas.addTab("Hoja " + controlador.getCantidadHojas(), new JScrollPane(tabla));
    }

     private void mostrarDialogoTablaHash() {
            JDialog dialogo = new JDialog(marco, "Tabla Hash", true);
            dialogo.setSize(400, 300);
            dialogo.setLayout(new BorderLayout());

            JPanel panelEntrada = new JPanel();
            panelEntrada.setLayout(new GridLayout(0, 2));

            JLabel etiquetaClave = new JLabel("Clave:");
            JTextField campoClave = new JTextField();
            panelEntrada.add(etiquetaClave);
            panelEntrada.add(campoClave);

            JLabel etiquetaValor = new JLabel("Valor:");
            JTextField campoValor = new JTextField();
            panelEntrada.add(etiquetaValor);
            panelEntrada.add(campoValor);

            JButton botonAgregar = new JButton("Agregar");
            panelEntrada.add(botonAgregar);

            JTextArea areaTexto = new JTextArea();
            JScrollPane panelDesplazamiento = new JScrollPane(areaTexto);
            dialogo.add(panelEntrada, BorderLayout.NORTH);
            dialogo.add(panelDesplazamiento, BorderLayout.CENTER);

            // Acción del botón "Agregar"
            botonAgregar.addActionListener(e -> {
                String clave = campoClave.getText();
                String valor = campoValor.getText();
                controlador.agregarEntradaHash(clave, valor);
                areaTexto.append("Clave: " + clave + ", Valor: " + valor + "\n");
            });

            dialogo.setVisible(true);
        }
    }