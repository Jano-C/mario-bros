package Plataforma;

import Fabricas.Sprite;
import Visitor.VisitorBolaDeFuego;
import Visitor.VisitorEnemigo;
import Visitor.VisitorMario;

public class Llegada extends Plataforma {

	public Llegada(Sprite sprite, int x, int y, int ancho, int alto) {
		super(sprite, x, y, ancho, alto);
	}

	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {
		visitorMario.visit(this);
	}

	@Override
	public void acceptEnemigo(VisitorEnemigo visitorEnemigo, int lado) {
		visitorEnemigo.visit(this, lado);
	}

	@Override
	public void acceptBolaDeFuego(VisitorBolaDeFuego visitorBolaDeFuego, int lado) {
		visitorBolaDeFuego.visit(this, lado);
	};
}
