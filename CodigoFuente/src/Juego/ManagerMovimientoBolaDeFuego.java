package Juego;

import Enemigo.Enemigo;
import Mario.BolaDeFuego;
import Plataforma.Plataforma;
import Visitor.ManagerColisionesBolaDeFuego;

public class ManagerMovimientoBolaDeFuego extends Thread {
	
	protected Juego juego;
	private volatile boolean running;
	protected ManagerColisionesBolaDeFuego managerColisionesBolaDeFuego;
	
	public ManagerMovimientoBolaDeFuego(Juego juego) {
		this.juego = juego;
		managerColisionesBolaDeFuego = new ManagerColisionesBolaDeFuego(juego);
	}
	
	
	@Override
	public void run() {
		running = true;
		double drawInterval =  1000000000/60;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		while(running == true) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta > 1) {
				
				managerColisionesBolaDeFuego.actualizarLista(juego.getNivelActual().getBolasDeFuego());
			
				for(BolaDeFuego bolaDeFuego : juego.getNivelActual().getBolasDeFuego()) {
					bolaDeFuego.actualizarPosicion();
				}
				
				for(Enemigo enemigo : juego.getNivelActual().getEnemigos()) {
					enemigo.acceptBolaDeFuego(managerColisionesBolaDeFuego);
				}
				
				for(Plataforma plataforma : juego.getNivelActual().getPlataformas()) {
					plataforma.acceptBolaDeFuego(managerColisionesBolaDeFuego);
				}
				
				managerColisionesBolaDeFuego.eliminarEnemigos();
				managerColisionesBolaDeFuego.eliminarPlataformas();
                delta--;
			}
			
		}
	}
	
	@Override
    public void interrupt() {
        running = false;
        super.interrupt();
    }
	
}
