package Mario;

import Auxiliares.ConstantesAuxiliares;
import Enemigo.Enemigo;
import Enemigo.PiranhaPlant;
import Enemigo.Spiny;
import Fabricas.FabricaSprites;
import Fabricas.Sprite;
import Powerups.PowerUp;
import java.util.*;

public class MarioInvencible implements EstadoMario {

	protected Mario mario;
	protected Sprite sprite;
	protected EstadoMario estadoAnterior;
    protected FabricaSprites fabricaSprites;
    protected Sprite marioIdleRight;
    protected Sprite marioIdleLeft;
    protected Sprite marioMovingRight;
    protected Sprite marioMovingLeft;
    protected Sprite marioJumpingRight;
    protected Sprite marioJumpingLeft;
	
	public MarioInvencible(Mario mario, EstadoMario estadoAnterior) {
        this.mario = mario;
        fabricaSprites = mario.getFabrica();
        this.sprite = fabricaSprites.getMario();
        marioIdleRight = fabricaSprites.getMarioEstrellaIdleRight();
        marioIdleLeft  = fabricaSprites.getMarioEstrellaIdleLeft();
        marioMovingRight = fabricaSprites.getMarioEstrellaMovingRight();
        marioMovingLeft = fabricaSprites.getMarioEstrellaMovingLeft();
        marioJumpingRight = fabricaSprites.getMarioEstrellaJumpingRight();
        marioJumpingLeft = fabricaSprites.getMarioEstrellaJumpingLeft();
        this.estadoAnterior = estadoAnterior;
        mario.notificarMarioEstrella();
		mario.setAlto(ConstantesAuxiliares.MARIOGRANDE_ALTO);
	}
	
	public EstadoMario getEstadoAnterior() {
		return estadoAnterior;
	}
	
	@Override
	public void sumarPuntos(int puntos) {
		mario.sumarPuntaje(puntos);
		
	}

	@Override
	public void setEstado(EstadoMario estadoMario) {
		mario.setEstado(estadoMario);
		
	}

	@Override
	public void serAfectadoPorPowerUp(PowerUp powerUp) {
		powerUp.aplicarEfecto(this);
		
	}
	
	@Override
	public void recibirGolpe(int puntosARestar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}
	
	public Mario getMario() {
		return mario;
	}
	@Override
	public boolean atacar(Enemigo enemigo) {
		enemigo.serAfectadoPorJugador(mario);
		return false;
	}
	
	@Override
	public boolean atacar(Spiny spiny) {
		spiny.serAfectadoPorJugador(mario);
		return true;
	}
	
	@Override
	public boolean atacar(PiranhaPlant piranhaPlant) {
		return true;
	}

	@Override
	public Sprite getSpriteMoviendoseDerecha() {
		return marioMovingRight;
	}

	@Override
	public Sprite getSpriteMoviendoseIzquierda() {
		return marioMovingLeft;
	}

	@Override
	public Sprite getSpriteIdleDerecha() {
		return marioIdleRight;
	}

	@Override
	public Sprite getSpriteIdeIzquierda() {
		return marioIdleLeft;
	}

	@Override
	public Sprite getSpriteSaltandoIzquierda() {
		return marioJumpingLeft;
	}

	@Override
	public Sprite getSpriteSaltandoDerecha() {
		return marioJumpingRight;
	}

	@Override
	public boolean chocharLadrilloSolido() {
		return false;
	}

	@Override
	public void crearBolaDeFuego() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAlto() {
		return ConstantesAuxiliares.MARIOGRANDE_ALTO;
	}
	

}
