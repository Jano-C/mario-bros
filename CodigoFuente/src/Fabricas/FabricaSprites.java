package Fabricas;

public abstract class FabricaSprites {

	protected String rutaACarpeta;

	public FabricaSprites(String rutaACarpeta) {
		this.rutaACarpeta = rutaACarpeta;
	}

	public Sprite getBloqueDePreguntasVacio() {
		return new Sprite(rutaACarpeta + "bloqueDePreguntasVacio.png");
	}

	public Sprite getLlegada() {
		return new Sprite(rutaACarpeta + "llegada.png");
	}

	public Sprite getTuberiaIzquierda() {
		return new Sprite(rutaACarpeta + "tuberiaIzquierda.png");
	}

	public Sprite getTuberiaDerecha() {
		return new Sprite(rutaACarpeta + "tuberiaDerecha.png");
	}

	public Sprite getTechoTuberiaIzquierda() {
		return new Sprite(rutaACarpeta + "techoTuberiaIzquierda.png");
	}

	public Sprite getTechoTuberiaDerecha() {
		return new Sprite(rutaACarpeta + "techoTuberiaDerecha.png");
	}

	public Sprite getMario() {
		return new Sprite(rutaACarpeta + "mario.gif");
	}

	public Sprite getMarioIdleRight() {
		return new Sprite(rutaACarpeta + "marioIdleRight.png");
	}

	public Sprite getMarioIdleLeft() {
		return new Sprite(rutaACarpeta + "marioIdleLeft.png");
	}

	public Sprite getMarioMovingRight() {
		return new Sprite(rutaACarpeta + "marioMovingRight.gif");
	}

	public Sprite getMarioMovingLeft() {
		return new Sprite(rutaACarpeta + "marioMovingLeft.gif");
	}

	public Sprite getMarioJumpingRight() {
		return new Sprite(rutaACarpeta + "marioJumpingRight.png");
	}

	public Sprite getMarioJumpingLeft() {
		return new Sprite(rutaACarpeta + "marioJumpingLeft.png");
	}

	public Sprite getSuperMario() {
		return new Sprite(rutaACarpeta + "superMarioIdleRight.png");
	}

	public Sprite getSuperMarioIdleRight() {
		return new Sprite(rutaACarpeta + "superMarioIdleRight.png");
	}

	public Sprite getSuperMarioIdleLeft() {
		return new Sprite(rutaACarpeta + "superMarioIdleLeft.png");
	}

	public Sprite getSuperMarioMovingRight() {
		return new Sprite(rutaACarpeta + "superMarioMovingRight.gif");
	}

	public Sprite getSuperMarioMovingLeft() {
		return new Sprite(rutaACarpeta + "superMarioMovingLeft.gif");
	}

	public Sprite getSuperMarioJumpingRight() {
		return new Sprite(rutaACarpeta + "superMarioJumpingRight.png");
	}

	public Sprite getSuperMarioJumpingLeft() {
		return new Sprite(rutaACarpeta + "superMarioJumpingLeft.png");
	}

	public Sprite getSuperMarioFuego() {
		return new Sprite(rutaACarpeta + "superMarioFuegoIdleRight.png");
	}

	public Sprite getSuperMarioFuegoIdleRight() {
		return new Sprite(rutaACarpeta + "superMarioFuegoIdleRight.png");
	}

	public Sprite getSuperMarioFuegoIdleLeft() {
		return new Sprite(rutaACarpeta + "superMarioFuegoIdleLeft.png");
	}

	public Sprite getSuperMarioFuegoMovingRight() {
		return new Sprite(rutaACarpeta + "superMarioFuegoMovingRight.gif");
	}

	public Sprite getSuperMarioFuegoMovingLeft() {
		return new Sprite(rutaACarpeta + "superMarioFuegoMovingLeft.gif");
	}

	public Sprite getSuperMarioFuegoJumpingRight() {
		return new Sprite(rutaACarpeta + "superMarioFuegoJumpingRight.png");
	}

	public Sprite getSuperMarioFuegoJumpingLeft() {
		return new Sprite(rutaACarpeta + "superMarioFuegoJumpingLeft.png");
	}

	public Sprite getMarioEstrella() {
		return new Sprite(rutaACarpeta + "marioEstrellaIdleRight.png");
	}

	public Sprite getMarioEstrellaIdleRight() {
		return new Sprite(rutaACarpeta + "marioEstrellaIdleRight.png");
	}

	public Sprite getMarioEstrellaIdleLeft() {
		return new Sprite(rutaACarpeta + "marioEstrellaIdleLeft.png");
	}

	public Sprite getMarioEstrellaMovingRight() {
		return new Sprite(rutaACarpeta + "marioEstrellaMovingRight.gif");
	}

	public Sprite getMarioEstrellaMovingLeft() {
		return new Sprite(rutaACarpeta + "marioEstrellaMovingLeft.gif");
	}

