package Mario;

import java.util.Timer;
import java.util.TimerTask;

import Auxiliares.ConstantesAuxiliares;
import Enemigo.BuzzyBeetle;
import Enemigo.Goomba;
import Enemigo.KoopaTroopa;
import Enemigo.Lakitu;
import Enemigo.PiranhaPlant;
import Enemigo.Spiny;
import Fabricas.FabricaSprites;
import Fabricas.Sprite;
import Plataforma.LadrilloSolido;
import Powerups.PowerUp;

public class MarioParpadeante implements EstadoMario {

	protected Mario mario;
	protected Sprite sprite;
	protected EstadoMario estadoAnterior;
	protected Timer timer;
	protected static final int DURACION_EFECTO = 2000;
	protected FabricaSprites fabricaSprites;
	protected Sprite marioIdleRight;
	protected Sprite marioIdleLeft;
	protected Sprite marioMovingRight;
	protected Sprite marioMovingLeft;
	protected Sprite marioJumpingRight;
	protected Sprite marioJumpingLeft;

	public MarioParpadeante(Mario mario, EstadoMario estadoAnterior) {
		this.mario = mario;
		fabricaSprites = mario.getFabrica();
		this.sprite = fabricaSprites.getMario();
		marioIdleRight = fabricaSprites.getMarioParpadeanteIdleRight();
		marioIdleLeft = fabricaSprites.getMarioParpadeanteIdleLeft();
		marioMovingRight = fabricaSprites.getMarioParpadeanteMovingRight();
		marioMovingLeft = fabricaSprites.getMarioParpadeanteMovingLeft();
		marioJumpingRight = fabricaSprites.getMarioParpadeanteJumpingRight();
		marioJumpingLeft = fabricaSprites.getMarioParpadeanteJumpingLeft();
		this.estadoAnterior = estadoAnterior;

		mario.setAlto(ConstantesAuxiliares.MARIONORMAL_ALTO);
		iniciarTemporizador();
	}

	@Override
	public void recibirGolpe(int puntosARestar) {

	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public void serAfectadoPorPowerUp(PowerUp powerUp) {
		powerUp.aplicarEfecto(this);
	}

	@Override
	public void sumarPuntos(int puntos) {

	}

	@Override
	public void setEstado(EstadoMario estadoMario) {
		mario.setEstado(estadoMario);
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
	public Sprite getSpriteIdleIzquierda() {
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
	public void chocarLadrilloSolido(LadrilloSolido ladrilloSolido) {

	}

	@Override
	public void crearBolaDeFuego() {

	}

	@Override
	public EstadoMario getEstadoAnterior() {
		return estadoAnterior;
	}

	@Override
	public int getAlto() {
		return ConstantesAuxiliares.MARIOGRANDE_ALTO;
	}

	@Override
	public void colisionaConLakitu(Lakitu lakitu, int lado) {

	}

	@Override
	public void colisionaConSpiny(Spiny spiny, int lado) {

	}

	@Override
	public void colisionaConBuzzyBeetle(BuzzyBeetle buzzyBeetle, int lado) {

	}

	@Override
	public void colisionaConPiranhaPlant(PiranhaPlant piranhaPlant, int lado) {

	}

	@Override
	public void colisionaConGoomba(Goomba goomba, int lado) {

	}

	@Override
	public void colisionaConKoopaTroopa(KoopaTroopa koopaTroopa, int lado) {

	}

	private void iniciarTemporizador() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				desactivar();
			}
		}, DURACION_EFECTO);
	}

	private void desactivar() {
		mario.setEstado(getEstadoAnterior());
		timer.cancel();

	}

}
