package Vistas;

import Entidades.EntidadLogica;

@SuppressWarnings("serial")
public class ObserverEntidad extends ObserverGrafico {

	protected PanelPantallaJuego panelPantallaJuego;

	public ObserverEntidad(EntidadLogica entidadObservada, PanelPantallaJuego panelPantallaJuego) {
		super(entidadObservada);
		this.panelPantallaJuego = panelPantallaJuego;
		this.actualizar();
	}

	@Override
	public void actualizarSoloImagen() {
		this.actualizarImagen();

	}

	public void notificarMuerte() {
		panelPantallaJuego.eliminarEntidad(this);
	}

}
