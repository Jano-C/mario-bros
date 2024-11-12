package Enemigo;

import Fabricas.Sprite;
import Mario.Mario;
import Visitor.VisitorBolaDeFuego;
import Visitor.VisitorMario;

public class PiranhaPlant extends Enemigo{
	protected boolean subiendo;
	protected int minimoYPosible;
	protected int maximoYPosible;
	private static final int PUNTOS_A_SUMAR = 30;
	private static final int PUNTOS_A_RESTAR = 30;

	public PiranhaPlant(Sprite sprite,Sprite izquierda, Sprite derecha, int x, int y, int ancho, int alto) {
		super(sprite,derecha,izquierda,x,y,ancho,alto);
		subiendo = false;
		velocidad = 1;
		minimoYPosible = y;
		maximoYPosible = y + alto;

	}
	
	@Override
	public void acceptMario(VisitorMario visitorMario) {
		visitorMario.visit(this);
	}
	
	public void atacar(Mario mario) {
		mario.recibirGolpe(PUNTOS_A_RESTAR);
		mario.setAire(false);
		mario.saltar();
	}
	
	@Override
	public void actualizarPosicion() {
		
		if(subiendo == false) {
			velocidad = 1;
		}else {
			velocidad = -1;
		}
		
		if(posicion.getY() <= minimoYPosible) {
			subiendo = false;
		}
		if(posicion.getY() >= maximoYPosible) {
			subiendo = true;
		}
		
		posicion.setY(posicion.getY() + velocidad);

		this.notificarObserver();
	}
	
	@Override
	public void serAfectadoPorBolaDeFuego(Mario mario) {
		mario.sumarPuntaje(PUNTOS_A_SUMAR);
		
	}


	@Override
	public void acceptBolaDeFuego(VisitorBolaDeFuego visitorBolaDeFuego) {
		visitorBolaDeFuego.visit(this);
		
	}

	@Override
	public void serAfectadoPorJugador(Mario mario) {
		mario.setAire(false);
		mario.saltar();
	}
	
}
