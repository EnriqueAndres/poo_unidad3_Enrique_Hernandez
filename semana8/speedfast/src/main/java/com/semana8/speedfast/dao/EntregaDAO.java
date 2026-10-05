package com.semana8.speedfast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.semana8.speedfast.conexion.ConexionBD;
import com.semana8.speedfast.modelo.Entrega;
import com.semana8.speedfast.modelo.Estado;
import com.semana8.speedfast.modelo.Pedido;
import com.semana8.speedfast.modelo.Repartidor;
import com.semana8.speedfast.modelo.Tipo;

public class EntregaDAO {

    //insertar entrega a base de datos
    public boolean guardarEntrega (Entrega entrega){
        String sql = "insert into entrega (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?);";

        try (Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql)){
            
            ps.setInt(1, entrega.getPedido().getId());
            ps.setInt(2, entrega.getRepartidor().getId());

            ps.setDate(3, java.sql.Date.valueOf(entrega.getFecha()));
            ps.setTime(4, java.sql.Time.valueOf(entrega.getHora()));

            int filas = ps.executeUpdate();
            return filas>0;

        } catch (SQLException e) {
            System.out.println("Error al registrar entrega: " + e.getMessage());
            return false;
        }
    }

    //obtener entregas almacenadas
    public List<Entrega> listarEntregas(){
        List<Entrega> entregas = new ArrayList<>();
        
        String sql = "SELECT e.id, e.id_pedido, e.id_repartidor, e.fecha, e.hora, " +
                     "p.direccion, p.tipo, p.estado, r.nombre " +
                     "FROM entrega e " +
                     "INNER JOIN pedido p ON e.id_pedido = p.id " +
                     "INNER JOIN repartidor r ON e.id_repartidor = r.id";

        try (Connection conexion = ConexionBD.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                int idEntrega = rs.getInt("id");
                
                Pedido pedido = new Pedido(
                    rs.getInt("id_pedido"),
                    rs.getString("direccion"),
                    Tipo.valueOf(rs.getString("tipo").toUpperCase()),
                    Estado.valueOf(rs.getString("estado").toUpperCase())
                );
                
                Repartidor repartidor = new Repartidor(
                    rs.getInt("id_repartidor"),
                    rs.getString("nombre")
                );
                // aca se crea la entrega
                Entrega entrega = new Entrega(
                    idEntrega,
                    pedido,
                    repartidor,
                    rs.getDate("fecha").toLocalDate(),
                    rs.getTime("hora").toLocalTime()
                );
                entregas.add(entrega);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar entrega: " + e.getMessage());
        }
        return entregas;
    }

    //borrar entrega de la base de datos
    public boolean eliminarEntrega(int id){
        String sql = "delete from entrega where id = ?";

        try (Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql)){
            
            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            return filas>0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar entrega: " + e.getMessage());
            return false;
        }
    }

    //actualizar entrega completa
    public boolean actualizarEntrega(Entrega entrega){
        String sql = "update entrega set id_pedido = ?, id_repartidor = ?, fecha = ?, hora = ? where id = ?";

        try (Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql)){
            
            ps.setInt(1, entrega.getPedido().getId());
            ps.setInt(2, entrega.getRepartidor().getId());
            ps.setDate(3, java.sql.Date.valueOf(entrega.getFecha()));
            ps.setTime(4, java.sql.Time.valueOf(entrega.getHora()));
            ps.setInt(5, entrega.getId());

            int filas = ps.executeUpdate();
            return filas>0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar entrega: " + e.getMessage());
            return false;
        }
    }
}
