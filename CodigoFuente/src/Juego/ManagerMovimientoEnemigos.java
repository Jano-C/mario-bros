package Juego;

import Enemigo.Enemigo;
import Enemigo.Lakitu;
import Enemigo.Spiny;
import Plataforma.Plataforma;

public class ManagerMovimientoEnemigos extends Thread {

	protected Juego juego;
	private volatile boolean running;
	private static final int FPS = 60;
	private static final long FRAME_TIME = 1000 / FPS;

	public ManagerMovimientoEnemigos(Juego juego) {
		this.juego = juego;
//        managerColisionesEnemigo = new ManagerColisionesEnemigo(juego.getNivelActual().getEnemigos());
	}

	@Override
	public void run() {
		running = true;

		while (running) {
			long startTime = System.currentTimeMillis();

			juego.detectarColisionesEnemigosYManejar();
			for (Enemigo enemigo : juego.getNivelActual().getEnemigos()) {
				enemigo.actualizarPosicion();
			}

			for (Lakitu lakitu : juego.getNivelActual().getLakitus()) {
				lakitu.arrojarSpiny();
			}

			long timeTaken = System.currentTimeMillis() - startTime;
			long sleepTime = FRAME_TIME - timeTaken;
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	@Override
	public void interrupt() {
		running = false;
	}
}
