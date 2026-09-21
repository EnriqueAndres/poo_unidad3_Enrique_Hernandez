package app.Controlador;

import java.util.ArrayList;
import java.util.List;

import app.Model.Repartidor;

public class RepartidorController {
    private List<Repartidor> listaRepartidor;

    public RepartidorController(){
        this.listaRepartidor = new ArrayList<>();
    }

    public void agregarRepartidor(Repartidor repartidor){
        listaRepartidor.add(repartidor);
    }

    //getter
    public List<Repartidor> getListaRepartidor(){
        return listaRepartidor;
    }
}
