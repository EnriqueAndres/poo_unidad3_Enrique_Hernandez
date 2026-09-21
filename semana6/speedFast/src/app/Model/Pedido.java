package app.Model;

public class Pedido {

    private final int id;
    private String tipoPedido;
    private String direccionEntrega;
    

    public Pedido(int id, String tipoPedido, String direccionEntrega) {
        this.id = id;
        this.tipoPedido = tipoPedido;
        this.direccionEntrega = direccionEntrega;
    }
    //getters
    public int getIdPedido() {
        return this.id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public String getTipoPedido(){
        return tipoPedido;
    }

    //setters
    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    @Override 
    public String toString() {
        return "Pedido #" + id +", tipo pedido: "+ tipoPedido + ", Dirección: " + direccionEntrega;
    }


}
