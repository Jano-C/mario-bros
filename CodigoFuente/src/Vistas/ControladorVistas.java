package Vistas;

import javax.swing.JFrame;

import Entidades.*;
import Fabricas.FabricaSpritesModoOriginal;
import Fabricas.FabricaSpritesModoSecundario;
import Juego.Juego;

public class ControladorVistas implements ControladorVistasInterfaz {

	protected JFrame ventana;
	protected Juego juego;
	protected PanelPantallaJuego panelPantallaJuego;
	protected PanelPantallaInicio panelPantallaInicio;
	protected PanelPantallaRanking panelPantallaRanking;
	protected PanelPantallaPerder panelPantallaPerder;
	protected PanelPantallaGanar panelPantallaGanar;

	public ControladorVistas(Juego juego) {
		this.juego = juego;
		this.panelPantallaInicio = new PanelPantallaInicio(this);
		this.panelPantallaJuego = new PanelPantallaJuego(this);
		this.panelPantallaRanking = new PanelPantallaRanking(this);
		this.panelPantallaPerder = new PanelPantallaPerder(this);
		this.panelPantallaGanar = new PanelPantallaGanar(this);
		configurarVentana();
		this.mostrarPantallaInicio();
	}

	public void reiniciarPantallaJuego() {
		String rutaAnterior = panelPantallaJuego.getRutaFondo();
		this.panelPantallaJuego = new PanelPantallaJuego(this);
		ventana.setContentPane(panelPantallaJuego);
		panelPantallaJuego.setFondoJuego(rutaAnterior);
		refrescar();
	}

	public Observer registrarEntidad(EntidadLogica entidadLogica) {
		Observer observerEntidad = panelPantallaJuego.incorporarEntidad(entidadLogica);
		observerEntidad.actualizar();
		refrescar();
		return observerEntidad;
	}

	public void eliminarEntidad(Observer observer) {
		panelPantallaJuego.eliminarEntidad(observer);
	}

	public Observer registrarEntidadJugador(EntidadLogicaJugador entidadJugador) {
		Observer observerJugador = panelPantallaJuego.incorporarEntidadJugador(entidadJugador);
		observerJugador.actualizar();
		refrescar();
		return observerJugador;
	}

	public void registrarOyenteVentana(KeyHandler keyHandler) {
		ventana.addKeyListener(keyHandler);
		ventana.setFocusable(true);
		ventana.requestFocusInWindow();
	}

	protected void configurarVentana() {
		ventana = new JFrame("Mario Bros");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setSize(ConstantesVentana.VENTANA_ANCHO, ConstantesVentana.VENTANA_ALTO);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	@Override
	public void mostrarPantallaInicio() {
		ventana.setContentPane(panelPantallaInicio);
		juego.iniciarMusicaMenu();
		juego.detenerMusicaJuego();
		refrescar();

	}

	@Override
	public void mostrarPantallaJuegoModoSecundario() {
		juego.setFabricaSprites(new FabricaSpritesModoSecundario());
		juego.cargarFabricaYGenerador();
		juego.iniciar();
		panelPantallaJuego.setFondoJuego("/Imagenes/fondoMapa2.png");
		ventana.setContentPane(panelPantallaJuego);
		juego.detenerMusicaMenu();
		juego.iniciarMusicaJuego();
		refrescar();
	}

	@Override
	public void mostrarPantallaJuegoModoOriginal() {
		juego.setFabricaSprites(new FabricaSpritesModoOriginal());
		juego.cargarFabricaYGenerador();
		juego.iniciar();
		panelPantallaJuego.setFondoJuego("/Imagenes/fondoMapa.png");
		ventana.setContentPane(panelPantallaJuego);
		juego.detenerMusicaMenu();
		juego.iniciarMusicaJuego();
		refrescar();
	}

	@Override
	public void mostrarPantallaRanking() {
		panelPantallaRanking.getRanking().cargarRankingDesdeArchivo();
		panelPantallaRanking.actualizarLabels();
		ventana.setContentPane(panelPantallaRanking);
		juego.detenerMusicaMenu();
		juego.iniciarMusicaJuego();
		refrescar();

	}

	public void mostrarPantallaGanar() {
		ventana.setContentPane(panelPantallaGanar);
		juego.detenerMusicaJuego();
		refrescar();
	}

	public void mostrarPantallaPerder() {
		ventana.setContentPane(panelPantallaPerder);
		juego.detenerMusicaJuego();
		refrescar();
	}

	public void refrescar() {
		ventana.revalidate();
		ventana.repaint();
	}

	public void terminarJuego() {
		juego = new Juego();
		juego.setControladorVistas(this);
		this.panelPantallaJuego = new PanelPantallaJuego(this);

	}

	public void guardarNombre(String nombre) {
		juego.recibirNombre(nombre);
	}

}
