package com.duoc.controlador;

import java.util.List;

import com.duoc.dao.EstudianteDAO;
import com.duoc.modelo.Estudiantes;

public class ControladorEstudiantes {

    private final EstudianteDAO estudianteDAO = new EstudianteDAO();

    public boolean guardarEstudiante(Estudiantes estudiante) {
        if (estudiante == null || estudiante.getRut() == null || estudiante.getRut().isBlank()) {
            return false;
        }
        return estudianteDAO.insertarEstudiante(estudiante);
    }

    public List<Estudiantes> getEstudiantes() {
        return estudianteDAO.listarEstudiantes();
    }

    public boolean actualizarEstudiante(Estudiantes estudiante) {
        if (estudiante == null || estudiante.getId() <= 0) {
            return false;
        }
        return estudianteDAO.actualizarEstudiante(estudiante);
    }

    public boolean borrarEstudiante(int id) {
        return estudianteDAO.eliminarEstudiante(id);
    }
}
