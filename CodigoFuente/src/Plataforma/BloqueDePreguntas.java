package Plataforma;

import java.util.Random;

import Auxiliares.ConstantesAuxiliares;
import Fabricas.FabricaSprites;
import Fabricas.Sprite;
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
	
    public PowerUp mostrarPowerUp() {
        tienePowerUp = false;
        
        Random random = new Random();
        int powerUpTipo = random.nextInt(5) + 1;
        
        switch (powerUpTipo) {
            case 1:
                return new Estrella(fabricaSprites.getEstrella(), posicion.getX(),
                                    posicion.getY() - ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                    ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                    ConstantesAuxiliares.TAMANOBLOQUE_ALTO);
            case 2:
                return new SuperChampinion(fabricaSprites.getSuperChampinion(), posicion.getX(),
                                          posicion.getY() - ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                          ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                          ConstantesAuxiliares.TAMANOBLOQUE_ALTO);
            case 3:
                return new FlorDeFuego(fabricaSprites.getFlorDeFuego(), posicion.getX(),
                                       posicion.getY() - ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                       ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                       ConstantesAuxiliares.TAMANOBLOQUE_ALTO);
            case 4:
                return new ChampinionVerde(fabricaSprites.getChampinionVerde(), posicion.getX(),
                                          posicion.getY() - ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                          ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                          ConstantesAuxiliares.TAMANOBLOQUE_ALTO);
            case 5:
                return new Moneda(fabricaSprites.getMoneda(), posicion.getX(),
                                      posicion.getY() - ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                      ConstantesAuxiliares.TAMANOBLOQUE_ALTO,
                                      ConstantesAuxiliares.TAMANOBLOQUE_ALTO);
            default:
                return null;
        }
    }
	
	
	
	public boolean getTienePowerUp() {
		return tienePowerUp;
	}

	@Override
	public void acceptMario(VisitorMario visitorMario, int lado) {
		visitorMario.visit(this);
		
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
