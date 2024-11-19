package Entidades;

import Fabricas.Sprite;

public abstract class EntidadDinamica extends Entidad {

	protected int velocidad;
	protected int vida;

	public EntidadDinamica(Sprite sprite, int x, int y, int ancho, int alto) {
		super(sprite, x, y, ancho, alto);
	}

	public void setVelocidadd(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

}
