package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.conexion.ConexionBD;
import com.example.modelo.Entrega;

public class EntregaDAO {

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

}
