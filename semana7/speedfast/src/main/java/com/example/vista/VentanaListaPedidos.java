package com.example.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.example.controlador.ControladorPedidos;
import com.example.modelo.Pedido;

public class VentanaListaPedidos extends JFrame{

    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private ControladorPedidos controladorPedidos;

    public VentanaListaPedidos(JFrame padre, ControladorPedidos controlador) {
        this.controladorPedidos = controlador;

        setTitle("Listado de Pedidos desde MySQL");
        setSize(550, 300);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());

        String[] columnas = {"ID", "Dirección", "Tipo", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tablaPedidos = new JTable(modeloTabla);
        JButton botonRefrescar = new JButton("Cargar / Refrescar BD");
        botonRefrescar.addActionListener(e -> cargarTabla());

        add(new JScrollPane(tablaPedidos), BorderLayout.CENTER);
        add(botonRefrescar, BorderLayout.SOUTH);

        cargarTabla();
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        for (Pedido p : controladorPedidos.getPedidos()) {
            Object[] fila = {p.getId(), p.getDireccion(), p.getTipo(), p.getEstado()};
            modeloTabla.addRow(fila);
        }
    }
}
