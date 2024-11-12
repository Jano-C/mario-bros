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
	public void visit(BloqueSolido bloqueSolido);
	public void visit(BloqueDePreguntas bloqueDePreguntas);
	public void visit(LadrilloSolido ladrilloSolido);
	public void visit(Tuberia tuberia);
	public void visit(Vacio vacio);
	public void visit(BuzzyBeetle buzzyBeetle);
	public void visit(Goomba gommba);
	public void visit(KoopaTroopa koopaTroopa);
	public void visit(Lakitu lakitu);
	public void visit(PiranhaPlant piranhaPlant);
	public void visit(Spiny spiny);
	
	public void visit(ChampinionVerde champinionVerde);
	public void visit(Estrella estrella);
	public void visit(FlorDeFuego florDeFuego);
	public void visit(Moneda moneda);
	public void visit(SuperChampinion superChampinion);
	public void visit(Llegada llegada);
	
	
}
