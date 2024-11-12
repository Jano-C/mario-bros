package Juego;

import Enemigo.Enemigo;
import Mario.Mario;
import Plataforma.Plataforma;
import Powerups.PowerUp;
import Visitor.ManagerColisionesMario;

public class ManagerMovimientoMario extends Thread {
	
	protected Juego juego;
	ManagerColisionesMario managerColisionesMario;
	protected boolean marioEstaEnEstrella;
	private volatile boolean running;
	protected long inicioMarioEstrella;
	protected long finalMarioEstrella;
	public ManagerMovimientoMario(Juego juego) {
		this.juego = juego;
		this.marioEstaEnEstrella = false;
		managerColisionesMario  = new ManagerColisionesMario(juego);
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
					
					
					
					Mario mario = juego.getNivelActual().getMario();
	                mario.actualizarPosicion();
	                
	                for (Plataforma plataforma : juego.getNivelActual().getPlataformas()) {
	                    plataforma.acceptMario(managerColisionesMario);
	                }
	                
	                for (Enemigo enemigo : juego.getNivelActual().getEnemigos()) {
	                    enemigo.acceptMario(managerColisionesMario);
	                }
	                
	                for (PowerUp powerUp : juego.getNivelActual().getPowerUps()) {
	                	powerUp.acceptMario(managerColisionesMario);
	                }
	                
	                managerColisionesMario.eliminarEnemigos();
	                managerColisionesMario.eliminarPowerUps();
	                managerColisionesMario.eliminarPlataformas();
	                
	                if(marioEstaEnEstrella) {
	                	if(inicioMarioEstrella >= finalMarioEstrella) {
	                		mario.setEstado(mario.getEstado().getEstadoAnterior());
	                		mario.actualizarTamano();
	                		marioEstaEnEstrella = false;
	                	}
	                	inicioMarioEstrella = System.currentTimeMillis();
	                }
	                
	                delta--;
				}
				
			}
		}
		
		
		@Override
	    public void interrupt() {
	        running = false;
	        super.interrupt();
	    }
		
		public void marioEstaEnEstrella() {
			marioEstaEnEstrella = true;
			inicioMarioEstrella = System.currentTimeMillis();
			finalMarioEstrella = inicioMarioEstrella + 10000;
			
		}
		
		
}