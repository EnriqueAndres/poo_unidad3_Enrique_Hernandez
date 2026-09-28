package com.example.conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/speedfast_db";

    private static final String USUARIO = "root";
    private static final String CLAVE = "admin123";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
