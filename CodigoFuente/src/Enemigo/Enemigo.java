package Enemigo;

import Entidades.EntidadDinamica;
import Entidades.EntidadLogica;
import Fabricas.Sprite;
import Mario.Mario;
import Plataforma.Llegada;
import Plataforma.Plataforma;
import Plataforma.Vacio;
import Visitor.Visitable;
import Visitor.VisitablePorBolaDeFuego;
import Visitor.VisitorEnemigo;


public abstract class Enemigo extends EntidadDinamica implements Visitable, VisitablePorBolaDeFuego, VisitorEnemigo{
	
	boolean enAire;
	float multiplicadorVelocidad;
	boolean moviendoseDerecha;
	protected boolean movingRight;
	protected int velocidad;
	protected int gravedad;
	protected Sprite izquierda, derecha;
	
	public Enemigo(Sprite sprite,Sprite derecha, Sprite izquierda, int x, int y, int ancho, int alto) {
		super(sprite,x,y,ancho,alto);
		this.izquierda = izquierda;
		this.derecha = derecha;
		movingRight = true;
		velocidad = 2;
        enAire = true;
        gravedad = 1;
	}
	
	public void actualizarPosicion() {
		
		if(enAire == true) {
			posicion.setY(getY() + gravedad);
		}
		
		if(movingRight) {
			moverseDerecha();
		}else {
			moverseIzquierda();
		}
	}
    
    public void aterrizar() {
        enAire = false;
    }
    
    public boolean getAire() {
    	return enAire;
    }

	public void setMovingRight(boolean right) {
		this.movingRight = right;
	}
	
	public boolean getMovingRight() {
		return this.movingRight;
	}
	
	public void moverseDerecha() {
		posicion.setX(posicion.getX() + velocidad);
		this.notificarObserver();
	}
	
	public void moverseIzquierda() {
		posicion.setX(posicion.getX() - velocidad);
		this.notificarObserver();
	}

	public void setAire(boolean b) {
		enAire = b;
	}
	
	private void cambiarSpriteEnColision() {
		if(movingRight){
			sprite = derecha;
		}else {
			sprite = izquierda;
		}
	}
	
	@Override
	public void visit(Plataforma plataforma, int lado) {
		revisarColision(plataforma, lado);
	}
	
	@Override
	public void visit(Vacio vacio, int lado) {
		
	}
	
	private void revisarColision(EntidadLogica entidadLogica, int lado) {
	    switch (lado) {
        case 1:
            movingRight = false;
            cambiarSpriteEnColision();
            break;
        case 2: 
        	 movingRight = true;
        	 cambiarSpriteEnColision();
            break;
        case 3:

            break;
        case 4: 
            enAire = false;
            break;
	    }
	}

	public abstract void serAfectadoPorBolaDeFuego(Mario mario);
	public abstract void serAfectadoPorJugador(Mario mario);
}
