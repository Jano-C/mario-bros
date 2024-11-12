package Mario;

import Entidades.EntidadDinamica;
import Fabricas.Sprite;

public class BolaDeFuego extends EntidadDinamica {

	public BolaDeFuego(Sprite sprite, int x, int y, int ancho, int alto) {
		super(sprite, x, y, ancho, alto);
		this.velocidad=6;
		
	}
	 
	public void actualizarPosicion() {
		posicion.setX(posicion.getX()+velocidad);
		this.notificarObserver();		
	}

	public void setOrientacion(int orientacion) {
		velocidad = velocidad * orientacion;
	}

}
