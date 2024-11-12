package Mario;

import Auxiliares.ConstantesAuxiliares;
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


public class Mario extends EntidadDinamica implements EntidadLogicaJugador, VisitorMario{
	
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
        super(sprite,x,y,ancho,alto);
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

        if(derecha) {
            this.moverseDerecha();
        }

        if(izquierda) {
            this.moverseIzquierda();

        }


        if (saltar) {
            this.saltar();
        }
        
        actualizarSprite();
	}

	
    public void aterrizar() {
        velocidadSalto = 0;
        enAire = false;
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
        	if(orientacion > 0) {
        		spriteActual = MARIO_JUMPING_RIGHT;
        		sprite = estadoActual.getSpriteSaltandoDerecha();
        	}else {
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
	public boolean atacar(Enemigo enemigo) {
		return estadoActual.atacar(enemigo);
	}
	
	@Override
	public boolean atacar(Spiny spiny) {
		return estadoActual.atacar(spiny);
	}
	
	@Override
	public boolean atacar(PiranhaPlant piranhaPlant) {
		return estadoActual.atacar(piranhaPlant);
	}
	
	@Override
	public void crearBolaDeFuego() {
		estadoActual.crearBolaDeFuego();
	}
    
    private void chequearSiTieneQueCaer() {
        if(posicion.getX() >= ultimaPos + ConstantesAuxiliares.TAMANOBLOQUE_ANCHO / 2) {
            enAire = true;
            ultimaPos = posicion.getX();
        }

        if(posicion.getX() <= ultimaPos - ConstantesAuxiliares.TAMANOBLOQUE_ANCHO / 2) {
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
            	if(orientacion > 0) {
            		nuevoSprite = MARIO_IDLE_RIGHT;
            		sprite = estadoActual.getSpriteIdleDerecha();
            	}else {
            		nuevoSprite = MARIO_IDLE_LEFT;
            		sprite = estadoActual.getSpriteIdeIzquierda();
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
    
    public boolean chocarLadrilloSolido() {
    	return estadoActual.chocharLadrilloSolido();
    }


	@Override
	public int getVidas() {
		return vidas;
	}
	
	public void notificarMarioEstrella() {
		juego.marioEstaEnEstrella();
	}




	@Override
	public void visit(BloqueSolido bloqueSolido, int lado) {
		corregirPosicion(bloqueSolido, lado);
	    switch (lado) {
	        case 1:
	        	//Que pasa cuando mario colisiona con bloqueSolido por lado 1

	            break;
	        case 2: 
	        	//Que pasa cuando mario colisiona con bloqueSolido por lado 2
	            break;
	        case 3:
	        	//Que pasa cuando mario colisiona con bloqueSolido por lado 3


	            break;
	        case 4: 
	        	//Que pasa cuando mario colisiona con bloqueSolido por lado 4
	        	velocidadSalto = 0;
	            enAire = false;
	            break;
	    }
	}


	@Override
	public void visit(BloqueDePreguntas bloqueDePreguntas) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(LadrilloSolido ladrilloSolido) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(Tuberia tuberia) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(Vacio vacio) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(BuzzyBeetle buzzyBeetle) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(Goomba gommba) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(KoopaTroopa koopaTroopa) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(Lakitu lakitu) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(PiranhaPlant piranhaPlant) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(Spiny spiny) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(ChampinionVerde champinionVerde) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(Estrella estrella) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(FlorDeFuego florDeFuego) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(Moneda moneda) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(SuperChampinion superChampinion) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(Llegada llegada) {
		// TODO Auto-generated method stub
		
	}
	
	private void corregirPosicion(EntidadLogica entidadLogica, int lado) {
	    switch (lado) {
        case 1:
            corregirPosicionEnColisionPorDerecha(entidadLogica);
            break;
        case 2: 
            corregirPosicionEnColisionPorIzquierda(entidadLogica);
            break;
        case 3:
        	corregirPosicionEnColisionPorAbajo(entidadLogica);
            break;
        case 4: 
            corregirPosicionEnColisionPorEncima(entidadLogica);
            break;
	    }
	}
	
	private void corregirPosicionEnColisionPorEncima(EntidadLogica entidadColisionada) {
		this.setY(entidadColisionada.getY() - entidadColisionada.getAlto()); 
	}
	
	private void corregirPosicionEnColisionPorIzquierda(EntidadLogica entidadColisionada) {
		this.setX(entidadColisionada.getX() + entidadColisionada.getAncho()); 
	}
	
	private void corregirPosicionEnColisionPorDerecha(EntidadLogica entidadColisionada) {
		this.setX(entidadColisionada.getX() - this.getAncho()); 
	}
	
	private void corregirPosicionEnColisionPorAbajo(EntidadLogica entidadColisionada) {
		this.setY(entidadColisionada.getY()  + entidadColisionada.getAlto()); 
	}
	

}