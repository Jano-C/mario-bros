package Enemigo;

import Fabricas.FabricaSprites;
import Fabricas.Sprite;
import Juego.Juego;
import Mario.Mario;
import Visitor.VisitorBolaDeFuego;
import Visitor.VisitorMario;

public class Lakitu extends Enemigo {

	private static final int PUNTOS_A_SUMAR = 60;
	private static final int PUNTOS_A_RESTAR = 0;
	private static final long INTERVALO_ARROJAR_SPINY = 20000;

	protected long ultimoSpinyArrojado;
	protected int cantidadSpinys;
	protected Sprite izquierda, derecha;
	protected FabricaSprites fabricaSprites;
	protected Juego juego;

	public Lakitu(Sprite sprite, Sprite derecha, Sprite izquierda, int x, int y, int ancho, int alto, Juego juego) {
		super(sprite, derecha, izquierda, x, y, ancho, alto);
		cantidadSpinys = 5;
		gravedad = 0;
		ultimoSpinyArrojado = 0;
		this.juego = juego;
		this.fabricaSprites = juego.getFabricaSprites();
	}

	public void serAfectadoPorJugador(Mario mario) {
		mario.sumarPuntaje(PUNTOS_A_SUMAR);
		eliminarImagen();
	}

	public void atacar(Mario mario) {
		mario.recibirGolpe(PUNTOS_A_RESTAR);
		mario.setAire(false);
		mario.saltar();
	}

	public void arrojarSpiny() {
		if (cantidadSpinys > 0 && puedeArrojarSpiny() == true) {
			Spiny spiny = new Spiny(fabricaSprites.getSpiny(), fabricaSprites.getSpinyDerecha(),
					fabricaSprites.getSpinyIzquierda(), posicion.getX(), posicion.getY(), 32, 32);

			juego.registrarObserverEntidad(spiny);
			juego.getNivelActual().agregarEnemigo(spiny);
			cantidadSpinys--;
		}
	}

	private boolean puedeArrojarSpiny() {

		boolean toret = false;

		if (System.currentTimeMillis() > ultimoSpinyArrojado + INTERVALO_ARROJAR_SPINY) {
			ultimoSpinyArrojado = System.currentTimeMillis();
			toret = true;
		}
		return toret;
	}

	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {
		visitorMario.visit(this, lado);
	}

	@Override
	public void acceptBolaDeFuego(VisitorBolaDeFuego visitorBolaDeFuego, int lado) {
		visitorBolaDeFuego.visit(this);
	}

	@Override
	public void serAfectadoPorBolaDeFuego(Mario mario) {
		eliminarImagen();
		mario.sumarPuntaje(PUNTOS_A_SUMAR);
	}
}
