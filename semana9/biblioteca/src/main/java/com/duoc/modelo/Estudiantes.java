package com.duoc.modelo;

public class Estudiantes extends Persona{

   private String curso;

    public Estudiantes(int id, String nombre, String rut, String curso, String correo) {
        super(id, nombre, rut, correo);
        this.curso = curso;
    }

    public String getCurso() { 
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String obtenerDetalleRol() {
        return "Estudiante del curso: " + curso;
    }

}
