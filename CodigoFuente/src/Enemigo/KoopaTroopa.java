package Enemigo;

import Fabricas.Sprite;
import Mario.Mario;
import Visitor.VisitorBolaDeFuego;
import Visitor.VisitorMario;

public class KoopaTroopa extends Enemigo {
	
	protected int vidas;

	public KoopaTroopa(Sprite sprite,Sprite derecha, Sprite izquierda, int x, int y, int ancho, int alto) {
		
		super(sprite,derecha,izquierda,x,y,ancho,alto);
		vidas = 2;
	}
	
	public int getVidas() {
		return vidas;
	}
	
	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {
		visitorMario.visit(this,lado);
	}
	
	public void atacar(Mario mario) {
		mario.recibirGolpe(45);
		mario.setAire(false);
		mario.saltar();
	}
	
	public void serAfectadoPorJugador(Mario mario) {
		vidas--;
		if(vidas == 0) {
			mario.sumarPuntaje(90);
		}
		mario.setAire(false);
		mario.saltar();
	}
	
	@Override
	public void serAfectadoPorBolaDeFuego(Mario mario) {
		mario.sumarPuntaje(90);
		
	}

	@Override
	public void acceptBolaDeFuego(VisitorBolaDeFuego visitorBolaDeFuego) {
		visitorBolaDeFuego.visit(this);
		
	}
}