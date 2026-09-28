package com.example.vista;

import javax.swing.*;
import java.awt.*;

import com.example.controlador.ControladorPedidos;

public class VentanaPrincipal extends JFrame{

    private ControladorPedidos controladorPedidos;

    public VentanaPrincipal() {
        
        controladorPedidos = new ControladorPedidos();

        setTitle("SpeedFast");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1, 10, 10));

        JButton btnRegistro = new JButton("Registrar Nuevo Pedido");

        JButton btnListar = new JButton("Consultar Pedidos");

        btnRegistro.addActionListener(e -> new VentanaRegistroPedido(this, controladorPedidos).setVisible(true));

        btnListar.addActionListener(e -> new VentanaListaPedidos(this, controladorPedidos).setVisible(true));

        add(btnRegistro);
        add(btnListar);
    }
}
