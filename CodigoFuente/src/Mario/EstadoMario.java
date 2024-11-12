package Mario;

import Enemigo.Enemigo;
import Enemigo.PiranhaPlant;
import Enemigo.Spiny;
import Fabricas.Sprite;
import Powerups.PowerUp;

public interface EstadoMario {

    public void recibirGolpe(int puntosARestar);
    public Sprite getSprite();
    public void serAfectadoPorPowerUp(PowerUp powerUp);
    public void sumarPuntos(int puntos);
    public void setEstado(EstadoMario estadoMario);
    public boolean atacar(Enemigo enemigo);
    public boolean atacar(Spiny spiny);
    public boolean atacar(PiranhaPlant piranhaPlant);
    public Sprite getSpriteMoviendoseDerecha();
    public Sprite getSpriteMoviendoseIzquierda();
    public Sprite getSpriteIdleDerecha();
    public Sprite getSpriteIdeIzquierda();
    public Sprite getSpriteSaltandoIzquierda();
    public Sprite getSpriteSaltandoDerecha();
    public boolean chocharLadrilloSolido();
    public void crearBolaDeFuego();
    public EstadoMario getEstadoAnterior();
    public int getAlto();
    	
   

}