	public Sprite getMarioEstrellaJumpingRight() {
		return new Sprite(rutaACarpeta + "marioEstrellaJumpingRight.png");
	}

	public Sprite getMarioEstrellaJumpingLeft() {
		return new Sprite(rutaACarpeta + "marioEstrellaJumpingLeft.png");
	}

	public Sprite getMarioParpadeanteIdleRight() {
		return new Sprite(rutaACarpeta + "marioParpadeanteIdleRight.png");
	}

	public Sprite getMarioParpadeanteIdleLeft() {
		return new Sprite(rutaACarpeta + "marioParpadeanteIdleLeft.png");
	}

	public Sprite getMarioParpadeanteMovingRight() {
		return new Sprite(rutaACarpeta + "marioParpadeanteMovingRight.gif");
	}

	public Sprite getMarioParpadeanteMovingLeft() {
		return new Sprite(rutaACarpeta + "marioParpadeanteMovingLeft.gif");
	}

	public Sprite getMarioParpadeanteJumpingRight() {
		return new Sprite(rutaACarpeta + "marioParpadeanteJumpingRight.png");
	}

	public Sprite getMarioParpadeanteJumpingLeft() {
		return new Sprite(rutaACarpeta + "marioParpadeanteJumpingLeft.png");
	}

	public Sprite get() {
		return new Sprite(rutaACarpeta + ".gif");
	}

	public Sprite getGoomba() {
		return new Sprite(rutaACarpeta + "goomba.gif");
	}

	public Sprite getGoombaDerecha() {
		return new Sprite(rutaACarpeta + "goombaDerecha.gif");
	}

	public Sprite getGoombaIzquierda() {
		return new Sprite(rutaACarpeta + "goombaIzquierda.gif");
	}

	public Sprite getLakitu() {
		return new Sprite(rutaACarpeta + "lakitu.gif");
	}

	public Sprite getLakituDerecha() {
		return new Sprite(rutaACarpeta + "lakituRight.png");
	}

	public Sprite getLakituIzquierda() {
		return new Sprite(rutaACarpeta + "lakituLeft.png");
	}

	public Sprite getSpiny() {
		return new Sprite(rutaACarpeta + "spiny.gif");
	}

	public Sprite getSpinyDerecha() {
		return new Sprite(rutaACarpeta + "spinyDerecha.gif");
	}

	public Sprite getSpinyIzquierda() {
		return new Sprite(rutaACarpeta + "spinyIzquierda.gif");
	}

	public Sprite getKoopaTroopa() {
		return new Sprite(rutaACarpeta + "koopaTroopa.gif");
	}

	public Sprite getKoopaTroopaDerecha() {
		return new Sprite(rutaACarpeta + "koopaTroopaDerecha.gif");
	}

	public Sprite getKoopaTroopaIzquierda() {
		return new Sprite(rutaACarpeta + "koopaTroopaIzquierda.gif");
	}

	public Sprite getBuzzyBeetle() {
		return new Sprite(rutaACarpeta + "buzzyBeetle.gif");
	}

	public Sprite getBuzzyBeetleDerecha() {
		return new Sprite(rutaACarpeta + "buzzyBeetleDerecha.gif");
	}

	public Sprite getBuzzyBeetleIzquierda() {
		return new Sprite(rutaACarpeta + "buzzyBeetleIzquierda.gif");
	}

	public Sprite getFlorDeFuego() {
		return new Sprite(rutaACarpeta + "florDeFuego.gif");
	}

	public Sprite getEstrella() {
		return new Sprite(rutaACarpeta + "estrella.gif");
	}

	public Sprite getChampinionVerde() {
		return new Sprite(rutaACarpeta + "champinionVerde.png");
	}

	public Sprite getMoneda() {
		return new Sprite(rutaACarpeta + "moneda.gif");
	}

	public Sprite getPiranhaPlant() {
		return new Sprite(rutaACarpeta + "piranhaPlant.gif");
	}

	public Sprite getBloqueSolido() {
		return new Sprite(rutaACarpeta + "bloqueSolido.png");
	}

	public Sprite getLadrilloSolido() {
		return new Sprite(rutaACarpeta + "bloqueDeLadrillo.png");
	}

	public Sprite getBloqueDePreguntas() {
		return new Sprite(rutaACarpeta + "bloqueDePreguntas.gif");
	}

	public Sprite getTuberia() {
		return new Sprite(rutaACarpeta + "default.png");
	}

	public Sprite getSuperChampinion() {
		return new Sprite(rutaACarpeta + "superChampinion.png");
	}

	public Sprite getVacio() {
		return new Sprite(rutaACarpeta + "vacio.png");
	}

	public Sprite getBolaDeFuego() {
		return new Sprite(rutaACarpeta + "bolaDeFuego.gif");
	}

}