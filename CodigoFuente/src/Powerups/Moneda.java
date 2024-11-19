package Powerups;

import Fabricas.Sprite;
import Mario.MarioInvencible;
import Mario.MarioNormal;
import Mario.MarioParpadeante;
import Mario.SuperMario;
import Mario.SuperMarioFuego;
import Visitor.VisitorMario;

public class Moneda extends PowerUp {

	private static final int PUNTOS_MARIO_NORMAL = 5;

	public Moneda(Sprite sprite, int x, int y, int ancho, int alto) {
		super(sprite, x, y, ancho, alto);
	}

	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {
		visitorMario.visit(this);
	}

	@Override
	public void aplicarEfecto(MarioNormal marioNormal) {
		marioNormal.sumarPuntos(PUNTOS_MARIO_NORMAL);
		marioNormal.getMario().getJuego().reproducirSonidoAgarrarMoneda();
		eliminarImagen();
	}

	@Override
	public void aplicarEfecto(SuperMario superMario) {
		superMario.sumarPuntos(PUNTOS_MARIO_NORMAL);
		superMario.getMario().getJuego().reproducirSonidoAgarrarMoneda();
		eliminarImagen();
	}

	@Override
	public void aplicarEfecto(SuperMarioFuego superMarioFuego) {
		superMarioFuego.sumarPuntos(PUNTOS_MARIO_NORMAL);
		superMarioFuego.getMario().getJuego().reproducirSonidoAgarrarMoneda();
		eliminarImagen();
	}

	@Override
	public void aplicarEfecto(MarioInvencible marioInvencible) {
		marioInvencible.sumarPuntos(PUNTOS_MARIO_NORMAL);
		marioInvencible.getMario().getJuego().reproducirSonidoAgarrarMoneda();
		eliminarImagen();
	}

	@Override
	public void aplicarEfecto(MarioParpadeante marioParpadeante) {

		marioParpadeante.sumarPuntos(PUNTOS_MARIO_NORMAL);
		eliminarImagen();

	}
}
