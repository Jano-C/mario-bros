package Enemigo;

import Fabricas.FabricaSprites;
import Fabricas.Sprite;
import Mario.Mario;
import Visitor.VisitorBolaDeFuego;
import Visitor.VisitorMario;

public class Lakitu extends Enemigo{
	
	protected int cantidadSpinys;
	protected Sprite izquierda, derecha;
	protected FabricaSprites fabricaSprites;

	public Lakitu(Sprite sprite,Sprite derecha, Sprite izquierda,  int x, int y, int ancho, int alto, FabricaSprites fabricaSprites) {
		super(sprite,derecha,izquierda,x,y,ancho,alto);
		cantidadSpinys = 5;
		gravedad = 0;
		this.fabricaSprites = fabricaSprites;
	}
	
	public void serAfectadoPorJugador(Mario mario) {
		mario.sumarPuntaje(60);
		mario.setAire(false);
		mario.saltar();
	}
	
	public void atacar(Mario mario) {
		mario.recibirGolpe(0);
		mario.setAire(false);
		mario.saltar();
	}
	
	
	public Spiny arrojarSpiny() {
		return new Spiny(fabricaSprites.getSpiny(),fabricaSprites.getSpinyDerecha(),fabricaSprites.getSpinyIzquierda(), posicion.getX(), posicion.getY(), 32,32);
	}
	
	public boolean tieneSpinys() {
		if (cantidadSpinys > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void restarSpiny() {
		cantidadSpinys--;
	}

	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {

		visitorMario.visit(this, lado);

	}

	@Override
	public void acceptBolaDeFuego(VisitorBolaDeFuego visitorBolaDeFuego) {
		visitorBolaDeFuego.visit(this);
		
	}
	
	@Override
	public void serAfectadoPorBolaDeFuego(Mario mario) {
		mario.sumarPuntaje(60);
		
	}
}
