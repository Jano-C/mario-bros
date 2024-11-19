package Enemigo;

import Fabricas.Sprite;
import Mario.Mario;
import Visitor.VisitorBolaDeFuego;
import Visitor.VisitorMario;

public class BuzzyBeetle extends Enemigo {

	private static final int PUNTOS_A_SUMAR = 30;
	private static final int PUNTOS_A_RESTAR = 15;

	protected Sprite izquierda, derecha;

	public BuzzyBeetle(Sprite sprite, Sprite derecha, Sprite izquierda, int x, int y, int ancho, int alto) {
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
	public void acceptBolaDeFuego(VisitorBolaDeFuego visitorBolaDeFuego, int lado) {

		eliminarImagen();
		visitorBolaDeFuego.visit(this);

	}

	@Override
	public void serAfectadoPorBolaDeFuego(Mario mario) {
		mario.sumarPuntaje(PUNTOS_A_SUMAR);
	}

}
