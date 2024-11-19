package Fabricas;

import Mario.Mario;
import Enemigo.*;
import Juego.Juego;
import Powerups.*;
import Plataforma.*;

public class FabricaEntidades {

	protected FabricaSprites fabricaSprites;

	public FabricaEntidades(FabricaSprites fabricaSprites) {
		this.fabricaSprites = fabricaSprites;
	}

	public Mario getMario(int x, int y, int ancho, int alto, Juego juego) {
		Sprite spriteMario = fabricaSprites.getMario();

		return new Mario(spriteMario, x, y, ancho, alto, juego);
	}

	public BuzzyBeetle getBuzzyBeetle(int x, int y, int ancho, int alto) {
		Sprite spriteBuzzyBeetle = fabricaSprites.getBuzzyBeetle();
		Sprite izquierda = fabricaSprites.getBuzzyBeetleIzquierda();
		Sprite derecha = fabricaSprites.getBuzzyBeetleDerecha();
		return new BuzzyBeetle(spriteBuzzyBeetle, derecha, izquierda, x, y, ancho, alto);
	}

	public Spiny getSpiny(int x, int y, int ancho, int alto) {
		Sprite spriteSpiny = fabricaSprites.getSpiny();
		Sprite izquierda = fabricaSprites.getSpinyIzquierda();
		Sprite derecha = fabricaSprites.getSpinyDerecha();
		return new Spiny(spriteSpiny, derecha, izquierda, x, y, ancho, alto);
	}

	public Goomba getGoomba(int x, int y, int ancho, int alto) {
		Sprite spriteGoomba = fabricaSprites.getGoomba();
		Sprite izquierda = fabricaSprites.getGoombaIzquierda();
		Sprite derecha = fabricaSprites.getGoombaDerecha();
		return new Goomba(spriteGoomba, derecha, izquierda, x, y, ancho, alto);
	}

	public KoopaTroopa getKoopaTroopa(int x, int y, int ancho, int alto) {
		Sprite spriteKoopaTroopa = fabricaSprites.getKoopaTroopa();
		Sprite izquierda = fabricaSprites.getKoopaTroopaIzquierda();
		Sprite derecha = fabricaSprites.getKoopaTroopaDerecha();
		return new KoopaTroopa(spriteKoopaTroopa, derecha, izquierda, x, y, ancho, alto);
	}

	public Lakitu getLakitu(int x, int y, int ancho, int alto, Juego juego) {
		Sprite spriteLakitu = fabricaSprites.getLakitu();
		Sprite izquierda = fabricaSprites.getLakituIzquierda();
		Sprite derecha = fabricaSprites.getLakituDerecha();
		return new Lakitu(spriteLakitu, derecha, izquierda, x, y, ancho, alto, juego);
	}

	public PiranhaPlant getPiranhaPlant(int x, int y, int ancho, int alto) {
		Sprite spritePiranhaPlant = fabricaSprites.getPiranhaPlant();
		return new PiranhaPlant(spritePiranhaPlant, spritePiranhaPlant, spritePiranhaPlant, x, y, ancho, alto);
	}

	public ChampinionVerde getChampinionVerde(int x, int y, int ancho, int alto) {
		Sprite spriteChampinionVerde = fabricaSprites.getChampinionVerde();
		return new ChampinionVerde(spriteChampinionVerde, x, y, ancho, alto);
	}

	public Estrella getEstrella(int x, int y, int ancho, int alto) {
		Sprite spriteEstrella = fabricaSprites.getEstrella();
		return new Estrella(spriteEstrella, x, y, ancho, alto);
	}

	public FlorDeFuego getFlorDeFuego(int x, int y, int ancho, int alto) {
		Sprite spriteFlorDeFuego = fabricaSprites.getFlorDeFuego();
		return new FlorDeFuego(spriteFlorDeFuego, x, y, ancho, alto);
	}

	public Moneda getMoneda(int x, int y, int ancho, int alto) {
		Sprite spriteMoneda = fabricaSprites.getMoneda();
		return new Moneda(spriteMoneda, x, y, ancho, alto);
	}

	public SuperChampinion getSuperChampinion(int x, int y, int ancho, int alto) {
		Sprite spriteSuperChampinion = fabricaSprites.getSuperChampinion();
		return new SuperChampinion(spriteSuperChampinion, x, y, ancho, alto);
	}

	public BloqueSolido getBloqueSolido(int x, int y, int ancho, int alto) {
		Sprite spriteBloqueSolido = fabricaSprites.getBloqueSolido();
		return new BloqueSolido(spriteBloqueSolido, x, y, ancho, alto);
	}

	public BloqueDePreguntas getBloqueDePreguntas(int x, int y, int ancho, int alto, FabricaSprites fabricaSprites) {
		Sprite spriteBloqueDePreguntas = fabricaSprites.getBloqueDePreguntas();
		return new BloqueDePreguntas(spriteBloqueDePreguntas, x, y, ancho, alto, fabricaSprites);
	}

	public LadrilloSolido getLadrilloSolido(int x, int y, int ancho, int alto) {
		Sprite spriteLadrilloSolido = fabricaSprites.getLadrilloSolido();
		return new LadrilloSolido(spriteLadrilloSolido, x, y, ancho, alto);
	}

	// Tuberias
	public Tuberia getTuberiaIzquierda(int x, int y, int ancho, int alto) {
		Sprite spriteTuberia = fabricaSprites.getTuberiaIzquierda();
		return new Tuberia(spriteTuberia, x, y, ancho, alto);
	}

	public Tuberia getTuberiaDerecha(int x, int y, int ancho, int alto) {
		Sprite spriteTuberia = fabricaSprites.getTuberiaDerecha();
		return new Tuberia(spriteTuberia, x, y, ancho, alto);
	}

	public Tuberia getTechoTuberiaIzquierda(int x, int y, int ancho, int alto) {
		Sprite spriteTuberia = fabricaSprites.getTechoTuberiaIzquierda();
		return new Tuberia(spriteTuberia, x, y, ancho, alto);
	}

	public Tuberia getTechoTuberiaDerecha(int x, int y, int ancho, int alto) {
		Sprite spriteTuberia = fabricaSprites.getTechoTuberiaDerecha();
		return new Tuberia(spriteTuberia, x, y, ancho, alto);
	}

	public Vacio getVacio(int x, int y, int ancho, int alto) {
		Sprite spriteVacio = fabricaSprites.getVacio();
		return new Vacio(spriteVacio, x, y, ancho, alto);
	}

	public Llegada getLlegada(int x, int y, int ancho, int alto) {
		Sprite spriteLlegada = fabricaSprites.getLlegada();
		return new Llegada(spriteLlegada, x, y, ancho, alto);
	}

}
