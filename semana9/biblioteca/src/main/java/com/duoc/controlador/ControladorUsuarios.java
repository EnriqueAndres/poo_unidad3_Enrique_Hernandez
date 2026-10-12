package com.duoc.controlador;

import java.util.List;

import com.duoc.modelo.Usuarios;

public class ControladorUsuarios {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Método para el login del sistema
    public Usuarios iniciarSesion(String correo, String contraseña) {
        if (correo == null || correo.isBlank() || contraseña == null || contraseña.isBlank()) {
            return null;
        }
        return usuarioDAO.autenticar(correo, contraseña);
    }

    public boolean registrarUsuario(Usuarios usuario) {
        if (usuario == null || usuario.getCorreo() == null || usuario.getCorreo().isBlank()) {
            return false;
        }
        return usuarioDAO.insertarUsuario(usuario);
    }

    public List<Usuarios> getUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    public boolean actualizarUsuario(Usuarios usuario) {
        return usuarioDAO.actualizarUsuario(usuario);
    }

    public boolean borrarUsuario(int id) {
        return usuarioDAO.eliminarUsuario(id);
    }
}
