package Powerups;

import Fabricas.Sprite;
import Mario.MarioInvencible;
import Mario.MarioNormal;
import Mario.MarioParpadeante;
import Mario.SuperMario;
import Mario.SuperMarioFuego;
import Visitor.VisitorMario;

public class FlorDeFuego extends PowerUp {

	private static final int PUNTOS_MARIO_NORMAL = 5;
	private static final int PUNTOS_SUPERMARIO = 30;
	private static final int PUNTOS_SUPERMARIOFUEGO = 50;

	public FlorDeFuego(Sprite sprite, int x, int y, int ancho, int alto) {
		super(sprite, x, y, ancho, alto);
	}

	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {
		visitorMario.visit(this);
	}

	@Override
	public void aplicarEfecto(MarioNormal marioNormal) {

		marioNormal.sumarPuntos(PUNTOS_MARIO_NORMAL);
		marioNormal.setEstado(new SuperMarioFuego(marioNormal.getMario()));
		marioNormal.getMario().getJuego().reproducirSonidoPowerUp();
		eliminarImagen();

	}

	@Override
	public void aplicarEfecto(SuperMario superMario) {

		superMario.sumarPuntos(PUNTOS_SUPERMARIO);
		superMario.setEstado(new SuperMarioFuego(superMario.getMario()));
		superMario.getMario().getJuego().reproducirSonidoPowerUp();
		eliminarImagen();

	}

	@Override
	public void aplicarEfecto(SuperMarioFuego superMarioFuego) {

		superMarioFuego.sumarPuntos(PUNTOS_SUPERMARIOFUEGO);
		superMarioFuego.setEstado(new SuperMarioFuego(superMarioFuego.getMario()));
		superMarioFuego.getMario().getJuego().reproducirSonidoPowerUp();
		eliminarImagen();
	}

	@Override
	public void aplicarEfecto(MarioInvencible marioInvencible) {

		marioInvencible.sumarPuntos(PUNTOS_MARIO_NORMAL);
		eliminarImagen();

	}

	@Override
	public void aplicarEfecto(MarioParpadeante marioParpadeante) {

		marioParpadeante.sumarPuntos(PUNTOS_MARIO_NORMAL);
		eliminarImagen();

	}

}
