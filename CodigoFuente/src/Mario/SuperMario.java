package Mario;

import Auxiliares.ConstantesAuxiliares;
import Auxiliares.DetectorColisiones;
import Enemigo.BuzzyBeetle;
import Enemigo.Enemigo;
import Enemigo.Goomba;
import Enemigo.KoopaTroopa;
import Enemigo.Lakitu;
import Enemigo.PiranhaPlant;
import Enemigo.Spiny;
import Fabricas.FabricaSprites;
import Fabricas.Sprite;
import Plataforma.LadrilloSolido;
import Powerups.PowerUp;

public class SuperMario implements EstadoMario {

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
		marioIdleLeft = fabricaSprites.getSuperMarioIdleLeft();
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

		ladrilloSolido.destruir();
		mario.getJuego().getNivelActual().eliminarPlataforma(ladrilloSolido);
		mario.getJuego().reproducirSonidoLadrilloRoto();

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

	@Override
	public void colisionaConLakitu(Lakitu lakitu, int lado) {

		if (lado == DetectorColisiones.ABAJO_4) {
			mario.atacar(lakitu);
			mario.getJuego().getNivelActual().eliminarEnemigo(lakitu);
			mario.getJuego().reproducirSonidoBump();
			mario.setAire(false);
			mario.saltar();

		} else {
			lakitu.atacar(mario);
		}

	}

	@Override
	public void colisionaConSpiny(Spiny spiny, int lado) {

		spiny.atacar(mario);

	}

	@Override
	public void colisionaConBuzzyBeetle(BuzzyBeetle buzzyBeetle, int lado) {

		if (lado == DetectorColisiones.ABAJO_4) {

			mario.atacar(buzzyBeetle);
			mario.getJuego().getNivelActual().eliminarEnemigo(buzzyBeetle);
			mario.getJuego().reproducirSonidoBump();
			mario.setAire(false);
			mario.saltar();

		} else {

			buzzyBeetle.atacar(mario);

		}

	}

	@Override
	public void colisionaConPiranhaPlant(PiranhaPlant piranhaPlant, int lado) {

		piranhaPlant.atacar(mario);

	}

	@Override
	public void colisionaConGoomba(Goomba goomba, int lado) {

		if (lado == DetectorColisiones.ABAJO_4) {
			mario.atacar(goomba);
			mario.getJuego().getNivelActual().eliminarEnemigo(goomba);
			mario.getJuego().reproducirSonidoBump();
			mario.setAire(false);
			mario.saltar();

		} else {
			goomba.atacar(mario);
		}

	}

	@Override
	public void colisionaConKoopaTroopa(KoopaTroopa koopaTroopa, int lado) {

		if (lado == DetectorColisiones.ABAJO_4) {

			mario.atacar(koopaTroopa);
			mario.getJuego().reproducirSonidoBump();
			mario.setAire(false);
			mario.saltar();
			if (koopaTroopa.getVidas() == 0) {

				mario.getJuego().getNivelActual().eliminarEnemigo(koopaTroopa);

			}

		} else {
			koopaTroopa.atacar(mario);
		}

	}

}
