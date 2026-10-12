package com.duoc.controlador;

import com.duoc.dao.LibroDAO;
import com.duoc.dao.PrestamoDAO;
import com.duoc.modelo.Prestamos;

public class ControladorPrestamos {

    private final PrestamoDAO prestamoDAO = new PrestamoDAO();
    private final LibroDAO libroDAO = new LibroDAO();

    public synchronized void registrarPrestamoConcurrente(Prestamos prestamo, int stockLibro, Runnable onComplete) {
        Thread hiloPrestamo = new Thread(() -> {
            try {
                if (stockActualLibro > 0) {
                    boolean guardado = prestamoDAO.insertarPrestamo(prestamo);
                    if (guardado) {
                        System.out.println("Préstamo procesado exitosamente en segundo plano (Hilo: " + Thread.currentThread().getName() + ")");
                    }
                } else {
                    System.out.println("Operación fallida: No hay stock disponible.");
                }
                if (onComplete != null) {
                    onComplete.run();
                }
            } catch (Exception e) {
                System.out.println("Error en hilo de préstamo: " + e.getMessage());
            }
        });
        hiloPrestamo.start();
    }
}
