package com.example.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Entrega {

    private int id;
    private Pedido pedido; //id_pedido
    private Repartidor repartidor; //id_repartidor
    private LocalDate fecha;
    private LocalTime hora;

    public Entrega(int id, Pedido pedido, Repartidor repartidor, LocalDate fecha, LocalTime hora) {
        this.id = id;
        this.pedido = pedido;
        this.repartidor = repartidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    //getters
    public int getId() {
        return id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    //setter

    public void setId(int id) {
        this.id = id;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
