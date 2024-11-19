package Visitor;

import Plataforma.Plataforma;
import Plataforma.Vacio;

public interface VisitorEnemigo {

	public void visit(Plataforma plataforma, int lado);

	public void visit(Vacio vacio, int lado);

}
