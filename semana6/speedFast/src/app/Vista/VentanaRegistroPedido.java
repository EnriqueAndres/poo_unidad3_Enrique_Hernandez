package app.Vista;

import javax.swing.*;
import java.awt.*;

import app.Controlador.PedidoController;
import app.Model.Pedido;

public class VentanaRegistroPedido extends JFrame{
    private JTextField campoID;
    private JTextField campoDireccion;
    private JComboBox<String> comboTipo;
    private JButton botonGuardar;
    private PedidoController controlador;
    private JFrame ventanaPadre;

    public VentanaRegistroPedido(JFrame padre, PedidoController controlador) {
        this.ventanaPadre = padre;
        this.controlador = controlador;

        setSize(300, 200);
        setLocationRelativeTo(padre);
        setLayout(new GridLayout(4, 2, 8, 8));

       
        campoID = new JTextField();
        campoDireccion = new JTextField();
        
        String[] opcionesTipo = {"Comida", "Encomienda", "Express"};
        comboTipo = new JComboBox<>(opcionesTipo);
        
        botonGuardar = new JButton("Guardar");

        // guardar 
        botonGuardar.addActionListener(e -> {
            String strID = campoID.getText().trim();
            String direccion = campoDireccion.getText().trim();
            String tipoPedido = (String) comboTipo.getSelectedItem();
            
           // validar datos
           int id;
            try {
                id = Integer.parseInt(strID);
                if (id <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un numero mayor a cero y entero.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // crear nuevo pedido
            Pedido nuevoPedido = new Pedido(id, tipoPedido, direccion);
            controlador.agregarPedido(nuevoPedido);

            // confirmacion
            JOptionPane.showMessageDialog(this, "Pedido registrado \nID: " + id, "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        
        add(new JLabel("  ID del Pedido:"));
        add(campoID);
        add(new JLabel("  Dirección:"));
        add(campoDireccion);
        add(new JLabel("  Tipo:"));
        add(comboTipo);
        add(new JLabel(""));
        add(botonGuardar);
    }
}
