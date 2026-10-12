package com.duoc.modelo;

public class Categorias {

    private int id;
    private String nombre;

    public Categorias(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
