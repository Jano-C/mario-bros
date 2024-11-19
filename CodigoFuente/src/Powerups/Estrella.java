package Powerups;

import Fabricas.Sprite;
import Mario.MarioInvencible;
import Mario.MarioNormal;
import Mario.MarioParpadeante;
import Mario.SuperMario;
import Mario.SuperMarioFuego;
import Visitor.VisitorMario;

public class Estrella extends PowerUp {

	private static final int PUNTOS_MARIO_NORMAL = 20;
	private static final int PUNTOS_MARIO_SUPERMARIO = 30;
	private static final int PUNTOS_MARIO_ESTRELLA = 35;

	public Estrella(Sprite sprite, int x, int y, int ancho, int alto) {
		super(sprite, x, y, ancho, alto);
	}

	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {
		visitorMario.visit(this);
	}

	@Override
	public void aplicarEfecto(MarioNormal marioNormal) {

		marioNormal.sumarPuntos(PUNTOS_MARIO_NORMAL);
		marioNormal.setEstado(new MarioInvencible(marioNormal.getMario(), marioNormal));
		marioNormal.getMario().getJuego().reproducirSonidoPowerUp();
		eliminarImagen();

	}

	@Override
	public void aplicarEfecto(SuperMario superMario) {

		superMario.sumarPuntos(PUNTOS_MARIO_SUPERMARIO);
		superMario.setEstado(new MarioInvencible(superMario.getMario(), superMario));
		superMario.getMario().getJuego().reproducirSonidoPowerUp();
		eliminarImagen();

	}

	@Override
	public void aplicarEfecto(SuperMarioFuego superMarioFuego) {

		superMarioFuego.sumarPuntos(PUNTOS_MARIO_SUPERMARIO);
		superMarioFuego.setEstado(new MarioInvencible(superMarioFuego.getMario(), superMarioFuego));
		superMarioFuego.getMario().getJuego().reproducirSonidoPowerUp();
		eliminarImagen();

	}

	@Override
	public void aplicarEfecto(MarioInvencible marioInvencible) {

		marioInvencible.sumarPuntos(PUNTOS_MARIO_ESTRELLA);
		marioInvencible.getMario().getJuego().reproducirSonidoPowerUp();
		eliminarImagen();

	}

	@Override
	public void aplicarEfecto(MarioParpadeante marioParpadeante) {

		marioParpadeante.sumarPuntos(PUNTOS_MARIO_NORMAL);
		eliminarImagen();

	}

}