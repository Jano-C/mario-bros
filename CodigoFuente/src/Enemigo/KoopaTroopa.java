package Enemigo;

import Fabricas.Sprite;
import Mario.Mario;
import Visitor.VisitorBolaDeFuego;
import Visitor.VisitorMario;

public class KoopaTroopa extends Enemigo {
	
	protected int vidas;
	private static final int PUNTOS_A_SUMAR = 90;
	private static final int PUNTOS_A_RESTAR = 45;

	public KoopaTroopa(Sprite sprite,Sprite derecha, Sprite izquierda, int x, int y, int ancho, int alto) {
		
		super(sprite,derecha,izquierda,x,y,ancho,alto);
		vidas = 2;
	}
	
	public int getVidas() {
		return vidas;
	}
	
	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {
		visitorMario.visit(this);
	}
	
	public void atacar(Mario mario) {
		mario.recibirGolpe(PUNTOS_A_RESTAR);
		mario.setAire(false);
		mario.saltar();
	}
	
	public void serAfectadoPorJugador(Mario mario) {
		vidas--;
		if(vidas == 0) {
			mario.sumarPuntaje(PUNTOS_A_SUMAR);
		}
		mario.setAire(false);
		mario.saltar();
	}
	
	@Override
	public void serAfectadoPorBolaDeFuego(Mario mario) {
		mario.sumarPuntaje(PUNTOS_A_SUMAR);
		
	}

	@Override
	public void acceptBolaDeFuego(VisitorBolaDeFuego visitorBolaDeFuego) {
		visitorBolaDeFuego.visit(this);
		
	}
}