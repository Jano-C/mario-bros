package Visitor;

import Enemigo.Enemigo;
import Plataforma.LadrilloSolido;
import Plataforma.Plataforma;

public interface VisitorBolaDeFuego {

	public void visit(Plataforma plataforma);
	public void visit(LadrilloSolido ladrilloSolido);
	public void visit(Enemigo enemigo);

}
