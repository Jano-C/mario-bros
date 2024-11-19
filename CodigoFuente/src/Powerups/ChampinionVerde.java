package Powerups;

import Fabricas.Sprite;
import Mario.MarioInvencible;
import Mario.MarioNormal;
import Mario.MarioParpadeante;
import Mario.SuperMario;
import Mario.SuperMarioFuego;
import Visitor.VisitorMario;

public class ChampinionVerde extends PowerUp {

	private static final int PUNTOS_MARIO = 100;

	public ChampinionVerde(Sprite sprite, int x, int y, int ancho, int alto) {
		super(sprite, x, y, ancho, alto);
	}

	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {
		visitorMario.visit(this);
	}

	@Override
	public void aplicarEfecto(MarioNormal marioNormal) {

		marioNormal.getMario().sumarVidas();
		marioNormal.sumarPuntos(PUNTOS_MARIO);
		marioNormal.getMario().getJuego().reproducirSonidoPowerUp();
		eliminarImagen();

	}

	@Override
	public void aplicarEfecto(SuperMario superMario) {

		superMario.getMario().sumarVidas();
		superMario.sumarPuntos(PUNTOS_MARIO);
		superMario.getMario().getJuego().reproducirSonidoPowerUp();
		eliminarImagen();

	}

	@Override
	public void aplicarEfecto(SuperMarioFuego superMarioFuego) {

		superMarioFuego.getMario().sumarVidas();
		superMarioFuego.sumarPuntos(PUNTOS_MARIO);
		superMarioFuego.getMario().getJuego().reproducirSonidoPowerUp();
		eliminarImagen();

	}

	@Override
	public void aplicarEfecto(MarioInvencible marioInvencible) {

		marioInvencible.getMario().sumarVidas();
		marioInvencible.sumarPuntos(PUNTOS_MARIO);
		marioInvencible.getMario().getJuego().reproducirSonidoPowerUp();
		eliminarImagen();

	}

	@Override
	public void aplicarEfecto(MarioParpadeante marioParpadeante) {

		marioParpadeante.sumarPuntos(PUNTOS_MARIO);
		eliminarImagen();

	}
}
