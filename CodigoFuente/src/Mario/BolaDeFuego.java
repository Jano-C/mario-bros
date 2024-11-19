package Mario;

import Auxiliares.DetectorColisiones;
import Enemigo.Enemigo;
import Entidades.EntidadDinamica;
import Fabricas.Sprite;
import Juego.Juego;
import Plataforma.LadrilloSolido;
import Plataforma.Plataforma;
import Visitor.VisitorBolaDeFuego;

public class BolaDeFuego extends EntidadDinamica implements VisitorBolaDeFuego {

	protected Juego juego;
	protected int gravedad;
	protected int maximoY;

	public BolaDeFuego(Sprite sprite, int x, int y, int ancho, int alto, Juego juego) {
		super(sprite, x, y, ancho, alto);
		this.velocidad = 6;
		this.juego = juego;
		this.gravedad = 1;
		this.maximoY = y;

	}

	public void actualizarPosicion() {
		posicion.setX(posicion.getX() + velocidad);
		posicion.setY(posicion.getY() + gravedad);
		chequearMaximoY();
		this.notificarObserver();
	}

	public void setOrientacion(int orientacion) {
		velocidad = velocidad * orientacion;
	}

	private void chequearMaximoY() {
		if (this.getY() <= maximoY) {
			gravedad = 1;
		}
	}

	@Override
	public void visit(Plataforma plataforma, int lado) {

		if (lado == DetectorColisiones.ABAJO_4) {
			gravedad = gravedad * -1;
		} else {
			this.destruir();
			juego.getNivelActual().eliminarBolaDeFuego(this);
		}

	}

	@Override
	public void visit(LadrilloSolido ladrilloSolido, int lado) {

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
