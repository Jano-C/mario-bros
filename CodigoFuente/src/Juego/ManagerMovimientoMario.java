package Juego;

import Mario.Mario;

public class ManagerMovimientoMario extends Thread {

	protected Juego juego;
	private volatile boolean running;

	public ManagerMovimientoMario(Juego juego) {

		this.juego = juego;
	}

	@Override
	public void run() {
		Mario mario = juego.getNivelActual().getMario();
		running = true;
		double drawInterval = 1000000000 / 60;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		while (running == true) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			if (delta > 1) {

				juego.detectarColisionesMarioYManejar();
				mario.moverse();

				delta--;
			}

		}
	}

	@Override
	public void interrupt() {
		running = false;
	}

}