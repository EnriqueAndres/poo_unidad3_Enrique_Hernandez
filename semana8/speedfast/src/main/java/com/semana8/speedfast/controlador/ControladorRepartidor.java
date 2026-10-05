package com.semana8.speedfast.controlador;

import java.util.List;

import com.semana8.speedfast.dao.RepartidorDAO;
import com.semana8.speedfast.modelo.Repartidor;

public class ControladorRepartidor {

    private final RepartidorDAO repartidorDAO = new RepartidorDAO();

    //listar repartidores
    public List<Repartidor> getRepartidores(){
        return repartidorDAO.listarTodo();
    }

    //agregar repartidor
    public boolean agregarRepartidor(Repartidor repartidor){
        return repartidorDAO.agregarRepartidor(repartidor);
    }

    //actualizar repartidor
    public boolean actualizarRepartidor(Repartidor repartidor){
        return repartidorDAO.actualizarRepartidor(repartidor);
    }

    //borrar repartidor
    public boolean borrarRepartidor(int id){
        return repartidorDAO.eliminarRepartidor(id);
    }
}
