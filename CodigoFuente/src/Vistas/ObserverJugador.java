package Vistas;

import Entidades.EntidadLogicaJugador;

@SuppressWarnings("serial")
public class ObserverJugador extends ObserverGrafico {

	protected EntidadLogicaJugador entidadLogicaJugador;
	protected PanelPantallaJuego panelPantallaJuego;
	protected int lastSprite;

	public ObserverJugador(EntidadLogicaJugador entidadLogicaJugador, PanelPantallaJuego panelPantallaJuego) {
		super(entidadLogicaJugador);

		this.entidadLogicaJugador = entidadLogicaJugador;
		this.panelPantallaJuego = panelPantallaJuego;
		lastSprite = entidadLogicaJugador.getSpriteActual();
		actualizarImagen();

	}

	@Override
	public void actualizar() {
		if (lastSprite != entidadLogicaJugador.getSpriteActual()) {
			lastSprite = entidadLogicaJugador.getSpriteActual();
			actualizarImagen();
		}
		actualizarPosicionYTamano();
		panelPantallaJuego.actualizarScrollHaciaJugador(entidadLogicaJugador);
		panelPantallaJuego.actualizarLabelsInformacion(entidadLogicaJugador);
	}

	@Override
	public void actualizarSoloImagen() {
		this.actualizarImagen();

	}

	@Override
	public void notificarMuerte() {
		panelPantallaJuego.eliminarEntidad(this);

	}
}
