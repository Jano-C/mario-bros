package Plataforma;

import Fabricas.Sprite;
import Visitor.VisitablePorBolaDeFuego;
import Visitor.VisitorBolaDeFuego;
import Visitor.VisitorEnemigo;
import Visitor.VisitorMario;

public class LadrilloSolido extends Plataforma implements VisitablePorBolaDeFuego{

	public LadrilloSolido(Sprite sprite, int x,int y,int ancho ,int alto) {
		super(sprite,x,y,ancho,alto);
	}
	
	@Override
	public void acceptMario(VisitorMario visitorMario) {
		visitorMario.visit(this);
	}

	@Override
	public void acceptBolaDeFuego(VisitorBolaDeFuego visitorBolaDeFuego) {
		visitorBolaDeFuego.visit(this);
	}

	@Override
	public void acceptEnemigo(VisitorEnemigo visitorEnemigo) {
		visitorEnemigo.visit(this);
		
	};
}
