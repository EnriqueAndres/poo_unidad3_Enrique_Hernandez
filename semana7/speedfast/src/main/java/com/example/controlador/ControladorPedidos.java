package com.example.controlador;

import java.util.List;
import java.util.ArrayList;

import com.example.dao.PedidoDAO;
import com.example.modelo.Pedido;

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
}
