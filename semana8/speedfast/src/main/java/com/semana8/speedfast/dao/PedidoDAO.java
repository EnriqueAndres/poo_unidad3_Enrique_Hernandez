package com.semana8.speedfast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.semana8.speedfast.conexion.ConexionBD;
import com.semana8.speedfast.modelo.Estado;
import com.semana8.speedfast.modelo.Pedido;
import com.semana8.speedfast.modelo.Tipo;

public class PedidoDAO {

    //insertar pedido a base de datos
    public boolean guardarPedido(Pedido pedido){
        
        String sql = "insert into pedido " + "(direccion, tipo, estado) " + "VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql)){
            
            ps.setString(1, pedido.getDireccion());
            ps.setString(2, pedido.getTipo().name());
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
                String tipoStr = rs.getString("tipo");
                String estadoStr = rs.getString("estado");

                Estado estado = Estado.valueOf(estadoStr.toUpperCase());
                Tipo tipo = Tipo.valueOf(tipoStr.toUpperCase());

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

    //actualizar pedido
    public boolean actualizarPedido(Pedido pedido){
        
        String sql = "update pedido set direccion = ?, tipo = ?, estado = ? where id = ?";

        try (Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql)){
            
            ps.setString(1, pedido.getDireccion());
            ps.setString(2, pedido.getTipo().name());
            ps.setString(3, pedido.getEstado().name());
            ps.setInt(4, pedido.getId());

            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el pedido: " + e.getMessage());
            return false;
        }
    }

    //borrar pedido
    public boolean borrarPedido(int id){
        String sql = "delete from pedido where id = ?";

        try (Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql)){
            
            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            return filas>0;
        } catch (SQLException e) {
            System.out.println("Error al borrar pedido: " + e.getMessage());
            return false;
        }
    
    }
}
