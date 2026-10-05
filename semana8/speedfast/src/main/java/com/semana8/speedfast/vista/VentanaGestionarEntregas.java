package com.semana8.speedfast.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.semana8.speedfast.controlador.ControladorEntrega;
import com.semana8.speedfast.controlador.ControladorPedidos;
import com.semana8.speedfast.controlador.ControladorRepartidor;
import com.semana8.speedfast.modelo.Entrega;
import com.semana8.speedfast.modelo.Pedido;
import com.semana8.speedfast.modelo.Repartidor;

public class VentanaGestionarEntregas extends JFrame {

    private ControladorEntrega ctrlEntrega;
    private ControladorPedidos ctrlPedidos;
    private ControladorRepartidor ctrlRepartidor;

    private JTable tablaEntregas;
    private DefaultTableModel modeloTabla;
    private JTextField txtId, txtFecha, txtHora;
    private JComboBox<Pedido> cmbPedido;
    private JComboBox<Repartidor> cmbRepartidor;
    private JButton btnGuardar, btnActualizar, btnEliminar, btnLimpiar;

    public VentanaGestionarEntregas(JFrame padre, ControladorEntrega ctrlEntrega, ControladorPedidos ctrlPedidos, ControladorRepartidor ctrlRepartidor) {
        this.ctrlEntrega = ctrlEntrega;
        this.ctrlPedidos = ctrlPedidos;
        this.ctrlRepartidor = ctrlRepartidor;
        
        setTitle("Gestión de Entregas");
        setSize(800, 550);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout(10, 10));

      
        String[] columnas = {"ID Entrega", "Pedido (Dir)", "Repartidor", "Fecha", "Hora"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEntregas = new JTable(modeloTabla);
        add(new JScrollPane(tablaEntregas), BorderLayout.CENTER);

       
        JPanel panelSur = new JPanel(new BorderLayout());
        
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelFormulario.add(new JLabel("ID Entrega (Automático):"));
        txtId = new JTextField();
        txtId.setEditable(false);
        panelFormulario.add(txtId);

      
        panelFormulario.add(new JLabel("Seleccionar Pedido:"));
        cmbPedido = new JComboBox<>();
        cmbPedido.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Pedido) {
                    Pedido p = (Pedido) value;
                    setText("ID: " + p.getId() + " - " + p.getDireccion() + " (" + p.getEstado() + ")");
                }
                return this;
            }
        });
        panelFormulario.add(cmbPedido);

        panelFormulario.add(new JLabel("Seleccionar Repartidor:"));
        cmbRepartidor = new JComboBox<>();
        cmbRepartidor.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Repartidor) {
                    Repartidor r = (Repartidor) value;
                    setText("ID: " + r.getId() + " - " + r.getNombre());
                }
                return this;
            }
        });
        panelFormulario.add(cmbRepartidor);

        panelFormulario.add(new JLabel("Fecha (YYYY-MM-DD):"));
        txtFecha = new JTextField(LocalDate.now().toString());
        panelFormulario.add(txtFecha);

        panelFormulario.add(new JLabel("Hora (HH:MM):"));
        txtHora = new JTextField(LocalTime.now().withSecond(0).withNano(0).toString());
        panelFormulario.add(txtHora);
        
        panelSur.add(panelFormulario, BorderLayout.CENTER);

    
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnGuardar = new JButton("Guardar Nueva");
        btnActualizar = new JButton("Actualizar Seleccionada");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar Formulario");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        panelSur.add(panelBotones, BorderLayout.SOUTH);
        add(panelSur, BorderLayout.SOUTH);

      
        cargarCombos();
        cargarDatosTabla();
        configurarEventos();
    }

    private void cargarCombos() {
        cmbPedido.removeAllItems();
        for (Pedido p : ctrlPedidos.getPedidos()) {
            cmbPedido.addItem(p);
        }

        cmbRepartidor.removeAllItems();
        for (Repartidor r : ctrlRepartidor.getRepartidores()) {
            cmbRepartidor.addItem(r);
        }
    }

    private void cargarDatosTabla() {
        modeloTabla.setRowCount(0); 
        List<Entrega> entregas = ctrlEntrega.getEntregas();
        for (Entrega e : entregas) {
            modeloTabla.addRow(new Object[]{
                e.getId(), 
                "ID: " + e.getPedido().getId() + " - " + e.getPedido().getDireccion(), 
                e.getRepartidor().getNombre(), 
                e.getFecha(), 
                e.getHora()
            });
        }
    }

    private void configurarEventos() {
        tablaEntregas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaEntregas.getSelectedRow() != -1) {
                int fila = tablaEntregas.getSelectedRow();
                txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
            
                int idEntrega = (int) modeloTabla.getValueAt(fila, 0);
                Entrega entregaSeleccionada = ctrlEntrega.getEntregas().stream()
                        .filter(ent -> ent.getId() == idEntrega)
                        .findFirst().orElse(null);

                if (entregaSeleccionada != null) {
                    for (int i = 0; i < cmbPedido.getItemCount(); i++) {
                        if (cmbPedido.getItemAt(i).getId() == entregaSeleccionada.getPedido().getId()) {
                            cmbPedido.setSelectedIndex(i);
                            break;
                        }
                    }
                    for (int i = 0; i < cmbRepartidor.getItemCount(); i++) {
                        if (cmbRepartidor.getItemAt(i).getId() == entregaSeleccionada.getRepartidor().getId()) {
                            cmbRepartidor.setSelectedIndex(i);
                            break;
                        }
                    }
                }

                txtFecha.setText(modeloTabla.getValueAt(fila, 3).toString());
                txtHora.setText(modeloTabla.getValueAt(fila, 4).toString());
            }
        });

        btnLimpiar.addActionListener(e -> limpiarFormulario());

        btnGuardar.addActionListener(e -> {
            try {
                Pedido pedido = (Pedido) cmbPedido.getSelectedItem();
                Repartidor repartidor = (Repartidor) cmbRepartidor.getSelectedItem();
                
                if (pedido == null || repartidor == null) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido y un repartidor.", "Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());
                LocalTime hora = LocalTime.parse(txtHora.getText().trim());

                Entrega nueva = new Entrega(0, pedido, repartidor, fecha, hora);
                if (ctrlEntrega.guardarEntrega(nueva)) {
                    JOptionPane.showMessageDialog(this, "Entrega registrada exitosamente.");
                    cargarDatosTabla();
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar en la BD.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Formato de fecha u hora incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnActualizar.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione una entrega de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(txtId.getText());
                Pedido pedido = (Pedido) cmbPedido.getSelectedItem();
                Repartidor repartidor = (Repartidor) cmbRepartidor.getSelectedItem();
                LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());
                LocalTime hora = LocalTime.parse(txtHora.getText().trim());

                Entrega actualizada = new Entrega(id, pedido, repartidor, fecha, hora);
                if (ctrlEntrega.actualizarEntrega(actualizada)) {
                    JOptionPane.showMessageDialog(this, "Entrega actualizada exitosamente.");
                    cargarDatosTabla();
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar en la BD.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Formato de fecha u hora incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione una entrega de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar esta entrega?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(txtId.getText());
                if (ctrlEntrega.borrarEntrega(id)) {
                    JOptionPane.showMessageDialog(this, "Entrega eliminada exitosamente.");
                    cargarDatosTabla();
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar en la BD.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void limpiarFormulario() {
        txtId.setText("");
        if (cmbPedido.getItemCount() > 0) cmbPedido.setSelectedIndex(0);
        if (cmbRepartidor.getItemCount() > 0) cmbRepartidor.setSelectedIndex(0);
        txtFecha.setText(LocalDate.now().toString());
        txtHora.setText(LocalTime.now().withSecond(0).withNano(0).toString());
        tablaEntregas.clearSelection();
    }
}