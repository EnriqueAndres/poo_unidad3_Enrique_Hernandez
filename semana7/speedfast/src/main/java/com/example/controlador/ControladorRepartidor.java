package com.example.controlador;

import java.util.ArrayList;
import java.util.List;

import com.example.dao.RepartidorDAO;
import com.example.modelo.Repartidor;

public class ControladorRepartidor {

    private final RepartidorDAO repartidorDAO = new RepartidorDAO();

    //listar repartidores
    public List<Repartidor> getRepartidores(){
        return repartidorDAO.listarTodo();
    }
}
