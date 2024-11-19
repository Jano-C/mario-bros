package Entidades;

import Fabricas.Sprite;
import Vistas.Observer;
import Vistas.ObserverGrafico;
import Auxiliares.Posicion;

public abstract class Entidad implements EntidadLogica {

	protected Posicion posicion;
	protected int ancho;
	protected int alto;
	protected Observer observer;
	protected Sprite sprite;
	protected int spriteActual;

	public Entidad(Sprite sprite, int x, int y, int ancho, int alto) {
		this.sprite = sprite;
		posicion = new Posicion(x, y);
		this.ancho = ancho;
		this.alto = alto;
		this.spriteActual = 0;

	}

	public void setX(int x) {
		posicion.setX(x);
	}

	public void setY(int y) {
		posicion.setY(y);
	}

	public int getX() {
		return posicion.getX();
	}

	public int getY() {
		return posicion.getY();
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return this.ancho;
	}

	public int getAlto() {
		return this.alto;
	}

	public Sprite getSprite() {
		return this.sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void notificarObserver() {
		observer.actualizar();
		observer.actualizarSoloImagen();
	}

	public void registrarObserver(Observer observer) {
		this.observer = observer;
	}

	public int getSpriteActual() {
		return spriteActual;
	}

	public Observer getObserver() {
		return observer;
	}

}
