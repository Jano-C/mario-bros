package Vistas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Entidades.EntidadLogicaJugador;

public class KeyHandler implements KeyListener {

	protected EntidadLogicaJugador entidadLogicaJugador;

	public KeyHandler(EntidadLogicaJugador entidadLogicaJugador) {
		this.entidadLogicaJugador = entidadLogicaJugador;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// No usar.

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int evento = e.getKeyCode();
		switch (evento) {
		case KeyEvent.VK_A:
			entidadLogicaJugador.setMoviendoseIzquierda(true);
			entidadLogicaJugador.setOrientacion(-1);
			break;
		case KeyEvent.VK_D:
			entidadLogicaJugador.setMoviendoseDerecha(true);
			entidadLogicaJugador.setOrientacion(1);
			break;
		case KeyEvent.VK_W:
			entidadLogicaJugador.setSaltar(true);
			break;
		case KeyEvent.VK_SPACE:
			entidadLogicaJugador.crearBolaDeFuego();
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int evento = e.getKeyCode();
		switch (evento) {
		case KeyEvent.VK_A:
			entidadLogicaJugador.setMoviendoseIzquierda(false);
			break;
		case KeyEvent.VK_D:
			entidadLogicaJugador.setMoviendoseDerecha(false);
			break;
		case KeyEvent.VK_W:
			entidadLogicaJugador.setSaltar(false);
			break;
		}

	}

}
