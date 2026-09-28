package com.example;
import javax.swing.SwingUtilities;
import com.example.vista.VentanaPrincipal;

public class App 
{
    public static void main( String[] args )
    {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
