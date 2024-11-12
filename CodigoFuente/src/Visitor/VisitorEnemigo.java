package Visitor;

import Plataforma.BloqueDePreguntas;
import Plataforma.BloqueSolido;
import Plataforma.LadrilloSolido;
import Plataforma.Tuberia;

public interface VisitorEnemigo {
	public void visit(BloqueSolido bloqueSolido);
	public void visit(BloqueDePreguntas bloqueDePreguntas);
	public void visit(LadrilloSolido ladrilloSolido);
	public void visit(Tuberia tuberia);
}
