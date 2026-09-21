package app.Vista;
import javax.swing.*;

import app.Controlador.PedidoController;

import java.awt.*;

public class VentanaPrincipal extends JFrame{
    private PedidoController controller;

    public VentanaPrincipal(){
        controller = new PedidoController();

        setTitle("SpeedFast - Gestión de Entregas");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnRegistro = new JButton("Registrar Pedido");
        JButton btnListar = new JButton("Listado Pedidos");
        JButton btnSimular = new JButton("Asignar Repartidor / Iniciar Entrega");

    
        btnRegistro.addActionListener(e -> {
            VentanaRegistroPedido form = new VentanaRegistroPedido(this, controller);
            form.setVisible(true);
        });
    
        btnListar.addActionListener(e -> {
            VentanaListaPedidos lista = new VentanaListaPedidos(this, controller);
            lista.setVisible(true);
        });

        btnSimular.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Repartidores asignados e inicio de entregas simulado correctamente.", 
                "SpeedFast", JOptionPane.INFORMATION_MESSAGE);
        });

        //colores
        btnRegistro.setBackground(new Color(41, 200, 100));
        btnListar.setBackground(new Color(100, 100, 255));
        btnSimular.setBackground(new Color(200, 100, 180));

        add(btnRegistro);
        add(btnListar);
        add(btnSimular);
    }
}


