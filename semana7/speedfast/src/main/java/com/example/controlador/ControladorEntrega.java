package com.example.controlador;

import com.example.dao.EntregaDAO;
import com.example.modelo.Entrega;
import java.util.List;
import java.util.ArrayList;

public class ControladorEntrega {
    
    public final List<Entrega> entregas = new ArrayList<>();
    public final EntregaDAO entregaDAO = new EntregaDAO();

    //guardar entrega
    public boolean saveEntrega(Entrega entrega){
        boolean registro = entregaDAO.guardarEntrega(entrega);
        if (registro){
            entregas.add(entrega);
        }
        return registro;
    }
}
