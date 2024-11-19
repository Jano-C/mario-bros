package Mario;

import Auxiliares.ConstantesAuxiliares;
import Enemigo.Enemigo;
import Enemigo.PiranhaPlant;
import Enemigo.Spiny;
import Fabricas.FabricaSprites;
import Fabricas.Sprite;
import Plataforma.LadrilloSolido;
import Powerups.PowerUp;

public class SuperMarioFuego extends SuperMario {

	protected long ultimoLanzamiento = 0;
	protected static final long DELAY_LANZAMIENTO = 500;

	public SuperMarioFuego(Mario mario) {

		super(mario);

		marioIdleRight = fabricaSprites.getSuperMarioFuegoIdleRight();
		marioIdleLeft = fabricaSprites.getSuperMarioFuegoIdleLeft();
		marioMovingRight = fabricaSprites.getSuperMarioFuegoMovingRight();
		marioMovingLeft = fabricaSprites.getSuperMarioFuegoMovingLeft();
		marioJumpingRight = fabricaSprites.getSuperMarioFuegoJumpingRight();
		marioJumpingLeft = fabricaSprites.getSuperMarioFuegoJumpingLeft();
	}

	private boolean puedeLanzarBolaDeFuego() {
		return System.currentTimeMillis() - ultimoLanzamiento >= DELAY_LANZAMIENTO;
	}

	public void crearBolaDeFuego() {
		if (puedeLanzarBolaDeFuego()) {

			BolaDeFuego bolaDeFuego = new BolaDeFuego(fabricaSprites.getBolaDeFuego(), mario.getX(),
					mario.getY() + (mario.getAlto() / 3), ConstantesAuxiliares.TAMANOBOLADEFUEGO,
					ConstantesAuxiliares.TAMANOBOLADEFUEGO, mario.getJuego());
			bolaDeFuego.setOrientacion(mario.getOrientacion());
			mario.getJuego().registrarBolaDeFuego(bolaDeFuego);
			mario.getJuego().getNivelActual().agregarBolaDeFuego(bolaDeFuego);
			mario.getJuego().sonidoBolaDeFuego();
			ultimoLanzamiento = System.currentTimeMillis();

		}
	}
}
