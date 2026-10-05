package com.semana8.speedfast.controlador;
import java.util.ArrayList;
import java.util.List;
import com.semana8.speedfast.dao.EntregaDAO;
import com.semana8.speedfast.modelo.Entrega;

public class ControladorEntrega {

    private final List<Entrega> entregas = new ArrayList<>();
    private final EntregaDAO entregaDAO = new EntregaDAO();


    public boolean guardarEntrega(Entrega entrega){
        boolean registrado = entregaDAO.guardarEntrega(entrega);
        if (registrado){
            entregas.add(entrega);
        }
        return registrado;
    }

    // obtener entregas almacenadas
    public List<Entrega> getEntregas(){
        return entregaDAO.listarEntregas();
    }

    //actualizar entrega completa
    public boolean actualizarEntrega(Entrega entrega){
        return entregaDAO.actualizarEntrega(entrega);
    }

    //borrar entrega
    public boolean borrarEntrega(int id){
        return entregaDAO.eliminarEntrega(id);
    }
}
