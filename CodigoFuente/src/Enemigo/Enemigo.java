package Enemigo;

import Auxiliares.ConstantesAuxiliares;
import Auxiliares.DetectorColisiones;
import Entidades.EntidadDinamica;
import Entidades.EntidadLogica;
import Fabricas.Sprite;
import Mario.Mario;
import Plataforma.Llegada;
import Plataforma.Plataforma;
import Plataforma.Vacio;
import Visitor.Visitable;
import Visitor.VisitablePorBolaDeFuego;
import Visitor.VisitorEnemigo;

public abstract class Enemigo extends EntidadDinamica implements Visitable, VisitablePorBolaDeFuego, VisitorEnemigo {

	boolean enAire;
	float multiplicadorVelocidad;
	boolean moviendoseDerecha;
	protected boolean movingRight;
	protected int velocidad;
	protected int gravedad;
	protected Sprite izquierda, derecha;
	protected int ultimaPos;

	public Enemigo(Sprite sprite, Sprite derecha, Sprite izquierda, int x, int y, int ancho, int alto) {

		super(sprite, x, y, ancho, alto);
		this.izquierda = izquierda;
		this.derecha = derecha;
		movingRight = true;
		velocidad = 2;
		ultimaPos = x;
		enAire = true;
		gravedad = 1;

	}

	public void actualizarPosicion() {

		chequearSiTieneQueCaer();

		if (enAire == true) {
			posicion.setY(getY() + gravedad);
		}

		if (movingRight) {
			moverseDerecha();
		} else {
			moverseIzquierda();
		}
	}

	public void aterrizar() {
		enAire = false;
	}

	public boolean getAire() {
		return enAire;
	}

	public void setMovingRight(boolean right) {
		this.movingRight = right;
	}

	public boolean getMovingRight() {
		return this.movingRight;
	}

	public void moverseDerecha() {
		posicion.setX(posicion.getX() + velocidad);
		this.notificarObserver();
	}

	public void moverseIzquierda() {
		posicion.setX(posicion.getX() - velocidad);
		this.notificarObserver();
	}

	public void setAire(boolean b) {
		enAire = b;
	}

	private void cambiarSpriteEnColision() {
		if (movingRight) {
			sprite = derecha;
		} else {
			sprite = izquierda;
		}
	}

	@Override
	public void visit(Plataforma plataforma, int lado) {
		revisarColision(plataforma, lado);
	}

	@Override
	public void visit(Vacio vacio, int lado) {

		if (lado == DetectorColisiones.ABAJO_4) {
			cambiarDireccion();
			cambiarSpriteEnColision();
		}
	}

	private void revisarColision(EntidadLogica entidadLogica, int lado) {
		switch (lado) {
		case DetectorColisiones.DERECHA_1:
			movingRight = false;
			cambiarSpriteEnColision();
			break;
		case DetectorColisiones.IZQUIERDA_2:
			movingRight = true;
			cambiarSpriteEnColision();
			break;
		case DetectorColisiones.ARRIBA_3:

			break;
		case DetectorColisiones.ABAJO_4:
			corregirPosicionEnColisionPorEncima(entidadLogica);
			enAire = false;
			break;
		}
	}

	private void chequearSiTieneQueCaer() {
		if (posicion.getX() >= ultimaPos + ConstantesAuxiliares.TAMANOBLOQUE_ANCHO / 2) {
			enAire = true;
			ultimaPos = posicion.getX();
		}

		if (posicion.getX() <= ultimaPos - ConstantesAuxiliares.TAMANOBLOQUE_ANCHO / 2) {
			enAire = true;
			ultimaPos = posicion.getX();
		}
	}

	private void cambiarDireccion() {

		if (movingRight == true) {
			movingRight = false;
		} else if (movingRight == false) {
			movingRight = true;
		}

	}

	private void corregirPosicionEnColisionPorEncima(EntidadLogica entidadColisionada) {
		this.setY(entidadColisionada.getY() - this.getAlto());
	}

	public void eliminarImagen() {
		observer.notificarMuerte();
	}

	public abstract void serAfectadoPorBolaDeFuego(Mario mario);

	public abstract void serAfectadoPorJugador(Mario mario);
}
