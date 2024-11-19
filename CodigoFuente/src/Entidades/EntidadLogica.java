package Entidades;

import Fabricas.Sprite;
import Vistas.Observer;

public interface EntidadLogica {

	public Sprite getSprite();

	public int getX();

	public int getY();

	public int getAncho();

	public int getAlto();

	public void setSprite(Sprite sprite);

	public void notificarObserver();

	public int getSpriteActual();

	public Observer getObserver();
}
