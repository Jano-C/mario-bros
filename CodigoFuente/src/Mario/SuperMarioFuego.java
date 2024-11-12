package Mario;

import Auxiliares.ConstantesAuxiliares;
import Enemigo.Enemigo;
import Enemigo.PiranhaPlant;
import Enemigo.Spiny;
import Fabricas.FabricaSprites;
import Fabricas.Sprite;
import Powerups.PowerUp;

public class SuperMarioFuego implements EstadoMario{

	
	protected Sprite sprite;
	protected Mario mario;
	
    protected FabricaSprites fabricaSprites;
    protected Sprite marioIdleRight;
    protected Sprite marioIdleLeft;
    protected Sprite marioMovingRight;
    protected Sprite marioMovingLeft;
    protected Sprite marioJumpingRight;
    protected Sprite marioJumpingLeft;
    private long ultimoLanzamiento = 0;
    private static final long DELAY_LANZAMIENTO = 500; 

    public SuperMarioFuego(Mario mario) {
        this.mario = mario;
        fabricaSprites = mario.getFabrica();
        this.sprite = fabricaSprites.getMario();
        marioIdleRight = fabricaSprites.getSuperMarioFuegoIdleRight();
        marioIdleLeft  = fabricaSprites.getSuperMarioFuegoIdleLeft();
        marioMovingRight = fabricaSprites.getSuperMarioFuegoMovingRight();
        marioMovingLeft = fabricaSprites.getSuperMarioFuegoMovingLeft();
        marioJumpingRight = fabricaSprites.getSuperMarioFuegoJumpingRight();
        marioJumpingLeft = fabricaSprites.getSuperMarioFuegoJumpingLeft();
        mario.setAlto(ConstantesAuxiliares.MARIOGRANDE_ALTO);
    }

    public boolean puedeLanzarBolaDeFuego() {
        return System.currentTimeMillis() - ultimoLanzamiento >= DELAY_LANZAMIENTO;
    }
	@Override
	public void recibirGolpe(int puntosARestar) {
		mario.setEstado(new MarioNormal(mario));
		
	}

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

	public void crearBolaDeFuego() {
		if (puedeLanzarBolaDeFuego()) {
		BolaDeFuego bolaDeFuego=new BolaDeFuego(fabricaSprites.getBolaDeFuego(), mario.getX(),mario.getY() + (mario.getAlto() / 3),ConstantesAuxiliares.TAMANOBOLADEFUEGO,ConstantesAuxiliares.TAMANOBOLADEFUEGO);
		bolaDeFuego.setOrientacion(mario.getOrientacion());
		mario.getJuego().registrarBolaDeFuego(bolaDeFuego);
		mario.getJuego().getNivelActual().agregarBolaDeFuego(bolaDeFuego);
		mario.getJuego().sonidoBolaDeFuego();
		 ultimoLanzamiento = System.currentTimeMillis();
		}
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
	public EstadoMario getEstadoAnterior() {
		return this;
	}
	
	@Override
	public int getAlto() {
		return ConstantesAuxiliares.MARIOGRANDE_ALTO;
	}



}
