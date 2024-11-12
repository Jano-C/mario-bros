package Visitor;

import java.util.List;
import Enemigo.Enemigo;
import Entidades.EntidadLogica;
import Plataforma.BloqueDePreguntas;
import Plataforma.BloqueSolido;
import Plataforma.LadrilloSolido;
import Plataforma.Tuberia;


public class ManagerColisionesEnemigo{
	
//	protected List<Enemigo> enemigos;
//	MetodosAuxiliares metodosAuxiliares;
//	public ManagerColisionesEnemigo(List<Enemigo> enemigos) {
//		metodosAuxiliares = new MetodosAuxiliares();
//		this.enemigos = enemigos;
//	}
//	
//	public void actualizarLista(List<Enemigo> enemigos) {
//		this.enemigos = enemigos;
//	}
//
//	@Override
//	public void visit(BloqueSolido bloqueSolido) {
//		chequearColsionYCambiarDireccion(bloqueSolido);
//		
//	}
//
//	@Override
//	public void visit(BloqueDePreguntas bloqueDePreguntas) {
//		chequearColsionYCambiarDireccion(bloqueDePreguntas);
//	}
//
//	@Override
//	public void visit(LadrilloSolido ladrilloSolido) {
//		chequearColsionYCambiarDireccion(ladrilloSolido);
//		
//	}
//
//	@Override
//	public void visit(Tuberia tuberia) {
//		chequearColsionYCambiarDireccion(tuberia);
//		
//	}
//	
//    private void chequearColsionYCambiarDireccion(EntidadLogica entidadLogica) {
//    	
//    	for (Enemigo e : enemigos) {
//        	
//    		if(metodosAuxiliares.colisionaConEntidadDerecha(e, entidadLogica)) {
//        		e.setMovingRight(false);
//        		e.cambiarSpriteEnColision();
//        	}
//    		if(metodosAuxiliares.colisionaConEntidadIzquierda(e, entidadLogica)) {
//        		e.setMovingRight(true);
//        		e.cambiarSpriteEnColision();
//        	}
//        	
//        	if(metodosAuxiliares.colisionaConEntidadAbajo(e, entidadLogica)) {
//        		if(e.getAire() == true) {
//        			moverEnemigoArriba(e);
//        		}
//        	}
//    	}
//    }
//    
//    private void moverEnemigoArriba(Enemigo enemigo) {
//    	enemigo.setY(enemigo.getY() - 1);
//    	enemigo.aterrizar();
//    	enemigo.setAire(false);
//    	enemigo.notificarObserver();
//    }

}
