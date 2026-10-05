package com.semana8.speedfast.vista;

import javax.swing.*;
import java.awt.*;

import com.semana8.speedfast.controlador.ControladorEntrega;
import com.semana8.speedfast.controlador.ControladorPedidos;
import com.semana8.speedfast.controlador.ControladorRepartidor;

public class VentanaPrincipal extends JFrame{

    private final ControladorPedidos ctrlPedidos = new ControladorPedidos();
    private final ControladorRepartidor ctrlRepartidores = new ControladorRepartidor();
    private final ControladorEntrega ctrlEntregas = new ControladorEntrega();

    public VentanaPrincipal() {
        setTitle("SpeedFast - Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 15, 15));

        JButton btnPedidos = new JButton("Gestión de Pedidos");
        JButton btnRepartidores = new JButton("Gestión de Repartidores");
        JButton btnEntregas = new JButton("Gestión de Entregas");

        // eventos
        btnPedidos.addActionListener(e -> new VentanaGestionarPedidos(this, ctrlPedidos).setVisible(true));
        btnRepartidores.addActionListener(e -> new VentanaGestionarRepartidores(this, ctrlRepartidores).setVisible(true));
        btnEntregas.addActionListener(e -> new VentanaGestionarEntregas(this, ctrlEntregas, ctrlPedidos, ctrlRepartidores).setVisible(true));

        add(btnPedidos);
        add(btnRepartidores);
        add(btnEntregas);
    }
}
