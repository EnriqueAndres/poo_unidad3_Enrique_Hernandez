package com.semana8.speedfast.controlador;

import java.util.ArrayList;
import java.util.List;

import com.semana8.speedfast.dao.PedidoDAO;
import com.semana8.speedfast.modelo.Pedido;

public class ControladorPedidos {

    private final List<Pedido> pedidos = new ArrayList<>();

    private final PedidoDAO pedidoDAO = new PedidoDAO();

    //guardar o registrar pedido || Persistencia.
    public boolean guardarPedido(Pedido pedido){
        boolean registrado = pedidoDAO.guardarPedido(pedido);
        if (registrado){
            pedidos.add(pedido);
        }
        return registrado;
    }

    //listar pedidos
    public List<Pedido> getPedidos(){
        return pedidoDAO.listarPedidos();
    }

    //actualizar Pedido
    public boolean actualizarPedido(Pedido pedido){
        return pedidoDAO.actualizarPedido(pedido);
    }

    //borrar pedido
    public boolean borrarPedido(int id){
        return pedidoDAO.borrarPedido(id);
    }
}
