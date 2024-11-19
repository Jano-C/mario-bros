package Entidades;

import Enemigo.Enemigo;
import Enemigo.PiranhaPlant;
import Enemigo.Spiny;

public interface EntidadLogicaJugador extends EntidadLogica {

	public int getVelocidad();

	public int getVidas();

	public boolean getAire();

	public int getAncho();

	public int getAlto();

	public void moverseDerecha();

	public void moverseIzquierda();

	public boolean moviendoseDerecha();

	public boolean moviendoseIzquierda();

	public void setMoviendoseIzquierda(boolean b);

	public void setMoviendoseDerecha(boolean b);

	public void setAire(boolean b);

	public void setSaltar(boolean b);

	public int getPuntaje();

	public void atacar(Enemigo enemigo);

	public void crearBolaDeFuego();

	public int getOrientacion();

	public void setOrientacion(int orientacion);

}