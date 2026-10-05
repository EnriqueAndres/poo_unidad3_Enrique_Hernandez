package com.semana8.speedfast;

import javax.swing.SwingUtilities;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.semana8.speedfast.vista.VentanaPrincipal;

@SpringBootApplication
public class SpeedfastApplication {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.setVisible(true);
        });
	}

}
