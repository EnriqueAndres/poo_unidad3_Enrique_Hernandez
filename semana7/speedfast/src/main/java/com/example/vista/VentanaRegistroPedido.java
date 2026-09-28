package com.example.vista;

import javax.swing.*;
import java.awt.*;

import com.example.controlador.ControladorPedidos;
import com.example.modelo.Estado;
import com.example.modelo.Pedido;

public class VentanaRegistroPedido extends JFrame{

    private JTextField campoDireccion;
    private JComboBox<String> comboTipo;
    private JButton botonGuardar;
    private ControladorPedidos controladorPedidos;

    public VentanaRegistroPedido(JFrame padre, ControladorPedidos controlador) {
        this.controladorPedidos = controlador;

        setTitle("Registrar Pedido");
        setSize(320, 200);
        setLocationRelativeTo(padre);
        setLayout(new GridLayout(3, 2, 8, 8));

        campoDireccion = new JTextField();
        String[] opcionesTipo = {"COMIDA", "ENCOMIENDA", "EXPRESS"};
        comboTipo = new JComboBox<>(opcionesTipo);
        botonGuardar = new JButton("Guardar en BD");

        botonGuardar.addActionListener(e -> {
            String direccion = campoDireccion.getText().trim();
            String tipo = (String) comboTipo.getSelectedItem();

            if (direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar una dirección.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Pedido nuevo = new Pedido(0, direccion, tipo, Estado.PENDIENTE);
            if (controladorPedidos.guardarPedido(nuevo)) {
                JOptionPane.showMessageDialog(this, "¡Pedido registrado correctamente en MySQL!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar en la base de datos.", "Error BD", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(new JLabel("  Dirección:"));
        add(campoDireccion);
        add(new JLabel("  Tipo:"));
        add(comboTipo);
        add(new JLabel(""));
        add(botonGuardar);
    }
}
