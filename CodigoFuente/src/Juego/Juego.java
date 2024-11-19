package Juego;

import java.util.List;

import Auxiliares.DetectorColisiones;
import Enemigo.Enemigo;
import Entidades.Entidad;
import Entidades.EntidadLogica;
import Fabricas.FabricaEntidades;
import Fabricas.FabricaSonidos;
import Fabricas.FabricaSprites;
import GeneradorNiveles.GeneradorNiveles;
import Mario.BolaDeFuego;
import Mario.Mario;
import Plataforma.Plataforma;
import Powerups.PowerUp;
import Ranking.Ranking;
import Vistas.ControladorVistas;
import Vistas.KeyHandler;
import Vistas.Observer;
import Vistas.ObserverGrafico;

public class Juego {

	protected ControladorVistas controladorVistas;
	protected GeneradorNiveles generadorNivel;
	protected FabricaSprites fabricaSprites;
	protected FabricaEntidades fabricaEntidades;
	protected Nivel nivelActual;
	protected ManagerMovimientoMario managerMovimientoMario;
	protected ManagerMovimientoEnemigos managerMovimientoEnemigos;
	protected Jugador jugador;
	protected Ranking ranking;
	protected String nombre;
	protected ManagerMovimientoBolaDeFuego managerMovimientoBolaDeFuego;
	protected FabricaSonidos fabricaSonidos;
	protected DetectorColisiones detectorColisiones;

	public Juego() {
		ranking = Ranking.getInstancia();
		ranking.cargarRankingDesdeArchivo();
		fabricaSonidos = new FabricaSonidos();
		detectorColisiones = new DetectorColisiones();
	}

	public void iniciarMusicaJuego() {
		fabricaSonidos.reproducirEnBucle("musicafondo");
	}

	public void detenerMusicaJuego() {
		fabricaSonidos.detener("musicafondo");
	}

	public void iniciarMusicaMenu() {
		fabricaSonidos.reproducir("menu");
	}

	public void detenerMusicaMenu() {
		fabricaSonidos.detener("menu");
	}

	public void sonidoBolaDeFuego() {
		fabricaSonidos.reproducir("bolafuego");
	}

	public void reproducirSonidoAgarrarMoneda() {
		fabricaSonidos.reproducir("moneda");
	}

	public void reproducirSonidoSalto() {
		fabricaSonidos.reproducir("salto");
	}

	public void reproducirSonidoPowerUp() {
		fabricaSonidos.reproducir("powerup");
	}

	public void reproducirSonidoMuerteMario() {
		fabricaSonidos.reproducir("muerte");
	}

	public void reproducirSonidoBump() {
		fabricaSonidos.reproducir("bump");
	}

	public void reproducirSonidoLadrilloRoto() {
		fabricaSonidos.reproducir("ladrillo");
	}

	public void reproducirSonidoNivelTerminado() {
		fabricaSonidos.reproducir("terminado");

	}

	public void cargarFabricaYGenerador() {
		fabricaEntidades = new FabricaEntidades(fabricaSprites);
		generadorNivel = new GeneradorNiveles(fabricaEntidades, this);
	}

	public void setControladorVistas(ControladorVistas controladorVistas) {
		this.controladorVistas = controladorVistas;
	}

	public void setFabricaSprites(FabricaSprites fabricaSprites) {
		this.fabricaSprites = fabricaSprites;
	}

	public FabricaSprites getFabricaSprites() {
		return fabricaSprites;
	}

	public ControladorVistas getControladorVistas() {
		return controladorVistas;
	}

	public void iniciar() {
		nivelActual = generadorNivel.generarNivel(1);
		controladorVistas.registrarOyenteVentana(new KeyHandler(nivelActual.getMario()));

		registrarObservers();
		iniciarHilos();

	}

	public void cambiarNivel(int nivel) {
		managerMovimientoMario.interrupt();
		managerMovimientoEnemigos.interrupt();
		managerMovimientoBolaDeFuego.interrupt();
		int puntajeNivelAnterior = nivelActual.getMario().getPuntaje();
		controladorVistas.reiniciarPantallaJuego();
		nivelActual = generadorNivel.generarNivel(nivel);
		controladorVistas.registrarOyenteVentana(new KeyHandler(nivelActual.getMario()));
		nivelActual.getMario().sumarPuntaje(puntajeNivelAnterior);
		registrarObservers();
		iniciarHilos();
	}

	protected void iniciarHilos() {
		managerMovimientoMario = new ManagerMovimientoMario(this);
		managerMovimientoMario.start();

		managerMovimientoEnemigos = new ManagerMovimientoEnemigos(this);
		managerMovimientoEnemigos.start();

		managerMovimientoBolaDeFuego = new ManagerMovimientoBolaDeFuego(this);
		managerMovimientoBolaDeFuego.start();

	}

