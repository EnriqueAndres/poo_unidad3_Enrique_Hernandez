package com.semana8.speedfast.modelo;

public class Pedido {

    private int id;
    private String direccion;
    private Tipo tipo;
    private Estado estado;

    //constructor
    public Pedido(int id, String direccion, Tipo tipo, Estado estado){
        this.id = id;
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = estado;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Estado getEstado() {
        return estado;
    }

    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
