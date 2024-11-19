package Juego;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import Enemigo.Enemigo;
import Enemigo.Lakitu;
import Mario.BolaDeFuego;
import Mario.Mario;
import Plataforma.Plataforma;
import Powerups.PowerUp;

public class Nivel {

	protected int numero;
	protected Mario mario;
	protected List<Enemigo> listaEnemigos;
	protected List<PowerUp> listaPowerUps;
	protected List<Plataforma> listaPlataformas;
	protected List<BolaDeFuego> listaBolasDeFuego;
	protected List<Lakitu> listaLakitus;

	public Nivel(int numero) {
		this.numero = numero;

		listaEnemigos = new CopyOnWriteArrayList<Enemigo>();
		listaPowerUps = new CopyOnWriteArrayList<PowerUp>();
		listaPlataformas = new CopyOnWriteArrayList<Plataforma>();
		listaBolasDeFuego = new CopyOnWriteArrayList<BolaDeFuego>();
		listaLakitus = new CopyOnWriteArrayList<Lakitu>();
	}

	public int getNumero() {
		return numero;
	}

	public Mario getMario() {
		return mario;
	}

	public List<Enemigo> getEnemigos() {
		return listaEnemigos;
	}

	public List<Lakitu> getLakitus() {
		return listaLakitus;
	}

	public List<PowerUp> getPowerUps() {
		return listaPowerUps;
	}

	public List<Plataforma> getPlataformas() {
		return listaPlataformas;
	}

	public List<BolaDeFuego> getBolasDeFuego() {
		return listaBolasDeFuego;
	}

	public void eliminarEnemigo(Enemigo enemigo) {
		this.listaEnemigos.remove(enemigo);
	}

	public void eliminarPlataforma(Plataforma plataforma) {
		this.listaPlataformas.remove(plataforma);
	}

	public void eliminarPowerUp(PowerUp powerUp) {
		this.listaPowerUps.remove(powerUp);
	}

	public void eliminarBolaDeFuego(BolaDeFuego bolaDeFuego) {
		this.listaBolasDeFuego.remove(bolaDeFuego);
	}

	public void agregarJugador(Mario mario) {
		this.mario = mario;
	}

	public void agregarEnemigo(Enemigo enemigo) {
		this.listaEnemigos.add(enemigo);
	}

	public void agregarLakitu(Lakitu lakitu) {
		this.listaLakitus.add(lakitu);
	}

	public void agregarPowerUp(PowerUp powerUp) {
		this.listaPowerUps.add(powerUp);
	}

	public void agregarPlataforma(Plataforma plataforma) {
		this.listaPlataformas.add(plataforma);
	}

	public void agregarBolaDeFuego(BolaDeFuego bolaDeFuego) {
		this.listaBolasDeFuego.add(bolaDeFuego);
	}
}
