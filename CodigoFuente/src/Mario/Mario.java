package Mario;

import Auxiliares.ConstantesAuxiliares;
import Auxiliares.DetectorColisiones;
import Enemigo.BuzzyBeetle;
import Enemigo.Enemigo;
import Enemigo.Goomba;
import Enemigo.KoopaTroopa;
import Enemigo.Lakitu;
import Enemigo.PiranhaPlant;
import Enemigo.Spiny;
import Entidades.EntidadDinamica;
import Entidades.EntidadLogica;
import Entidades.EntidadLogicaJugador;
import Fabricas.FabricaSprites;
import Fabricas.Sprite;
import Juego.Juego;
import Plataforma.BloqueDePreguntas;
import Plataforma.BloqueSolido;
import Plataforma.LadrilloSolido;
import Plataforma.Llegada;
import Plataforma.Plataforma;
import Plataforma.Tuberia;
import Plataforma.Vacio;
import Powerups.ChampinionVerde;
import Powerups.Estrella;
import Powerups.FlorDeFuego;
import Powerups.Moneda;
import Powerups.PowerUp;
import Powerups.SuperChampinion;
import Visitor.VisitorMario;

public class Mario extends EntidadDinamica implements EntidadLogicaJugador, VisitorMario {

	protected Juego juego;
	protected EstadoMario estadoActual;
	protected Boolean izquierda;
	protected Boolean derecha;
	protected Boolean saltar;
	protected float gravedad = 0.6f;
	protected float velocidadSalto = 0f;
	protected float fuerzaSalto = -12f;
	protected boolean enAire;
	protected int puntaje;
	protected int vidas;
	protected int ultimaPos;
	protected FabricaSprites fabricaSprites;
	protected int orientacion;
	protected static final int MARIO_IDLE_RIGHT = 0;
	protected static final int MARIO_IDLE_LEFT = 1;
	protected static final int MARIO_MOVING_RIGHT = 2;
	protected static final int MARIO_MOVING_LEFT = 3;
	protected static final int MARIO_JUMPING_RIGHT = 4;
	protected static final int MARIO_JUMPING_LEFT = 5;

	public Mario(Sprite sprite, int x, int y, int ancho, int alto, Juego juego) {
		super(sprite, x, y, ancho, alto);
		fabricaSprites = juego.getFabricaSprites();
		vidas = 3;
		this.estadoActual = new MarioNormal(this);
		this.velocidad = 6;
		izquierda = false;
		puntaje = 0;
		derecha = false;
		saltar = false;
		enAire = true;
		ultimaPos = x;
		spriteActual = MARIO_IDLE_RIGHT;
		orientacion = 1;

		this.juego = juego;
	}

	public void moverseDerecha() {
		posicion.setX(posicion.getX() + velocidad);
		this.notificarObserver();
	}

	public void moverseIzquierda() {
		posicion.setX(posicion.getX() - velocidad);
		this.notificarObserver();
	}

	public void moverseArriba() {
		posicion.setY(posicion.getY() - velocidad);
		this.notificarObserver();
	}

	public void recibirGolpe(int puntosARestar) {
		estadoActual.recibirGolpe(puntosARestar);
		this.notificarObserver();
	}

	public void notificarObserver() {
		this.observer.actualizar();
	}

	public void setMoviendoseDerecha(boolean derecha) {
		this.derecha = derecha;
	}

	public void setMoviendoseIzquierda(boolean izquierda) {
		this.izquierda = izquierda;
	}

	public void setSaltar(boolean saltar) {
		this.saltar = saltar;
	}

	@Override
	public boolean getAire() {
		return enAire;
	}

	public void setAire(boolean enAire) {
		this.enAire = enAire;
	}

	public void actualizarTamano() {
		alto = estadoActual.getAlto();
	}

	@Override
	public boolean moviendoseDerecha() {
		return derecha;
	}

	public boolean moviendoseIzquierda() {
		return izquierda;
	}

	public void sumarVidas() {
		vidas++;
	}

	public void restarVidas() {
		vidas--;
	}

