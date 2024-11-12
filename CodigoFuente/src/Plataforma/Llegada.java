package Plataforma;

import Fabricas.Sprite;
import Visitor.VisitorBolaDeFuego;
import Visitor.VisitorEnemigo;
import Visitor.VisitorMario;

public class Llegada extends Plataforma{

	public Llegada(Sprite sprite, int x,int y,int ancho ,int alto) {
		super(sprite,x,y,ancho,alto);
	}
	
	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {
		visitorMario.visit(this);
	}

	@Override
	public void acceptEnemigo(VisitorEnemigo visitorEnemigo) {
		
	}

	@Override
	public void acceptBolaDeFuego(VisitorBolaDeFuego visitorBolaDeFuego) {
		visitorBolaDeFuego.visit(this);
	};
}
