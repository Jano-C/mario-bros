package Enemigo;

import Fabricas.Sprite;
import Mario.Mario;
import Plataforma.Plataforma;
import Visitor.VisitorBolaDeFuego;
import Visitor.VisitorMario;

public class PiranhaPlant extends Enemigo {

	private static final int PUNTOS_A_SUMAR = 30;
	private static final int PUNTOS_A_RESTAR = 30;

	protected boolean subiendo;
	protected int minimoYPosible;
	protected int maximoYPosible;

	public PiranhaPlant(Sprite sprite, Sprite izquierda, Sprite derecha, int x, int y, int ancho, int alto) {
		super(sprite, derecha, izquierda, x, y, ancho, alto);
		subiendo = false;
		velocidad = 1;
		minimoYPosible = y;
		maximoYPosible = y + alto;

	}

	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {

		visitorMario.visit(this, lado);

	}

	public void atacar(Mario mario) {
		mario.recibirGolpe(PUNTOS_A_RESTAR);
		mario.setAire(false);
		mario.saltar();
	}

	@Override
	public void actualizarPosicion() {

		if (subiendo == false) {
			velocidad = 1;
		} else {
			velocidad = -1;
		}

		if (posicion.getY() <= minimoYPosible) {
			subiendo = false;
		}
		if (posicion.getY() >= maximoYPosible) {
			subiendo = true;
		}

		posicion.setY(posicion.getY() + velocidad);

		this.notificarObserver();
	}

	@Override
	public void serAfectadoPorBolaDeFuego(Mario mario) {

		eliminarImagen();
		mario.sumarPuntaje(PUNTOS_A_SUMAR);

	}

	@Override
	public void visit(Plataforma plataforma, int lado) {
		// No debe hacer nada
	}

	@Override
	public void acceptBolaDeFuego(VisitorBolaDeFuego visitorBolaDeFuego, int lado) {
		visitorBolaDeFuego.visit(this);

	}

	@Override
	public void serAfectadoPorJugador(Mario mario) {
		eliminarImagen();
		mario.sumarPuntaje(PUNTOS_A_SUMAR);
	}

}
