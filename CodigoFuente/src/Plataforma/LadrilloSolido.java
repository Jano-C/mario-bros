package Plataforma;

import Fabricas.Sprite;
import Visitor.VisitablePorBolaDeFuego;
import Visitor.VisitorBolaDeFuego;
import Visitor.VisitorEnemigo;
import Visitor.VisitorMario;

public class LadrilloSolido extends Plataforma implements VisitablePorBolaDeFuego {

	public LadrilloSolido(Sprite sprite, int x, int y, int ancho, int alto) {
		super(sprite, x, y, ancho, alto);
	}

	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {
		visitorMario.visit(this, lado);
	}

	@Override
	public void acceptBolaDeFuego(VisitorBolaDeFuego visitorBolaDeFuego, int lado) {
		visitorBolaDeFuego.visit(this, lado);
	}

	@Override
	public void acceptEnemigo(VisitorEnemigo visitorEnemigo, int lado) {
		visitorEnemigo.visit(this, lado);

	};

	public void destruir() {
		observer.notificarMuerte();
	}

	public void serAfectadoPorBolaDeFuego() {
		this.destruir();
	}
}
