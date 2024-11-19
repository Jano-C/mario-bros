package Visitor;

import Enemigo.BuzzyBeetle;
import Enemigo.Goomba;
import Enemigo.KoopaTroopa;
import Enemigo.Lakitu;
import Enemigo.PiranhaPlant;
import Enemigo.Spiny;
import Plataforma.BloqueDePreguntas;
import Plataforma.BloqueSolido;
import Plataforma.LadrilloSolido;
import Plataforma.Llegada;
import Plataforma.Tuberia;
import Plataforma.Vacio;
import Powerups.ChampinionVerde;
import Powerups.Estrella;
import Powerups.FlorDeFuego;
import Powerups.Moneda;
import Powerups.SuperChampinion;

public interface VisitorMario {
	public void visit(BloqueSolido bloqueSolido, int lado);

	public void visit(BloqueDePreguntas bloqueDePreguntas, int lado);

	public void visit(LadrilloSolido ladrilloSolido, int lado);

	public void visit(Tuberia tuberia, int lado);

	public void visit(Vacio vacio, int lado);

	public void visit(BuzzyBeetle buzzyBeetle, int lado);

	public void visit(Goomba gommba, int lado);

	public void visit(KoopaTroopa koopaTroopa, int lado);

	public void visit(Lakitu lakitu, int lado);

	public void visit(PiranhaPlant piranhaPlant, int lado);

	public void visit(Spiny spiny, int lado);

	public void visit(ChampinionVerde champinionVerde);

	public void visit(Estrella estrella);

	public void visit(FlorDeFuego florDeFuego);

	public void visit(Moneda moneda);

	public void visit(SuperChampinion superChampinion);

	public void visit(Llegada llegada);

}
