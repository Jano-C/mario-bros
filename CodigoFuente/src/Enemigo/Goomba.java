package Enemigo;

import Fabricas.Sprite;
import Mario.Mario;
import Visitor.VisitorBolaDeFuego;
import Visitor.VisitorMario;

public class Goomba extends Enemigo {

	private static final int PUNTOS_A_SUMAR = 60;
	private static final int PUNTOS_A_RESTAR = 30;

	protected Sprite izquierda, derecha;

	public Goomba(Sprite sprite, Sprite derecha, Sprite izquierda, int x, int y, int ancho, int alto) {
		super(sprite, derecha, izquierda, x, y, ancho, alto);
	}

	public void atacar(Mario mario) {
		mario.recibirGolpe(PUNTOS_A_RESTAR);
		mario.setAire(false);
		mario.saltar();
	}

	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {

		visitorMario.visit(this, lado);

	}

	public void serAfectadoPorJugador(Mario mario) {
		eliminarImagen();
		mario.sumarPuntaje(PUNTOS_A_SUMAR);
	}

	@Override
	public void serAfectadoPorBolaDeFuego(Mario mario) {

		eliminarImagen();
		mario.sumarPuntaje(PUNTOS_A_SUMAR);
	}

	@Override
	public void acceptBolaDeFuego(VisitorBolaDeFuego visitorBolaDeFuego, int lado) {
		visitorBolaDeFuego.visit(this);
	}
}