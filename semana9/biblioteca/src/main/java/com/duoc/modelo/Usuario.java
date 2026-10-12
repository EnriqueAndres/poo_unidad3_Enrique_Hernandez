package com.duoc.modelo;


public class Usuarios {

    private int id;
    private String nombre;
    private String rut;
    private String correo;
    private String contraseña;
    private Rol rol;

    public Usuarios(int id, String nombre, String rut, String correo, String contraseña, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.rut = rut;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    //getters
    public int getId() {
        return id; 
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public Rol getRol() {
        return rol;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

}



