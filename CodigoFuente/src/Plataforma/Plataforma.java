package Plataforma;

import Entidades.Entidad;
import Fabricas.Sprite;
import Mario.BolaDeFuego;
import Mario.Mario;
import Visitor.Visitable;
import Visitor.VisitablePorBolaDeFuego;
import Visitor.VisitablePorEnemigos;

public abstract class Plataforma extends Entidad implements Visitable, VisitablePorEnemigos, VisitablePorBolaDeFuego {

	public Plataforma(Sprite sprite, int x, int y, int ancho, int alto) {
		super(sprite, x, y, ancho, alto);
	}

}
