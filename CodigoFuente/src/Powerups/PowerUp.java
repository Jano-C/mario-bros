package Powerups;

import Entidades.Entidad;
import Fabricas.Sprite;
import Mario.MarioInvencible;
import Mario.MarioNormal;
import Mario.MarioParpadeante;
import Mario.SuperMario;
import Mario.SuperMarioFuego;
import Visitor.Visitable;

public abstract class PowerUp extends Entidad implements Visitable {

	public PowerUp(Sprite sprite, int x, int y, int ancho, int alto) {
		super(sprite, x, y, ancho, alto);
	}

	public abstract void aplicarEfecto(MarioNormal marioNormal);

	public abstract void aplicarEfecto(SuperMario superMario);

	public abstract void aplicarEfecto(SuperMarioFuego superMarioFuego);

	public abstract void aplicarEfecto(MarioInvencible marioInvencible);

	public abstract void aplicarEfecto(MarioParpadeante marioParpadeante);

	public int getX() {
		return posicion.getX();
	}

	public int getY() {
		return posicion.getY();
	}

	public void setX(int a) {
		posicion.setX(a);
	}

	public void setY(int y) {
		posicion.setY(y);
	}

	public void setAncho(int a) {
		ancho = a;
	}

	public void setAlto(int a) {
		alto = a;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	protected void eliminarImagen() {
		observer.notificarMuerte();
	}

}