	public void moverse() {

		chequearSiTieneQueCaer();
		actualizarPosicionGravedad();

		if (derecha) {
			this.moverseDerecha();
		}

		if (izquierda) {
			this.moverseIzquierda();

		}

		if (saltar) {
			this.saltar();
		}

		actualizarSprite();
	}

	public void setVelocidadSalto(int velocidadSalto) {
		this.velocidadSalto = velocidadSalto;
	}

	protected void actualizarPosicionGravedad() {

		if (enAire) {
			velocidadSalto += gravedad;
			posicion.setY((int) (posicion.getY() + velocidadSalto));
			if (observer != null) {
				this.notificarObserver();
			}
		}
	}

	public void saltar() {
		if (!enAire) {
			juego.reproducirSonidoSalto();
			if (orientacion > 0) {
				spriteActual = MARIO_JUMPING_RIGHT;
				sprite = estadoActual.getSpriteSaltandoDerecha();
			} else {
				spriteActual = MARIO_JUMPING_LEFT;
				sprite = estadoActual.getSpriteSaltandoIzquierda();
			}
			velocidadSalto = fuerzaSalto;
			enAire = true;
		}
	}

	public void setEstado(EstadoMario estadoNuevo) {
		this.estadoActual = estadoNuevo;
		sprite = estadoActual.getSprite();
	}

