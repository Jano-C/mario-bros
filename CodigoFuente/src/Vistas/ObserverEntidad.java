package Vistas;

import Entidades.EntidadLogica;

@SuppressWarnings("serial")
public class ObserverEntidad extends ObserverGrafico {

	public ObserverEntidad(EntidadLogica entidadObservada) {
		super(entidadObservada);
		this.actualizar();
	}

	@Override
	public void actualizarSoloImagen() {
		this.actualizarImagen();
		
	}

}
