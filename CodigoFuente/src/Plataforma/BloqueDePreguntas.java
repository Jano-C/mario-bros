package Plataforma;

import java.util.Random;

import Auxiliares.ConstantesAuxiliares;
import Fabricas.FabricaSprites;
import Fabricas.Sprite;
import Juego.Juego;
import Powerups.ChampinionVerde;
import Powerups.Estrella;
import Powerups.FlorDeFuego;
import Powerups.Moneda;
import Powerups.PowerUp;
import Powerups.SuperChampinion;
import Visitor.VisitorBolaDeFuego;
import Visitor.VisitorEnemigo;
import Visitor.VisitorMario;

public class BloqueDePreguntas extends Plataforma {

	protected boolean tienePowerUp;
	protected FabricaSprites fabricaSprites;

	public BloqueDePreguntas(Sprite sprite, int x, int y, int ancho, int alto, FabricaSprites fabricaSprites) {

		super(sprite, x, y, ancho, alto);
		tienePowerUp = true;
		this.fabricaSprites = fabricaSprites;
	}

	public void mostrarPowerUp(Juego juego) {

		PowerUp powerUp;
		Random random = new Random();
		int powerUpTipo = random.nextInt(5) + 1;

		if (tienePowerUp) {
			switch (powerUpTipo) {
			case 1:
				powerUp = new Estrella(fabricaSprites.getEstrella(), posicion.getX(),
						posicion.getY() - ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
						ConstantesAuxiliares.TAMANOBLOQUE_ALTO, ConstantesAuxiliares.TAMANOBLOQUE_ALTO);
				break;
			case 2:
				powerUp = new SuperChampinion(fabricaSprites.getSuperChampinion(), posicion.getX(),
						posicion.getY() - ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
						ConstantesAuxiliares.TAMANOBLOQUE_ALTO, ConstantesAuxiliares.TAMANOBLOQUE_ALTO);
				break;
			case 3:
				powerUp = new FlorDeFuego(fabricaSprites.getFlorDeFuego(), posicion.getX(),
						posicion.getY() - ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
						ConstantesAuxiliares.TAMANOBLOQUE_ALTO, ConstantesAuxiliares.TAMANOBLOQUE_ALTO);
				break;

			case 4:
				powerUp = new ChampinionVerde(fabricaSprites.getChampinionVerde(), posicion.getX(),
						posicion.getY() - ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
						ConstantesAuxiliares.TAMANOBLOQUE_ALTO, ConstantesAuxiliares.TAMANOBLOQUE_ALTO);
				break;
			case 5:
				powerUp = new Moneda(fabricaSprites.getMoneda(), posicion.getX(),
						posicion.getY() - ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
						ConstantesAuxiliares.TAMANOBLOQUE_ALTO, ConstantesAuxiliares.TAMANOBLOQUE_ALTO);
				break;
			default:
				powerUp = null;
				break;
			}

			this.setSprite(fabricaSprites.getBloqueDePreguntasVacio());
			this.notificarObserver();

			juego.getNivelActual().agregarPowerUp(powerUp);
			juego.registrarObserverEntidad(powerUp);
			tienePowerUp = false;
		}

	}

	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {
		visitorMario.visit(this, lado);

	}

	@Override
	public void acceptBolaDeFuego(VisitorBolaDeFuego visitorBolaDeFuego, int lado) {
		visitorBolaDeFuego.visit(this, lado);

	}

	@Override
	public void acceptEnemigo(VisitorEnemigo visitorEnemigo, int lado) {
		visitorEnemigo.visit(this, lado);

	};

}
