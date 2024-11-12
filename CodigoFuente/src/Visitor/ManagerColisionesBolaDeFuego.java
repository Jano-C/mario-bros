package Visitor;

import java.util.ArrayList;
import java.util.List;
import Auxiliares.MetodosAuxiliares;
import Enemigo.Enemigo;
import Entidades.EntidadLogica;
import Juego.Juego;
import Juego.Nivel;
import Mario.BolaDeFuego;
import Plataforma.LadrilloSolido;
import Plataforma.Plataforma;


public class ManagerColisionesBolaDeFuego implements VisitorBolaDeFuego {
	
	private List<Enemigo> enemigosAEliminar;
	private List<Plataforma> plataformasAEliminar;
	protected List<BolaDeFuego> listaBolasDeFuego;
	protected Nivel nivelActual;
	protected Juego juego;
	protected MetodosAuxiliares metodosAuxiliares;
	public ManagerColisionesBolaDeFuego(Juego juego) {
		this.juego = juego;
		this.nivelActual = juego.getNivelActual();
		this.listaBolasDeFuego = nivelActual.getBolasDeFuego();
		metodosAuxiliares = new MetodosAuxiliares();
		enemigosAEliminar = new ArrayList<Enemigo>();
		plataformasAEliminar = new ArrayList<Plataforma>();
	}
	
	public void actualizarLista(List<BolaDeFuego> bolasDeFuego) {
		listaBolasDeFuego = bolasDeFuego;
	}

	@Override
	public void visit(Plataforma plataforma) {
		chequearColision(plataforma);
	}
	
	@Override
	public void visit(LadrilloSolido ladrilloSolido) {
		if(chequearColision(ladrilloSolido)) {
			juego.reproducirSonidoLadrilloRoto();
			plataformasAEliminar.add(ladrilloSolido);
		}
	}

	@Override
	public void visit(Enemigo enemigo) {
		if(chequearColision(enemigo)) {
			juego.reproducirSonidoBump();
			enemigosAEliminar.add(enemigo);
			enemigo.serAfectadoPorBolaDeFuego(juego.getNivelActual().getMario());
		}
		
	}
	

	protected boolean chequearColision(EntidadLogica entidadLogica) {
		boolean algunaColisiono = false;
		for(BolaDeFuego bolaDeFuego : listaBolasDeFuego) {
			if(metodosAuxiliares.colisionaCon(bolaDeFuego, entidadLogica)) {
				metodosAuxiliares.ocultarImagen(bolaDeFuego);
				listaBolasDeFuego.remove(bolaDeFuego);
				nivelActual.getBolasDeFuego().remove(bolaDeFuego);
				algunaColisiono = true;
			}
		}
		return algunaColisiono;
	}
	
   public void eliminarEnemigos() {
       for (EntidadLogica enemigo : enemigosAEliminar) {
    	   metodosAuxiliares.ocultarImagen(enemigo);
    	   enemigo.notificarObserver();
    	   nivelActual.getEnemigos().remove(enemigo);
       }
       enemigosAEliminar.clear();
   }
   

	
   public void eliminarPlataformas() {
       for (EntidadLogica plataforma : plataformasAEliminar) {
    	   metodosAuxiliares.ocultarImagen(plataforma);
    	   plataforma.notificarObserver();
    	   nivelActual.getPlataformas().remove(plataforma);
       }
       plataformasAEliminar.clear();
   }
   

}
