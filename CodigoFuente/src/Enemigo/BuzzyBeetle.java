package Enemigo;

import Fabricas.Sprite;
import Mario.Mario;
import Visitor.VisitorBolaDeFuego;
import Visitor.VisitorMario;

public class BuzzyBeetle extends Enemigo{


	
	protected Sprite izquierda, derecha;
	
	public BuzzyBeetle(Sprite sprite,Sprite derecha, Sprite izquierda, int x, int y, int ancho, int alto) {
		super(sprite,derecha,izquierda,x,y,ancho,alto);

	}
	
	public void atacar(Mario mario) {
		mario.recibirGolpe(15);
		mario.setAire(false);
		mario.saltar();
	}
	
	
	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {
		visitorMario.visit(this, lado);
	}

	public void serAfectadoPorJugador(Mario mario) {
		mario.sumarPuntaje(30);
		mario.setAire(false);
		mario.saltar();
	}

	@Override
	public void acceptBolaDeFuego(VisitorBolaDeFuego visitorBolaDeFuego) {
		visitorBolaDeFuego.visit(this);
		
	}

	@Override
	public void serAfectadoPorBolaDeFuego(Mario mario) {
		mario.sumarPuntaje(30);
	}
	
}
