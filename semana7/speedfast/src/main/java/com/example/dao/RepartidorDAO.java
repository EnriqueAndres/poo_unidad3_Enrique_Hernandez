package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.conexion.ConexionBD;
import com.example.modelo.Repartidor;
import java.sql.SQLException;

public class RepartidorDAO {
    
    public List<Repartidor> listarTodo(){

        List<Repartidor> repartidores = new ArrayList<>();

        String sql = "select id, nombre " + "from repartidor";

        try (Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            
            while (rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                Repartidor repartidor = new Repartidor(id, nombre);

                repartidores.add(repartidor);
            }
        } catch (SQLException e) {
            System.out.println(  "Error al listar repartidores: "  + e.getMessage()
            );
        }

        return repartidores;
    }
}
