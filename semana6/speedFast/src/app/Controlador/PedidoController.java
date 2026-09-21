package app.Controlador;
import java.util.ArrayList;
import java.util.List;
import app.Model.Pedido;


public class PedidoController {
    private List<Pedido> listaPedidos;

    public PedidoController(){
        this.listaPedidos = new ArrayList<>();
        listaPedidos.add(new Pedido(1, "Comida", "Av. Santiago 1234"));
        listaPedidos.add(new Pedido(2, "Encomienda", "Punta Arenas 555"));
    }

    public void agregarPedido(Pedido pedido){
        listaPedidos.add(pedido);
    }

    //getter
    public List<Pedido> getListaPedidos(){
        return listaPedidos;
    }
}
