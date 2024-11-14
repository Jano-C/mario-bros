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
		
		int ladoColision = NINGUNO;
		
		if(colisionaConEntidadDerecha(entidadLogica,entidadColisionada)) {
			
			ladoColision = DERECHA_1;
		}
		if(colisionaConEntidadAbajo(entidadLogica,entidadColisionada)) {
			
			ladoColision = ABAJO_4;
		}
		if(colisionaConEntidadArriba(entidadLogica,entidadColisionada)) {
			
			ladoColision = ARRIBA_3;
		}
		if(colisionaConEntidadIzquierda(entidadLogica,entidadColisionada)) {
			
			ladoColision = IZQUIERDA_2;
		}
		
		return ladoColision;
	}
   
	private boolean colisionaConEntidadIzquierda(EntidadLogica entidadLogica, EntidadLogica entidadColisionada) {
    	return entidadLogica.getX()  < entidadColisionada.getX() + entidadColisionada.getAncho() &&  
    			entidadLogica.getX() > entidadColisionada.getX() &&
    			entidadLogica.getY() + entidadLogica.getAlto() - (mejora / 2) > entidadColisionada.getY() &&
    			entidadLogica.getY() < entidadColisionada.getY() + entidadColisionada.getAlto();
    }
   
    private boolean colisionaConEntidadAbajo(EntidadLogica entidadLogica, EntidadLogica entidadColisionada) {
    	return entidadLogica.getY() + entidadLogica.getAlto() > entidadColisionada.getY() && 
    			entidadLogica.getY() < entidadColisionada.getY() + entidadColisionada.getAlto() && 
    			entidadLogica.getX() + mejora < entidadColisionada.getX() + entidadColisionada.getAncho() && 
    			entidadLogica.getX() + entidadLogica.getAncho() - mejora > entidadColisionada.getX(); 
    }
   
    private boolean colisionaConEntidadArriba(EntidadLogica entidadLogica, EntidadLogica entidadColisionada) {
	   return entidadLogica.getY() - mejora <= entidadColisionada.getY() + entidadColisionada.getAlto() && 
			   entidadLogica.getY() + mejora > entidadColisionada.getY() + entidadColisionada.getAlto() && 
			   entidadLogica.getX()  < entidadColisionada.getX() + entidadColisionada.getAncho() && 
			   entidadLogica.getX() + entidadLogica.getAncho() > entidadColisionada.getX();
   }
    
    private boolean colisionaConEntidadDerecha(EntidadLogica entidadLogica, EntidadLogica entidadColisionada) {
    	return entidadLogica.getX() + entidadLogica.getAncho() > entidadColisionada.getX() && 
    			entidadLogica.getX() + mejora < entidadColisionada.getX() && 
    			entidadLogica.getY() + entidadLogica.getAlto() - mejora > entidadColisionada.getY() && 
    			entidadLogica.getY() + mejora < entidadColisionada.getY() + entidadColisionada.getAlto();
    }
    
//Esto hay que sacarlo de aca
   public void ocultarImagen(EntidadLogica entidadLogica) {
       entidadLogica.setSprite(new Sprite("/Imagenes/2vacio.png"));
       entidadLogica.notificarObserver();
   }

}
