package Juego;

import java.util.List;
import Entidades.Entidad;
import Fabricas.FabricaEntidades;
import Fabricas.FabricaSonidos;
import Fabricas.FabricaSprites;
import GeneradorNiveles.GeneradorNiveles;
import Mario.BolaDeFuego;
import Mario.Mario;
import Ranking.Ranking;
import Vistas.ControladorVistas;
import Vistas.KeyHandler;
import Vistas.Observer;

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
	public Juego() {

		ranking= Ranking.getInstancia();
		ranking.cargarRankingDesdeArchivo();
		fabricaSonidos = new FabricaSonidos();
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
	public void agarrarMoneda() {
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
		Observer observer=controladorVistas.registrarEntidad(bolaDeFuego);
		bolaDeFuego.registrarObserver(observer);
	}
	
	
	protected void registrarObservers() {
		registrarObserverJugador(nivelActual.getMario());
		registrarObserverEntidades(nivelActual.getPowerUps());
		registrarObserverEntidades(nivelActual.getPlataformas());
		registrarObserverEntidades(nivelActual.getEnemigos());

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
		for(Entidad entidad : entidades) {
			Observer observer = controladorVistas.registrarEntidad(entidad);
			entidad.registrarObserver(observer);
		}
	}

	public Nivel getNivelActual() {
		return nivelActual;
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
		jugador=new Jugador(this.nombre, 0);
	}
	public void recibirNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public void marioEstaEnEstrella() {
		managerMovimientoMario.marioEstaEnEstrella();
	}
	
}
