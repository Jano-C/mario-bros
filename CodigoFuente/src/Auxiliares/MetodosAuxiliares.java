package Auxiliares;

import Entidades.EntidadLogica;
import Fabricas.Sprite;

public class MetodosAuxiliares {
	
	protected int mejora = 8;
	
	public boolean colisionaCon(EntidadLogica entidadLogica, EntidadLogica entidadColisionada) {
		return (colisionaConEntidadDerecha(entidadLogica,entidadColisionada) ||
				colisionaConEntidadIzquierda(entidadLogica,entidadColisionada) ||
				colisionaConEntidadAbajo(entidadLogica,entidadColisionada) ||
				colisionaConEntidadArriba(entidadLogica,entidadColisionada));
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