	public EstadoMario getEstado() {
		return estadoActual;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void serAfectadoPorPowerUp(PowerUp powerUp) {
		estadoActual.serAfectadoPorPowerUp(powerUp);
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void sumarPuntaje(int p) {
		puntaje += p;
	}

	public Juego getJuego() {
		return juego;
	}

	@Override
	public void atacar(Enemigo enemigo) {
		enemigo.serAfectadoPorJugador(this);
	}

	@Override
	public void crearBolaDeFuego() {
		estadoActual.crearBolaDeFuego();
	}

	private void chequearSiTieneQueCaer() {
		if (posicion.getX() >= ultimaPos + ConstantesAuxiliares.TAMANOBLOQUE_ANCHO / 2) {
			enAire = true;
			ultimaPos = posicion.getX();
		}

		if (posicion.getX() <= ultimaPos - ConstantesAuxiliares.TAMANOBLOQUE_ANCHO / 2) {
			enAire = true;
			ultimaPos = posicion.getX();
		}
	}

	public void actualizarSprite() {
		int nuevoSprite;

		if (!enAire) {
			if (derecha) {
				nuevoSprite = MARIO_MOVING_RIGHT;
				sprite = estadoActual.getSpriteMoviendoseDerecha();
			} else if (izquierda) {
				nuevoSprite = MARIO_MOVING_LEFT;
				sprite = estadoActual.getSpriteMoviendoseIzquierda();
			} else {
				if (orientacion > 0) {
					nuevoSprite = MARIO_IDLE_RIGHT;
					sprite = estadoActual.getSpriteIdleDerecha();
				} else {
					nuevoSprite = MARIO_IDLE_LEFT;
					sprite = estadoActual.getSpriteIdleIzquierda();
				}
			}
		} else {

			nuevoSprite = spriteActual;
		}

		if (!enAire && nuevoSprite != spriteActual) {
			spriteActual = nuevoSprite;
			notificarObserver();

		}
	}

	public FabricaSprites getFabrica() {
		return fabricaSprites;
	}

	public int getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(int orientacion) {
		this.orientacion = orientacion;
	}

	public void caerAlVacio() {
		juego.terminarJuego();
	}

	@Override
	public int getVidas() {
		return vidas;
	}

	@Override
	public void visit(BloqueSolido bloqueSolido, int lado) {
		corregirPosicion(bloqueSolido, lado);
	}

	@Override
	public void visit(BloqueDePreguntas bloqueDePreguntas, int lado) {

		corregirPosicion(bloqueDePreguntas, lado);
		if (lado == DetectorColisiones.ARRIBA_3) { // Colisiona por abajo
			bloqueDePreguntas.mostrarPowerUp(juego);

		}
	}

	@Override
	public void visit(LadrilloSolido ladrilloSolido, int lado) {

		corregirPosicion(ladrilloSolido, lado);
		if (lado == DetectorColisiones.ARRIBA_3) { // Colisiona por abajo
			estadoActual.chocarLadrilloSolido(ladrilloSolido);
		}

	}

	@Override
	public void visit(Tuberia tuberia, int lado) {

		corregirPosicion(tuberia, lado);
	}

	@Override
	public void visit(Vacio vacio, int lado) {

		if (lado == DetectorColisiones.ARRIBA_3) {
			caerAlVacio();
		}

	}

	@Override
	public void visit(BuzzyBeetle buzzyBeetle, int lado) {

		estadoActual.colisionaConBuzzyBeetle(buzzyBeetle, lado);

	}

	@Override
	public void visit(Goomba gommba, int lado) {

		estadoActual.colisionaConGoomba(gommba, lado);

	}

	@Override
	public void visit(KoopaTroopa koopaTroopa, int lado) {

		estadoActual.colisionaConKoopaTroopa(koopaTroopa, lado);

	}

	@Override
	public void visit(Lakitu lakitu, int lado) {

		estadoActual.colisionaConLakitu(lakitu, lado);

	}

	@Override
	public void visit(PiranhaPlant piranhaPlant, int lado) {

		estadoActual.colisionaConPiranhaPlant(piranhaPlant, lado);
	}

	@Override
	public void visit(Spiny spiny, int lado) {

		estadoActual.colisionaConSpiny(spiny, lado);
	}

	@Override
	public void visit(ChampinionVerde champinionVerde) {

		estadoActual.serAfectadoPorPowerUp(champinionVerde);
		juego.getNivelActual().eliminarPowerUp(champinionVerde);
	}

	@Override
	public void visit(Estrella estrella) {

		estadoActual.serAfectadoPorPowerUp(estrella);
		juego.getNivelActual().eliminarPowerUp(estrella);

	}

	@Override
	public void visit(FlorDeFuego florDeFuego) {

		estadoActual.serAfectadoPorPowerUp(florDeFuego);
		juego.getNivelActual().eliminarPowerUp(florDeFuego);

	}

	@Override
	public void visit(Moneda moneda) {

		estadoActual.serAfectadoPorPowerUp(moneda);
		juego.getNivelActual().eliminarPowerUp(moneda);

	}

	@Override
	public void visit(SuperChampinion superChampinion) {

		estadoActual.serAfectadoPorPowerUp(superChampinion);
		juego.getNivelActual().eliminarPowerUp(superChampinion);

	}

	@Override
	public void visit(Llegada llegada) {

		int numeroProximoNivel = juego.getNivelActual().getNumero() + 1;
		juego.reproducirSonidoNivelTerminado();
		if (numeroProximoNivel == 4) {
			juego.ganarJuego();
		} else {
			juego.cambiarNivel(numeroProximoNivel);
		}

	}

	private void corregirPosicion(EntidadLogica entidadLogica, int lado) {
		switch (lado) {
		case DetectorColisiones.DERECHA_1:
			corregirPosicionEnColisionPorDerecha(entidadLogica);
			break;
		case DetectorColisiones.IZQUIERDA_2:
			corregirPosicionEnColisionPorIzquierda(entidadLogica);
			break;
		case DetectorColisiones.ARRIBA_3:
			velocidadSalto = 0;
			corregirPosicionEnColisionPorAbajo(entidadLogica);
			break;
		case DetectorColisiones.ABAJO_4:
			corregirPosicionEnColisionPorEncima(entidadLogica);
			velocidadSalto = 0;
			enAire = false;
			break;
		}
	}

	private void corregirPosicionEnColisionPorEncima(EntidadLogica entidadColisionada) {
		this.setY(entidadColisionada.getY() - this.getAlto());
	}

	private void corregirPosicionEnColisionPorIzquierda(EntidadLogica entidadColisionada) {
		this.setX(entidadColisionada.getX() + this.getAncho());
	}

	private void corregirPosicionEnColisionPorDerecha(EntidadLogica entidadColisionada) {
		this.setX(entidadColisionada.getX() - this.getAncho());
	}

	private void corregirPosicionEnColisionPorAbajo(EntidadLogica entidadColisionada) {
		this.setY(entidadColisionada.getY() + this.getAlto() + 9);
	}

}