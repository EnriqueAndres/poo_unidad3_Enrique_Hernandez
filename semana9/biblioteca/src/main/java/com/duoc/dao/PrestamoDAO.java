package com.duoc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duoc.conexion.ConexionBD;
import com.duoc.modelo.Prestamos;

public class PrestamoDAO {

    // crear
    public boolean insertarPrestamo(Prestamos prestamo) {
        String sql = "INSERT INTO prestamos (id_estudiante, id_libro, fecha_prestamo, fecha_devolucion, devuelto) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, prestamo.getId_estudiante());
            ps.setInt(2, prestamo.getId_libro());
            ps.setDate(3, Date.valueOf(prestamo.getFecha_prestamo()));
            ps.setDate(4, Date.valueOf(prestamo.getFecha_devolucion()));
            ps.setBoolean(5, prestamo.isDevuelto());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar préstamo: " + e.getMessage());
            return false;
        }
    }

    // get leer
    public List<Prestamos> listarPrestamos() {
        List<Prestamos> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Prestamos p = new Prestamos(
                    rs.getInt("id"),
                    rs.getInt("id_estudiante"),
                    rs.getInt("id_libro"),
                    rs.getDate("fecha_prestamo").toLocalDate(),
                    rs.getDate("fecha_devolucion").toLocalDate(),
                    rs.getBoolean("devuelto")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar préstamos: " + e.getMessage());
        }
        return lista;
    }

    // actualizar
    public boolean actualizarPrestamo(Prestamos prestamo) {
        String sql = "UPDATE prestamos SET id_estudiante = ?, id_libro = ?, fecha_prestamo = ?, fecha_devolucion = ?, devuelto = ? WHERE id = ?";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, prestamo.getId_estudiante());
            ps.setInt(2, prestamo.getId_libro());
            ps.setDate(3, Date.valueOf(prestamo.getFecha_prestamo()));
            ps.setDate(4, Date.valueOf(prestamo.getFecha_devolucion()));
            ps.setBoolean(5, prestamo.isDevuelto());
            ps.setInt(6, prestamo.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar préstamo: " + e.getMessage());
            return false;
        }
    }

    // borrar
    public boolean eliminarPrestamo(int id) {
        String sql = "DELETE FROM prestamos WHERE id = ?";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar préstamo: " + e.getMessage());
            return false;
        }
    }
}
