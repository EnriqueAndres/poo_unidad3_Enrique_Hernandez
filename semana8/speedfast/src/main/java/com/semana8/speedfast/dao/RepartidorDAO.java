package com.semana8.speedfast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.semana8.speedfast.conexion.ConexionBD;
import com.semana8.speedfast.modelo.Repartidor;

public class RepartidorDAO {


    //listar todos los repartidores
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

    //agregar un nuevo repartidor
    public boolean agregarRepartidor(Repartidor repartidor){
        String sql = "insert into repartidor (nombre) VALUES (?)";

        try (Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql)){
            
            ps.setString(1, repartidor.getNombre());

            int filas = ps.executeUpdate();
            return filas>0;
        } catch (SQLException e) {
            System.out.println("Error al agregar repartidor: " + e.getMessage());
            return false;
        }
    }

    //actualizar un repartidor
    public boolean actualizarRepartidor(Repartidor repartidor){
        String sql = "update repartidor set nombre = ? where id = ?";

        try (Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql)){
            
            ps.setString(1, repartidor.getNombre());
            ps.setInt(2, repartidor.getId());

            int filas = ps.executeUpdate();
            return filas>0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar repartidor: " + e.getMessage());
            return false;
        }
    }

    //eliminar un repartidor
    public boolean eliminarRepartidor(int id){
        String sql = "delete from repartidor where id = ?";

        try (Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql)){
            
            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            return filas>0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar repartidor: " + e.getMessage());
            return false;
        }
    }
}
