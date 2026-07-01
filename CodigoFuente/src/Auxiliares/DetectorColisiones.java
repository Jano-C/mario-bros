package Auxiliares;

import Entidades.EntidadLogica;
import Fabricas.Sprite;

public class DetectorColisiones {
	
	protected int mejora = 8;
	public static final int NINGUNO=0;
	public static final int DERECHA_1=1;
	public static final int IZQUIERDA_2=2;
	public static final int ARRIBA_3=3;
	public static final int ABAJO_4=4;

	public int colisionaCon(EntidadLogica entidadLogica, EntidadLogica entidadColisionada) {
		int x1 = entidadLogica.getX();
		int y1 = entidadLogica.getY();
		int w1 = entidadLogica.getAncho();
		int h1 = entidadLogica.getAlto();

		int x2 = entidadColisionada.getX();
		int y2 = entidadColisionada.getY();
		int w2 = entidadColisionada.getAncho();
		int h2 = entidadColisionada.getAlto();

		// Check if bounding boxes actually overlap
		if (x1 + w1 <= x2 || x1 >= x2 + w2 || y1 + h1 <= y2 || y1 >= y2 + h2) {
			return NINGUNO;
		}

		// Calculate overlap depth on each side
		int overlapLeft = (x2 + w2) - x1;   // Collided on Left side of A (A is to the right of B)
		int overlapRight = (x1 + w1) - x2;  // Collided on Right side of A (A is to the left of B)
		int overlapTop = (y2 + h2) - y1;    // Collided on Top side of A (A is below B)
		int overlapBottom = (y1 + h1) - y2; // Collided on Bottom side of A (A is above B)

		// Find the side with the minimum overlap
		int minOverlap = Math.min(Math.min(overlapLeft, overlapRight), Math.min(overlapTop, overlapBottom));

		if (minOverlap == overlapBottom) {
			return ABAJO_4;
		} else if (minOverlap == overlapTop) {
			return ARRIBA_3;
		} else if (minOverlap == overlapRight) {
			return DERECHA_1;
		} else {
			return IZQUIERDA_2;
		}
	}

	public void ocultarImagen(EntidadLogica entidadLogica) {
		entidadLogica.setSprite(new Sprite("/Imagenes/2vacio.png"));
		entidadLogica.notificarObserver();
	}

}
