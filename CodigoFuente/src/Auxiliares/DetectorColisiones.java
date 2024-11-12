package Auxiliares;

import Entidades.EntidadLogica;
import Fabricas.Sprite;

public class DetectorColisiones {
	
	protected int mejora = 8;
	
	public int colisionaCon(EntidadLogica entidadLogica, EntidadLogica entidadColisionada) {
		
		int ladoColision = 0;
		
		if(colisionaConEntidadDerecha(entidadLogica,entidadColisionada)) {
			System.out.println("Colisiona con entidad a su derecha.");
			ladoColision = 1;
		}
		if(colisionaConEntidadAbajo(entidadLogica,entidadColisionada)) {
			System.out.println("Colisiona con entidad a su abajo.");
			ladoColision = 4;
		}
		if(colisionaConEntidadArriba(entidadLogica,entidadColisionada)) {
			System.out.println("Colisiona con entidad arriba.");
			ladoColision = 3;
		}
		if(colisionaConEntidadIzquierda(entidadLogica,entidadColisionada)) {
			System.out.println("Colisiona con entidad izquierda.");
			ladoColision = 2;
		}
		
		return ladoColision;
	}
	
    public boolean colisionaConEntidadDerecha(EntidadLogica entidadLogica, EntidadLogica entidadColisionada) {
        return entidadLogica.getX() + entidadLogica.getAncho() >= entidadColisionada.getX() &&  
        		entidadLogica.getX() < entidadColisionada.getX() &&
        	entidadLogica.getY() + entidadLogica.getAlto() > entidadColisionada.getY() &&
        	entidadLogica.getY() < entidadColisionada.getY() + entidadColisionada.getAlto();
    }
   
    public boolean colisionaConEntidadIzquierda(EntidadLogica entidadLogica, EntidadLogica entidadColisionada) {
    	return entidadLogica.getX()  < entidadColisionada.getX() + entidadColisionada.getAncho() &&  
    			entidadLogica.getX() > entidadColisionada.getX() &&
    			entidadLogica.getY() + entidadLogica.getAlto() - (mejora / 2) > entidadColisionada.getY() &&
    			entidadLogica.getY() < entidadColisionada.getY() + entidadColisionada.getAlto();
    }
   
    public boolean colisionaConEntidadAbajo(EntidadLogica entidadLogica, EntidadLogica entidadColisionada) {
    	return entidadLogica.getY() + entidadLogica.getAlto() > entidadColisionada.getY() && 
    			entidadLogica.getY() < entidadColisionada.getY() + entidadColisionada.getAlto() && 
    			entidadLogica.getX() + mejora < entidadColisionada.getX() + entidadColisionada.getAncho() && 
    			entidadLogica.getX() + entidadLogica.getAncho() - mejora > entidadColisionada.getX(); 
    }
   
    public boolean colisionaConEntidadArriba(EntidadLogica entidadLogica, EntidadLogica entidadColisionada) {
	   return entidadLogica.getY() - mejora <= entidadColisionada.getY() + entidadColisionada.getAlto() && 
			   entidadLogica.getY() + mejora > entidadColisionada.getY() + entidadColisionada.getAlto() && 
			   entidadLogica.getX()  < entidadColisionada.getX() + entidadColisionada.getAncho() && 
			   entidadLogica.getX() + entidadLogica.getAncho() > entidadColisionada.getX();
   }
    
   public void ocultarImagen(EntidadLogica entidadLogica) {
       entidadLogica.setSprite(new Sprite("/Imagenes/2vacio.png"));
       entidadLogica.notificarObserver();
   }

}
