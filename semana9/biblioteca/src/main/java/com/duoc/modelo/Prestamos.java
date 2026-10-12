package com.duoc.modelo;

import java.time.LocalDate;

public class Prestamos {

    private int id
    private Estudiantes id_estudiante;
    private Libros id_libro;
    private LocalDate fecha_prestamo;
    private LocalDate fecha_devolucion;
    private boolean devuelto;

    public Prestamos(int id, int id_estudiante, int id_libro, LocalDate fecha_prestamo, LocalDate fecha_devolucion, boolean devuelto) {
        this.id = id;
        this.id_estudiante = id_estudiante;
        this.id_libro = id_libro;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.devuelto = devuelto;
    }

    public int getId() {
        return id;
    }

    public Estudiantes getId_estudiante() {
        return id_estudiante;
    }

    public LocalDate getFecha_devolucion() {
        return fecha_devolucion;
    }

    public LocalDate getFecha_prestamo() {
        return fecha_prestamo;
    }

    public Libros getId_libro() {
        return id_libro;
    }

    public boolean isDevuelto() {
        return devuelto;
    }


    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    public void setFecha_devolucion(LocalDate fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public void setFecha_prestamo(LocalDate fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public void setId_estudiante(Estudiantes id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public void setId_libro(Libros id_libro) {
        this.id_libro = id_libro;
    }


}
