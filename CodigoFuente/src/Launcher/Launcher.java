package Launcher;

import java.awt.EventQueue;

import Juego.Juego;
import Vistas.ControladorVistas;

public class Launcher {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juego juego = new Juego();
					ControladorVistas controladorVistas = new ControladorVistas(juego);
					juego.setControladorVistas(controladorVistas);
					controladorVistas.mostrarPantallaInicio();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
