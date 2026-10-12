package com.duoc.controlador;

import java.util.List;

import com.duoc.dao.LibroDAO;
import com.duoc.modelo.Libros;

public class ControladorLibros {
    
    private final LibroDAO libroDAO = new LibroDAO();

    public boolean guardarLibro(Libros libro) {
        if (libro == null || libro.getTitulo() == null || libro.getTitulo().isBlank()) {
            return false;
        }
        return libroDAO.insertarLibro(libro);
    }

    public List<Libros> getLibros() {
        return libroDAO.listarLibros();
    }

    public boolean actualizarLibro(Libros libro) {
        if (libro == null || libro.getId() <= 0) {
            return false;
        }
        return libroDAO.actualizarLibro(libro);
    }

    public boolean borrarLibro(int id) {
        return libroDAO.eliminarLibro(id);
    }
}
