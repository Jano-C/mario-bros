package Enemigo;

import Entidades.EntidadDinamica;
import Fabricas.Sprite;
import Mario.Mario;
import Visitor.Visitable;
import Visitor.VisitablePorBolaDeFuego;


public abstract class Enemigo extends EntidadDinamica implements Visitable, VisitablePorBolaDeFuego{
	
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
	
	public void cambiarSpriteEnColision() {
		if(movingRight){
			sprite = derecha;
		}else {
			sprite = izquierda;
		}
	}

	public abstract void serAfectadoPorBolaDeFuego(Mario mario);
	public abstract void serAfectadoPorJugador(Mario mario);
}
