package Plataforma;

import java.util.Random;

import Auxiliares.ConstantesAuxiliares;
import Fabricas.FabricaSprites;
import Fabricas.Sprite;
import Juego.Juego;
import Powerups.ChampinionVerde;
import Powerups.Estrella;
import Powerups.FlorDeFuego;
import Powerups.Moneda;
import Powerups.PowerUp;
import Powerups.SuperChampinion;
import Visitor.VisitorBolaDeFuego;
import Visitor.VisitorEnemigo;
import Visitor.VisitorMario;

public class BloqueDePreguntas extends Plataforma{

	protected boolean tienePowerUp;
	protected FabricaSprites fabricaSprites;
	
	public BloqueDePreguntas(Sprite sprite, int x,int y,int ancho ,int alto,FabricaSprites fabricaSprites) {
		
		super(sprite,x,y,ancho,alto);
		tienePowerUp = true;
		this.fabricaSprites = fabricaSprites;
	}
	
    public void mostrarPowerUp(Juego juego) {
        tienePowerUp = false;
        PowerUp powerUp;
        Random random = new Random();
        int powerUpTipo = random.nextInt(5) + 1;
        
        switch (powerUpTipo) {
            case 1:
            	powerUp = new Estrella(fabricaSprites.getEstrella(), posicion.getX(),
                                    posicion.getY() - ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                    ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                    ConstantesAuxiliares.TAMANOBLOQUE_ALTO);
            	break;
            case 2:
            	powerUp = new SuperChampinion(fabricaSprites.getSuperChampinion(), posicion.getX(),
                                          posicion.getY() - ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                          ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                          ConstantesAuxiliares.TAMANOBLOQUE_ALTO);
            	break;
            case 3:
            	powerUp=  new FlorDeFuego(fabricaSprites.getFlorDeFuego(), posicion.getX(),
                                       posicion.getY() - ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                       ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                       ConstantesAuxiliares.TAMANOBLOQUE_ALTO);
            	break;
            	
            case 4:
            	powerUp = new ChampinionVerde(fabricaSprites.getChampinionVerde(), posicion.getX(),
                                          posicion.getY() - ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                          ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                          ConstantesAuxiliares.TAMANOBLOQUE_ALTO);
            	break;
            case 5:
            	powerUp = new Moneda(fabricaSprites.getMoneda(), posicion.getX(),
                                      posicion.getY() - ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                      ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                      ConstantesAuxiliares.TAMANOBLOQUE_ALTO);
            	break;
            default:
            	powerUp = null;
                break;
        }
        //Cambiar el sprite del bloque de preguntas al bloque de preguntas vacio.
        juego.getNivelActual().agregarPowerUp(powerUp);
        juego.registrarObserverEntidad(powerUp);
    }
	
	
	
	public boolean getTienePowerUp() {
		return tienePowerUp;
	}

	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {
		visitorMario.visit(this, lado);
		
	}

	@Override
	public void acceptBolaDeFuego(VisitorBolaDeFuego visitorBolaDeFuego) {
		visitorBolaDeFuego.visit(this);
		
	}

	@Override
	public void acceptEnemigo(VisitorEnemigo visitorEnemigo) {
		visitorEnemigo.visit(this);
		
	};
	
}
