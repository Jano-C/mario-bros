package Mario;

import Enemigo.BuzzyBeetle;
import Enemigo.Enemigo;
import Enemigo.Goomba;
import Enemigo.KoopaTroopa;
import Enemigo.Lakitu;
import Enemigo.PiranhaPlant;
import Enemigo.Spiny;
import Fabricas.Sprite;
import Plataforma.LadrilloSolido;
import Powerups.PowerUp;

public interface EstadoMario {

	public void recibirGolpe(int puntosARestar);

	public Sprite getSprite();

	public void serAfectadoPorPowerUp(PowerUp powerUp);

	public void sumarPuntos(int puntos);

	public void setEstado(EstadoMario estadoMario);

	public Sprite getSpriteMoviendoseDerecha();

	public Sprite getSpriteMoviendoseIzquierda();

	public Sprite getSpriteIdleDerecha();

	public Sprite getSpriteIdleIzquierda();

	public Sprite getSpriteSaltandoIzquierda();

	public Sprite getSpriteSaltandoDerecha();

	public void chocarLadrilloSolido(LadrilloSolido ladrilloSolido);

	public void crearBolaDeFuego();

	public EstadoMario getEstadoAnterior();

	public int getAlto();

	// Testing
	public void colisionaConLakitu(Lakitu lakitu, int lado);

	public void colisionaConSpiny(Spiny spiny, int lado);

	public void colisionaConBuzzyBeetle(BuzzyBeetle buzzyBeetle, int lado);

	public void colisionaConPiranhaPlant(PiranhaPlant piranhaPlant, int lado);

	public void colisionaConGoomba(Goomba goomba, int lado);

	public void colisionaConKoopaTroopa(KoopaTroopa koopaTroopa, int lado);

}