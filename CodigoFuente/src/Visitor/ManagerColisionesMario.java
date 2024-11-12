package Visitor;

import java.util.ArrayList;
import java.util.List;
import Enemigo.BuzzyBeetle;
import Enemigo.Goomba;
import Enemigo.KoopaTroopa;
import Enemigo.Lakitu;
import Enemigo.PiranhaPlant;
import Enemigo.Spiny;
import Entidades.EntidadLogica;
import Fabricas.Sprite;
import Juego.Juego;
import Juego.Nivel;
import Mario.Mario;
import Plataforma.BloqueDePreguntas;
import Plataforma.BloqueSolido;
import Plataforma.LadrilloSolido;
import Plataforma.Llegada;
import Plataforma.Tuberia;
import Plataforma.Vacio;
import Powerups.ChampinionVerde;
import Powerups.Estrella;
import Powerups.FlorDeFuego;
import Powerups.Moneda;
import Powerups.PowerUp;
import Powerups.SuperChampinion;

public class ManagerColisionesMario {

//	protected Mario mario;
//	protected Nivel nivelActual;
//	protected Juego juego;
//	protected MetodosAuxiliares metodosAuxiliares;
//	private List<EntidadLogica> enemigosAEliminar;
//	private List<EntidadLogica> powerUpsAEliminar;
//	private List<EntidadLogica> plataformasAEliminar;
//	private long lastCollisionTime = 0;
//	private static final long COLLISION_DELAY = 500; 
//
//	
//	public ManagerColisionesMario(Juego juego) {
//		this.juego = juego;
//		this.mario = juego.getNivelActual().getMario();
//		this.nivelActual = juego.getNivelActual();
//		enemigosAEliminar = new ArrayList<>();
//		plataformasAEliminar = new ArrayList<>();
//		powerUpsAEliminar = new ArrayList<>();
//		metodosAuxiliares = new MetodosAuxiliares();
//	}
//	
//	public void visit(BloqueSolido bloqueSolido) {
//
//		//Testing
//		if (colisionaConEntidadDerecha(bloqueSolido)) {
//		    moverMarioIzquierda(bloqueSolido);
//		}
//		if(colisionaConEntidadArriba(bloqueSolido)) {
//			moverMarioAbajo(bloqueSolido);
//		}
//	    if (colisionaConEntidadAbajo(bloqueSolido)) {
//	        moverMarioArriba(bloqueSolido);
//	    }
//	    if (colisionaConEntidadIzquierda(bloqueSolido)) {
//	        moverMarioDerecha(bloqueSolido);
//	    }
//	}
//	
//	public void visit(BloqueDePreguntas bloqueDePreguntas) {
//		
//		if (colisionaConEntidadDerecha(bloqueDePreguntas)) {
//		    moverMarioIzquierda(bloqueDePreguntas);
//		}
//		if(colisionaConEntidadArriba(bloqueDePreguntas)) {
//			
//			if(bloqueDePreguntas.getTienePowerUp()) {
//				
//				vaciarBloqueDePreguntas(bloqueDePreguntas);
//				PowerUp powerUp = bloqueDePreguntas.mostrarPowerUp();
//				juego.getNivelActual().agregarPowerUp(powerUp);
//				juego.registrarObserverEntidad(powerUp);
//			}
//			
//			moverMarioAbajo(bloqueDePreguntas);
//		}
//	    if (colisionaConEntidadAbajo(bloqueDePreguntas)) {
//	        moverMarioArriba(bloqueDePreguntas);
//	    }
//	    if (colisionaConEntidadIzquierda(bloqueDePreguntas)) {
//	        moverMarioDerecha(bloqueDePreguntas);
//	    }
//	}
//	
//	public void visit(LadrilloSolido ladrilloSolido) {
//		if (colisionaConEntidadDerecha(ladrilloSolido)) {
//		    moverMarioIzquierda(ladrilloSolido);
//		}
//		if(colisionaConEntidadArriba(ladrilloSolido)) {
//			if(mario.chocarLadrilloSolido()) {
//				juego.reproducirSonidoLadrilloRoto();
//				plataformasAEliminar.add(ladrilloSolido);
//			}
//			moverMarioAbajo(ladrilloSolido);
//		}
//	    if (colisionaConEntidadAbajo(ladrilloSolido)) {
//	        moverMarioArriba(ladrilloSolido);
//	    }
//	    if (colisionaConEntidadIzquierda(ladrilloSolido)) {
//	        moverMarioDerecha(ladrilloSolido);
//	    }
//	}
//	
//	public void visit(Tuberia tuberia) {
//		if (colisionaConEntidadDerecha(tuberia)) {
//		    moverMarioIzquierda(tuberia);
//		}
//		if(colisionaConEntidadArriba(tuberia)) {
//			moverMarioAbajo(tuberia);
//		}
//	    if (colisionaConEntidadAbajo(tuberia)) {
//	        moverMarioArriba(tuberia);
//	    }
//	    if (colisionaConEntidadIzquierda(tuberia)) {
//	        moverMarioDerecha(tuberia);
//	    }
//	}
//	
//	public void visit(Vacio vacio) {
//		if(colisionaConEntidadArriba(vacio)) {
//			mario.caerAlVacio();
//		}
//	}
//	
//	public void visit(BuzzyBeetle buzzyBeetle) {
//	    long currentTime = System.currentTimeMillis();
//	    if (currentTime - lastCollisionTime >= COLLISION_DELAY) {
//			if (colisionaConEntidadAbajo(buzzyBeetle)) {
//				mario.atacar(buzzyBeetle);
//				juego.reproducirSonidoBump();
//				enemigosAEliminar.add(buzzyBeetle);
//		    }
//		    else if (colisionaConEntidadDerecha(buzzyBeetle) || colisionaConEntidadIzquierda(buzzyBeetle) || colisionaConEntidadArriba(buzzyBeetle)) {
//		    	buzzyBeetle.atacar(mario);
//		    }
//	    }
//	}
//	
//	public void visit(Goomba goomba) {
//	    long currentTime = System.currentTimeMillis();
//	    if (currentTime - lastCollisionTime >= COLLISION_DELAY) {
//	        if (colisionaConEntidadAbajo(goomba)) {
//	            mario.atacar(goomba);
//	            juego.reproducirSonidoBump();
//	            enemigosAEliminar.add(goomba);
//	            lastCollisionTime = currentTime;
//	        } else if (colisionaConEntidadDerecha(goomba) || colisionaConEntidadIzquierda(goomba) || colisionaConEntidadArriba(goomba)) {
//	            goomba.atacar(mario);
//	            lastCollisionTime = currentTime;
//	        }
//	    }
//	}
//	
//	public void visit(KoopaTroopa koopaTroopa) {
//		long currentTime = System.currentTimeMillis();
//		if (currentTime - lastCollisionTime >= COLLISION_DELAY) {
//			if (colisionaConEntidadAbajo(koopaTroopa)) {
//				mario.atacar(koopaTroopa);
//				juego.reproducirSonidoBump();
//				if(koopaTroopa.getVidas() == 0) {
//					enemigosAEliminar.add(koopaTroopa);
//				}
//		    }
//		    else if (colisionaConEntidadDerecha(koopaTroopa) || colisionaConEntidadIzquierda(koopaTroopa) || colisionaConEntidadArriba(koopaTroopa)) {
//		    	koopaTroopa.atacar(mario);
//		    }
//	    }
//	}
//	
//	public void visit(Lakitu lakitu) {
//		long currentTime = System.currentTimeMillis();
//		if (currentTime - lastCollisionTime >= COLLISION_DELAY) {
//			if (colisionaConEntidadAbajo(lakitu)) {
//				mario.atacar(lakitu);
//				juego.reproducirSonidoBump();
//				enemigosAEliminar.add(lakitu);
//		    }
//		    else if (colisionaConEntidadDerecha(lakitu) || colisionaConEntidadIzquierda(lakitu) || colisionaConEntidadArriba(lakitu)) {
//		    	lakitu.atacar(mario);
//		    }
//	    }
//	}
//	
//	public void visit(PiranhaPlant piranhaPlant) {
//		long currentTime = System.currentTimeMillis();
//		if (currentTime - lastCollisionTime >= COLLISION_DELAY) {
//			if (colisionaCon(piranhaPlant)) {
//				if(mario.atacar(piranhaPlant)) {
//					juego.reproducirSonidoBump();
//					enemigosAEliminar.add(piranhaPlant);
//				}
//		    }
//	    }
//	}
//	
//	public void visit(Spiny spiny) {	    
//		long currentTime = System.currentTimeMillis();
//		if (currentTime - lastCollisionTime >= COLLISION_DELAY) {
//			if (colisionaConEntidadAbajo(spiny)) {
//				
//				if(mario.atacar(spiny)) {
//					juego.reproducirSonidoBump();
//					enemigosAEliminar.add(spiny);
//				}
//
//		    }
//		    else if (colisionaConEntidadDerecha(spiny) || colisionaConEntidadIzquierda(spiny) || colisionaConEntidadArriba(spiny)) {
//		    	spiny.atacar(mario);
//		    }
//	    }
//	}
//	
//	public void visit(FlorDeFuego florDeFuego) {
//		if(colisionaCon(florDeFuego)) {
//			mario.serAfectadoPorPowerUp(florDeFuego);
//			powerUpsAEliminar.add(florDeFuego);
//		}
//	}
//	
//	@Override
//	public void visit(ChampinionVerde champinionVerde) {
//		if(colisionaCon(champinionVerde)) {
//			mario.serAfectadoPorPowerUp(champinionVerde);
//			powerUpsAEliminar.add(champinionVerde);
//		}
//	}
//
//	@Override
//	public void visit(Estrella estrella) {
//		if(colisionaCon(estrella)) {
//			mario.serAfectadoPorPowerUp(estrella);
//			powerUpsAEliminar.add(estrella);
//		}
//	}
//
//	@Override
//	public void visit(Moneda moneda) {
//		if(colisionaCon(moneda)) {
//			mario.serAfectadoPorPowerUp(moneda);
//			powerUpsAEliminar.add(moneda);
//			juego.agarrarMoneda();
//		}
//	}
//	
//	@Override
//	public void visit(Llegada llegada) {
//		if(colisionaCon(llegada)) {
//			int numeroProximoNivel = juego.getNivelActual().getNumero() + 1;
//			juego.reproducirSonidoNivelTerminado();
//			if(numeroProximoNivel == 4) {
//				juego.ganarJuego();
//			}else {
//				juego.cambiarNivel(numeroProximoNivel);
//			}
//			
//		}
//		
//	}
//
//	@Override
//	public void visit(SuperChampinion superChampinion) {
//		if(colisionaCon(superChampinion)) {
//			mario.serAfectadoPorPowerUp(superChampinion);
//			powerUpsAEliminar.add(superChampinion);
//		}
//	}
//	
//    private boolean colisionaCon(EntidadLogica entidadLogica) {
//        return colisionaConEntidadDerecha(entidadLogica) || colisionaConEntidadArriba(entidadLogica) || colisionaConEntidadIzquierda(entidadLogica) || colisionaConEntidadAbajo(entidadLogica);
//    }
//    
//    private boolean colisionaConEntidadDerecha(EntidadLogica entidadLogica) {
//    	//Este metodo esta redefinido para que mario detecte las colisiones correctamente.
//        return mario.getX() + mario.getAncho() > entidadLogica.getX() && 
//               mario.getX() + 8 < entidadLogica.getX() && 
//               mario.getY() + mario.getAlto() - 8 > entidadLogica.getY() && 
//               mario.getY() + 8 < entidadLogica.getY() + entidadLogica.getAlto();
//    }
//   
//    private boolean colisionaConEntidadIzquierda(EntidadLogica entidadLogica) {
//        return metodosAuxiliares.colisionaConEntidadIzquierda(mario, entidadLogica);
//    }
//   
//    private boolean colisionaConEntidadAbajo(EntidadLogica entidadLogica) {
//        return metodosAuxiliares.colisionaConEntidadAbajo(mario, entidadLogica); 
//    }
//
//    private boolean colisionaConEntidadArriba(EntidadLogica entidadLogica) {
//    	return metodosAuxiliares.colisionaConEntidadArriba(mario, entidadLogica);
//    }
//
//   
//   private void moverMarioAbajo(EntidadLogica entidadLogica) {
//	   mario.setY(entidadLogica.getY() + mario.getAlto() + 4);
//       mario.notificarObserver();
//   }
//   
//   private void moverMarioArriba(EntidadLogica entidadLogica) {
//       mario.aterrizar();
//       mario.setAire(false);
//       mario.notificarObserver();
//   }
//
//   private void moverMarioIzquierda(EntidadLogica entidadLogica) {
//	   mario.setX(entidadLogica.getX() - mario.getAncho()); 
//   }
//
//   private void moverMarioDerecha(EntidadLogica entidadLogica) {
//	   mario.setX(entidadLogica.getX() + entidadLogica.getAncho()); 
//   }
//   
//   
//   public void eliminarEnemigos() {
//       for (EntidadLogica enemigo : enemigosAEliminar) {
//    	   metodosAuxiliares.ocultarImagen(enemigo);
//    	   nivelActual.getEnemigos().remove(enemigo);
//       }
//       enemigosAEliminar.clear();
//   }
//   
//   
//   public void eliminarPowerUps() {
//       for (EntidadLogica powerUp : powerUpsAEliminar) {
//           
//    	   metodosAuxiliares.ocultarImagen(powerUp);
//    	   nivelActual.getPowerUps().remove(powerUp);
//           
//       }
//       powerUpsAEliminar.clear();
//   }
//   
//   public void eliminarPlataformas() {
//       for (EntidadLogica plataforma : plataformasAEliminar) {
//           
//    	   metodosAuxiliares.ocultarImagen(plataforma);
//    	   nivelActual.getPlataformas().remove(plataforma);
//           
//       }
//       powerUpsAEliminar.clear();
//   }

//   public void matarMario() {
//	   juego.getControladorVistas().mostrarPantallaPerder();
//   }
   
//   protected void vaciarBloqueDePreguntas(EntidadLogica entidadLogica) {
//       entidadLogica.setSprite(new Sprite("/Imagenes/1bloqueDePreguntasVacio.png"));
//       entidadLogica.notificarObserver();
//   }
//
//@Override
//public void visit(BloqueSolido bloqueSolido, int lado) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(BloqueDePreguntas bloqueDePreguntas) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(LadrilloSolido ladrilloSolido) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(Tuberia tuberia) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(Vacio vacio) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(BuzzyBeetle buzzyBeetle) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(Goomba gommba) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(KoopaTroopa koopaTroopa) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(Lakitu lakitu) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(PiranhaPlant piranhaPlant) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(Spiny spiny) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(ChampinionVerde champinionVerde) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(Estrella estrella) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(FlorDeFuego florDeFuego) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(Moneda moneda) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(SuperChampinion superChampinion) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void visit(Llegada llegada) {
//	// TODO Auto-generated method stub
//	
//}
//	





   
   
}