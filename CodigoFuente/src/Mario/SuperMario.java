package Mario;

import Auxiliares.ConstantesAuxiliares;
import Enemigo.Enemigo;
import Enemigo.PiranhaPlant;
import Enemigo.Spiny;
import Fabricas.FabricaSprites;
import Fabricas.Sprite;
import Powerups.PowerUp;

public class SuperMario implements EstadoMario{
	
	protected Sprite sprite;
	protected Mario mario;
	protected FabricaSprites fabricaSprites;
	protected Sprite marioIdleRight;
    protected Sprite marioIdleLeft;
    protected Sprite marioMovingRight;
    protected Sprite marioMovingLeft;
    protected Sprite marioJumpingRight;
    protected Sprite marioJumpingLeft;
	

	public SuperMario(Mario mario) {
        this.mario = mario;
        fabricaSprites = mario.getFabrica();
        this.sprite = fabricaSprites.getMario();
        marioIdleRight = fabricaSprites.getSuperMarioIdleRight();
        marioIdleLeft  = fabricaSprites.getSuperMarioIdleLeft();
        marioMovingRight = fabricaSprites.getSuperMarioMovingRight();
        marioMovingLeft = fabricaSprites.getSuperMarioMovingLeft();
        marioJumpingRight = fabricaSprites.getSuperMarioJumpingRight();
        marioJumpingLeft = fabricaSprites.getSuperMarioJumpingLeft();
        mario.setAlto(ConstantesAuxiliares.MARIOGRANDE_ALTO);
    }
	
	@Override
	public void recibirGolpe(int puntosARestar) {
		mario.setEstado(new MarioNormal(mario));
	}

	@Override
	public Sprite getSprite() {
		return sprite;
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
		spiny.atacar(mario);
		return false;
	}

	@Override
	public boolean atacar(PiranhaPlant piranhaPlant) {
		piranhaPlant.atacar(mario);
		return false;
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
		return true;
	}

	@Override
	public void crearBolaDeFuego() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public EstadoMario getEstadoAnterior() {
		return this;
	}
	
	@Override
	public int getAlto() {
		return ConstantesAuxiliares.MARIOGRANDE_ALTO;
	}

}
