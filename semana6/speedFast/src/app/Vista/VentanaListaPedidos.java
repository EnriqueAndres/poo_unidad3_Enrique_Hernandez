package app.Vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;

import app.Controlador.PedidoController;
import app.Model.Pedido;

public class VentanaListaPedidos extends JFrame{
    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private PedidoController controlador;

    public VentanaListaPedidos(JFrame frame, PedidoController controlador) {
        this.controlador = controlador;

        setTitle("Listado de Pedidos SpeedFast");
        setSize(500, 300);
        setLocationRelativeTo(frame);
        setLayout(new BorderLayout());

        // columnas
        String[] columnas = {"ID", "Tipo", "Dirección de Entrega"};
        modeloTabla = new DefaultTableModel(columnas, 0);

        tablaPedidos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);

        JButton botonRefrescar = new JButton("Refrescar Tabla");
        botonRefrescar.addActionListener(e -> cargarDatosTabla());

        add(scrollPane, BorderLayout.CENTER);
        add(botonRefrescar, BorderLayout.SOUTH);

        cargarDatosTabla();
    }

    //recargar datos al ingresar uno nuevo
    public void cargarDatosTabla() {
        modeloTabla.setRowCount(0);
        for (Pedido p : controlador.getListaPedidos()) {
            Object[] fila = {p.getIdPedido(), p.getTipoPedido(), p.getDireccionEntrega()};
            modeloTabla.addRow(fila);
        }
    }
}
