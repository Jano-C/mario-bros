package Mario;

import Enemigo.Enemigo;
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
    public void atacar(Enemigo enemigo);
    public void chocarSpiny(Spiny spiny);
    public void chocarPiranhaPlant(PiranhaPlant piranhaPlant);
    public Sprite getSpriteMoviendoseDerecha();
    public Sprite getSpriteMoviendoseIzquierda();
    public Sprite getSpriteIdleDerecha();
    public Sprite getSpriteIdeIzquierda();
    public Sprite getSpriteSaltandoIzquierda();
    public Sprite getSpriteSaltandoDerecha();
    public void chocharLadrilloSolido(LadrilloSolido ladrilloSolido);
    public void crearBolaDeFuego();
    public EstadoMario getEstadoAnterior();
    public int getAlto();
    	
   

}