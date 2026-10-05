package com.semana8.speedfast.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.semana8.speedfast.controlador.ControladorPedidos;
import com.semana8.speedfast.modelo.Estado;
import com.semana8.speedfast.modelo.Pedido;
import com.semana8.speedfast.modelo.Tipo;

import java.awt.*;
import java.util.List;



public class VentanaGestionarPedidos extends JFrame{


    private ControladorPedidos controlador;
    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private JTextField txtId, txtDireccion;
    private JComboBox<Tipo> cmbTipo;
    private JComboBox<Estado> cmbEstado;
    private JButton btnGuardar, btnActualizar, btnEliminar, btnLimpiar;

    public VentanaGestionarPedidos(JFrame padre, ControladorPedidos controlador) {

        this.controlador = controlador;
        
        setTitle("Gestión de Pedidos");
        setSize(700, 500);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout(10, 10));

        String[] columnas = {"ID", "Dirección", "Tipo", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tablaPedidos = new JTable(modeloTabla);
        add(new JScrollPane(tablaPedidos), BorderLayout.CENTER);

       
        JPanel panelSur = new JPanel(new BorderLayout());
        
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelFormulario.add(new JLabel("ID (Automático):"));
        txtId = new JTextField();
        txtId.setEditable(false);
        panelFormulario.add(txtId);
        
        panelFormulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);

        panelFormulario.add(new JLabel("Tipo:"));
        cmbTipo = new JComboBox<>(Tipo.values());
        panelFormulario.add(cmbTipo);

        panelFormulario.add(new JLabel("Estado:"));
        cmbEstado = new JComboBox<>(Estado.values());
        panelFormulario.add(cmbEstado);
        
        panelSur.add(panelFormulario, BorderLayout.CENTER);

        //botoones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnGuardar = new JButton("Guardar Nuevo");
        btnActualizar = new JButton("Actualizar Seleccionado");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar Formulario");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        panelSur.add(panelBotones, BorderLayout.SOUTH);
        add(panelSur, BorderLayout.SOUTH);

        configurarEventos();
        cargarDatosTabla();
    }

    private void cargarDatosTabla() {
        modeloTabla.setRowCount(0);
        List<Pedido> pedidos = controlador.getPedidos();
        for (Pedido p : pedidos) {
            modeloTabla.addRow(new Object[]{
                p.getId(), 
                p.getDireccion(), 
                p.getTipo(), 
                p.getEstado()
            });
        }
    }

    private void configurarEventos() {
        
        tablaPedidos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaPedidos.getSelectedRow() != -1) {
                int filaSelecionada = tablaPedidos.getSelectedRow();
                txtId.setText(modeloTabla.getValueAt(filaSelecionada, 0).toString());
                txtDireccion.setText(modeloTabla.getValueAt(filaSelecionada, 1).toString());
                
                
                cmbTipo.setSelectedItem(modeloTabla.getValueAt(filaSelecionada, 2));
                cmbEstado.setSelectedItem(modeloTabla.getValueAt(filaSelecionada, 3));
            }
        });

        //Limpiar campos
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        //Guardar nuevo pedido
        btnGuardar.addActionListener(e -> {
            String direccion = txtDireccion.getText().trim();
            if (direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La dirección no puede estar vacía.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Tipo tipo = (Tipo) cmbTipo.getSelectedItem();
            Estado estado = (Estado) cmbEstado.getSelectedItem();

           
            Pedido nuevo = new Pedido(0, direccion, tipo, estado); 
            if (controlador.guardarPedido(nuevo)) {
                JOptionPane.showMessageDialog(this, "Pedido registrado exitosamente.");
                cargarDatosTabla();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al guardar en la BD.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        //actualizar pedido
        btnActualizar.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un pedido de la tabla para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String direccion = txtDireccion.getText().trim();
            if (direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La dirección no puede estar vacía.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(txtId.getText());
            Tipo tipo = (Tipo) cmbTipo.getSelectedItem();
            Estado estado = (Estado) cmbEstado.getSelectedItem();

            Pedido actualizado = new Pedido(id, direccion, tipo, estado);

            if (controlador.actualizarPedido(actualizado)) {
                JOptionPane.showMessageDialog(this, "Pedido actualizado exitosamente.");
                cargarDatosTabla();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al actualizar en la BD.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        //eliminar pedido
        btnEliminar.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un pedido de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este pedido?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(txtId.getText());
                if (controlador.borrarPedido(id)) {
                    JOptionPane.showMessageDialog(this, "Pedido eliminado exitosamente.");
                    cargarDatosTabla();
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar. Asegúrese de que no tenga entregas asignadas.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtDireccion.setText("");
        cmbTipo.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
        tablaPedidos.clearSelection();
    }
}
