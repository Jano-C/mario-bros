package Mario;

import Enemigo.Enemigo;
import Entidades.EntidadDinamica;
import Fabricas.Sprite;
import Juego.Juego;
import Plataforma.LadrilloSolido;
import Plataforma.Plataforma;
import Visitor.VisitorBolaDeFuego;

public class BolaDeFuego extends EntidadDinamica implements VisitorBolaDeFuego{

	protected Juego juego;
	
	public BolaDeFuego(Sprite sprite, int x, int y, int ancho, int alto, Juego juego) {
		super(sprite, x, y, ancho, alto);
		this.velocidad=6;
		this.juego = juego;
		
	}
	 
	public void actualizarPosicion() {
		posicion.setX(posicion.getX()+velocidad);
		this.notificarObserver();		
	}

	public void setOrientacion(int orientacion) {
		velocidad = velocidad * orientacion;
	}

	@Override
	public void visit(Plataforma plataforma) {
		
		this.destruir();
		juego.getNivelActual().eliminarBolaDeFuego(this);
		
	}

	@Override
	public void visit(LadrilloSolido ladrilloSolido) {
		
		this.destruir();
		ladrilloSolido.serAfectadoPorBolaDeFuego();
		juego.reproducirSonidoLadrilloRoto();
		juego.getNivelActual().eliminarPlataforma(ladrilloSolido);
		
		
	}

	@Override
	public void visit(Enemigo enemigo) {
		
		this.destruir();
		enemigo.serAfectadoPorBolaDeFuego(juego.getNivelActual().getMario());
		juego.reproducirSonidoBump();
		juego.getNivelActual().eliminarEnemigo(enemigo);
		
	}
	
	public void destruir() {
		juego.getNivelActual().eliminarBolaDeFuego(this);
		observer.notificarMuerte();
	}

}
