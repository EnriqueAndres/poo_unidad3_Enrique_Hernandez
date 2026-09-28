package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.conexion.ConexionBD;
import com.example.modelo.Estado;
import com.example.modelo.Pedido;

import java.util.List;
import java.util.ArrayList;

public class PedidoDAO {

    //insertar pedido a base de datos
    public boolean guardarPedido(Pedido pedido){
        
        String sql = "insert into pedido " + "(direccion, tipo, estado) " + "VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql)){
            
            ps.setString(1, pedido.getDireccion());
            ps.setString(2, pedido.getTipo());
            ps.setString(3, pedido.getEstado().name());

            int filas = ps.executeUpdate();
            return filas>0;
        } catch (SQLException e) {

            System.out.println("Error al registrar producto: " + e.getMessage());
            return false;
        }
    }

    // obtener pedidos almacenados

    public List<Pedido> listarPedidos(){
        List<Pedido> pedidos = new ArrayList<>();

        String sql = "select id, direccion, tipo, estado " + "from pedido";

        try (Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            
            while(rs.next()){
                int id = rs.getInt("id");
                String direccion = rs.getString("direccion");
                String tipo = rs.getString("tipo");
                String estadoStr = rs.getString("estado");

                Estado estado = Estado.valueOf(estadoStr.toUpperCase());

                Pedido pedido = new Pedido(
                    id,
                    direccion,
                    tipo,
                    estado
                );

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
             System.out.println(  "Error al listar productos: "  + e.getMessage()
            );
        }
        return pedidos;
    }
}