	public void registrarBolaDeFuego(BolaDeFuego bolaDeFuego) {
		Observer observer = controladorVistas.registrarEntidad(bolaDeFuego);
		bolaDeFuego.registrarObserver(observer);
	}

	protected void registrarObservers() {
		registrarObserverJugador(nivelActual.getMario());
		registrarObserverEntidades(nivelActual.getPowerUps());
		registrarObserverEntidades(nivelActual.getPlataformas());
		registrarObserverEntidades(nivelActual.getEnemigos());

	}

	public void eliminarObserverEntidad(Observer observer) {
		controladorVistas.eliminarEntidad(observer);
	}

	protected void registrarObserverJugador(Mario mario) {
		Observer observer = controladorVistas.registrarEntidadJugador(mario);
		mario.registrarObserver(observer);
	}

	public void registrarObserverEntidad(Entidad entidad) {
		Observer observer = controladorVistas.registrarEntidad(entidad);
		entidad.registrarObserver(observer);
	}

	protected void registrarObserverEntidades(List<? extends Entidad> entidades) {
		for (Entidad entidad : entidades) {
			Observer observer = controladorVistas.registrarEntidad(entidad);
			entidad.registrarObserver(observer);
		}
	}

	public Nivel getNivelActual() {
		return nivelActual;
	}

	public void detectarColisionesMarioYManejar() {

		for (Plataforma plataforma : nivelActual.getPlataformas()) {
			int lado = detectorColisiones.colisionaCon(nivelActual.getMario(), plataforma);
			if (lado != DetectorColisiones.NINGUNO) {
				plataforma.acceptMario(nivelActual.getMario(), lado);
			}
		}

		for (Enemigo enemigo : nivelActual.getEnemigos()) {
			int lado = detectorColisiones.colisionaCon(nivelActual.getMario(), enemigo);
			if (lado != DetectorColisiones.NINGUNO) {
				enemigo.acceptMario(nivelActual.getMario(), lado);
			}
		}

		for (PowerUp powerUp : nivelActual.getPowerUps()) {
			int lado = detectorColisiones.colisionaCon(nivelActual.getMario(), powerUp);
			if (lado != DetectorColisiones.NINGUNO) {
				powerUp.acceptMario(nivelActual.getMario(), lado);
			}
		}

	}

	public void detectarColisionesEnemigosYManejar() {

		for (Enemigo enemigo : nivelActual.getEnemigos()) {
			for (Plataforma plataforma : nivelActual.getPlataformas()) {
				int lado = detectorColisiones.colisionaCon(enemigo, plataforma);
				if (lado != DetectorColisiones.NINGUNO) {
					plataforma.acceptEnemigo(enemigo, lado);
				}
			}
		}
	}

	public void detectarColsionesBolasDeFuegoYManejar() {

		for (BolaDeFuego bolaDeFuego : nivelActual.getBolasDeFuego()) {

			for (Plataforma plataforma : nivelActual.getPlataformas()) {
				int lado = detectorColisiones.colisionaCon(bolaDeFuego, plataforma);
				if (lado != DetectorColisiones.NINGUNO) {
					plataforma.acceptBolaDeFuego(bolaDeFuego, lado);
				}
			}

			for (Enemigo enemigo : nivelActual.getEnemigos()) {
				int lado = detectorColisiones.colisionaCon(bolaDeFuego, enemigo);
				if (lado != DetectorColisiones.NINGUNO) {
					enemigo.acceptBolaDeFuego(bolaDeFuego, lado);
				}

			}
		}
	}

	public void terminarJuego() {
		reproducirSonidoMuerteMario();
		managerMovimientoMario.interrupt();
		managerMovimientoEnemigos.interrupt();
		managerMovimientoBolaDeFuego.interrupt();
		controladorVistas.mostrarPantallaPerder();
		crearJugador();
		actualizarRanking();
		ranking.mostrarRanking();
		ranking.guardarRankingEnArchivo();
	}

	public void ganarJuego() {
		managerMovimientoMario.interrupt();
		controladorVistas.mostrarPantallaGanar();
		crearJugador();
		actualizarRanking();
		ranking.mostrarRanking();
		ranking.guardarRankingEnArchivo();
	}

	private void actualizarRanking() {
		jugador.setPuntaje(nivelActual.getMario().getPuntaje());
		ranking.agregarJugador(jugador);
	}

	public void crearJugador() {
		jugador = new Jugador(this.nombre, 0);
	}

	public void recibirNombre(String nombre) {
		this.nombre = nombre;
	}

}
