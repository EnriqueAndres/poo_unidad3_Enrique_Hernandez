package com.semana8.speedfast.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import com.semana8.speedfast.controlador.ControladorRepartidor;
import com.semana8.speedfast.modelo.Repartidor;

public class VentanaGestionarRepartidores extends JFrame{

    private ControladorRepartidor controlador;
    private JTable tablaRepartidores;
    private DefaultTableModel modeloTabla;
    private JTextField txtId, txtNombre;
    private JButton btnGuardar, btnActualizar, btnEliminar, btnLimpiar;

    public VentanaGestionarRepartidores(JFrame padre, ControladorRepartidor controlador) {
        this.controlador = controlador;
        
        setTitle("Gestión de Repartidores");
        setSize(600, 450);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout(10, 10));

        String[] columnas = {"ID", "Nombre"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tablaRepartidores = new JTable(modeloTabla);
        add(new JScrollPane(tablaRepartidores), BorderLayout.CENTER);

        JPanel panelSur = new JPanel(new BorderLayout());
        
        JPanel panelFormulario = new JPanel(new GridLayout(2, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelFormulario.add(new JLabel("ID (Automático para nuevos):"));
        txtId = new JTextField();
        txtId.setEditable(false); 
        panelFormulario.add(txtId);
        
        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);
        
        panelSur.add(panelFormulario, BorderLayout.CENTER);

       
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
        List<Repartidor> repartidores = controlador.getRepartidores();
        for (Repartidor r : repartidores) {
            modeloTabla.addRow(new Object[]{r.getId(), r.getNombre()});
        }
    }

    private void configurarEventos() {
        
        tablaRepartidores.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaRepartidores.getSelectedRow() != -1) {
                int filaSelecionada = tablaRepartidores.getSelectedRow();
                txtId.setText(modeloTabla.getValueAt(filaSelecionada, 0).toString());
                txtNombre.setText(modeloTabla.getValueAt(filaSelecionada, 1).toString());
            }
        });

     
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        //guardar nuevo repartidor
        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Repartidor nuevo = new Repartidor(0, nombre); 
            if (controlador.agregarRepartidor(nuevo)) {
                JOptionPane.showMessageDialog(this, "Repartidor registrado exitosamente.");
                cargarDatosTabla();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al guardar en la BD.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnActualizar.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un repartidor de la tabla para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(txtId.getText());
            Repartidor actualizado = new Repartidor(id, nombre);

            if (controlador.actualizarRepartidor(actualizado)) {
                JOptionPane.showMessageDialog(this, "Repartidor actualizado exitosamente.");
                cargarDatosTabla();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al actualizar en la BD.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // eliminar repartidor
        btnEliminar.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un repartidor de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este repartidor?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(txtId.getText());
                if (controlador.borrarRepartidor(id)) {
                    JOptionPane.showMessageDialog(this, "Repartidor eliminado exitosamente.");
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
        txtNombre.setText("");
        tablaRepartidores.clearSelection();
    }
}
