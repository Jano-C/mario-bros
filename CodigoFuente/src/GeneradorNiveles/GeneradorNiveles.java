package GeneradorNiveles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import Enemigo.BuzzyBeetle;
import Enemigo.Goomba;
import Enemigo.KoopaTroopa;
import Enemigo.Lakitu;
import Enemigo.Spiny;
import Fabricas.FabricaEntidades;
import Juego.Juego;
import Juego.Nivel;
import Vistas.ConstantesVentana;

public class GeneradorNiveles {

	private static final int TAMANO_BLOQUE = 32;
	private static final int TAMANO_BLOQUE_Y_MEDIO = 48;
	protected FabricaEntidades fabricaEntidades;
	protected String[] nivelAGenerar;
	protected Juego juego;

	public GeneradorNiveles(FabricaEntidades fabricaEntidades, Juego juego) {
		this.juego = juego;
		this.fabricaEntidades = fabricaEntidades;
	}

	private String[] cargarNivelDesdeArchivo(String rutaArchivo) {
		try {

			List<String> lineas = Files.readAllLines(Paths.get(rutaArchivo));
			return lineas.toArray(new String[0]);
		} catch (IOException e) {
			e.printStackTrace();
			return new String[] {};
		}
	}

	public Nivel generarNivel(int numeroNivel) {

		Nivel nivelGenerado = new Nivel(numeroNivel);

		switch (numeroNivel) {
		case 1:
			nivelAGenerar = cargarNivelDesdeArchivo("src/Niveles/nivel1.txt");
			break;
		case 2:
			nivelAGenerar = cargarNivelDesdeArchivo("src/niveles/nivel2.txt");
			break;
		case 3:
			nivelAGenerar = cargarNivelDesdeArchivo("src/niveles/nivel3.txt");
			break;

		default:
			nivelAGenerar = cargarNivelDesdeArchivo("src/niveles/nivel1.txt");
			break;
		}

		for (int y = 0; y < nivelAGenerar.length; y++) {
			for (int x = 0; x < nivelAGenerar[y].length(); x++) {

				char entidadACrear = nivelAGenerar[y].charAt(x);
				int posicionXPixeles = x * TAMANO_BLOQUE * ConstantesVentana.ESCALA;
				int posicionYPixeles = y * TAMANO_BLOQUE * ConstantesVentana.ESCALA;

				int posicionYPixelesParaAltos = posicionYPixeles - TAMANO_BLOQUE / 2;

				switch (entidadACrear) {

				// Mario
				case 'J':
					nivelGenerado.agregarJugador(fabricaEntidades.getMario(posicionXPixeles, posicionYPixeles,
							TAMANO_BLOQUE, TAMANO_BLOQUE, juego));
					break;

				// Enemigos
				case 'B':
					BuzzyBeetle buzzyBeetle = fabricaEntidades.getBuzzyBeetle(posicionXPixeles, posicionYPixeles,
							TAMANO_BLOQUE, TAMANO_BLOQUE);
					nivelGenerado.agregarEnemigo(buzzyBeetle);
					break;
				case 'G':
					Goomba goomba = fabricaEntidades.getGoomba(posicionXPixeles, posicionYPixeles, TAMANO_BLOQUE,
							TAMANO_BLOQUE);
					nivelGenerado.agregarEnemigo(goomba);
					break;
				case 'K':
					KoopaTroopa koopaTroopa = fabricaEntidades.getKoopaTroopa(posicionXPixeles,
							posicionYPixelesParaAltos, TAMANO_BLOQUE, TAMANO_BLOQUE_Y_MEDIO);
					nivelGenerado.agregarEnemigo(koopaTroopa);
					break;
				case 'L':
					Lakitu lakitu = fabricaEntidades.getLakitu(posicionXPixeles, posicionYPixelesParaAltos,
							TAMANO_BLOQUE, TAMANO_BLOQUE_Y_MEDIO, juego);
					nivelGenerado.agregarEnemigo(lakitu);
					nivelGenerado.agregarLakitu(lakitu);
					break;
				case 'P':
					nivelGenerado.agregarEnemigo(fabricaEntidades.getPiranhaPlant(posicionXPixeles + 16,
							posicionYPixelesParaAltos, TAMANO_BLOQUE, TAMANO_BLOQUE_Y_MEDIO));
					break;
				case 'Y':
					Spiny spiny = fabricaEntidades.getSpiny(posicionXPixeles, posicionYPixeles, TAMANO_BLOQUE,
							TAMANO_BLOQUE);
					nivelGenerado.agregarEnemigo(spiny);
					break;

				// PowerUps
				case 'C':
					nivelGenerado.agregarPowerUp(fabricaEntidades.getChampinionVerde(posicionXPixeles, posicionYPixeles,
							TAMANO_BLOQUE, TAMANO_BLOQUE));
					break;
				case 'E':
					nivelGenerado.agregarPowerUp(fabricaEntidades.getEstrella(posicionXPixeles, posicionYPixeles,
							TAMANO_BLOQUE, TAMANO_BLOQUE));
					break;
				case 'F':
					nivelGenerado.agregarPowerUp(fabricaEntidades.getFlorDeFuego(posicionXPixeles, posicionYPixeles,
							TAMANO_BLOQUE, TAMANO_BLOQUE));
					break;
				case 'M':
					nivelGenerado.agregarPowerUp(fabricaEntidades.getMoneda(posicionXPixeles, posicionYPixeles,
							TAMANO_BLOQUE, TAMANO_BLOQUE));
					break;
				case 'S':
					nivelGenerado.agregarPowerUp(fabricaEntidades.getSuperChampinion(posicionXPixeles, posicionYPixeles,
							TAMANO_BLOQUE, TAMANO_BLOQUE));
					break;

				// Plataformas
				case '1':
					nivelGenerado.agregarPlataforma(fabricaEntidades.getBloqueDePreguntas(posicionXPixeles,
							posicionYPixeles, TAMANO_BLOQUE, TAMANO_BLOQUE, juego.getFabricaSprites()));
					break;
				case '2':
					nivelGenerado.agregarPlataforma(fabricaEntidades.getBloqueSolido(posicionXPixeles, posicionYPixeles,
							TAMANO_BLOQUE, TAMANO_BLOQUE));
					break;
				case '3':
					nivelGenerado.agregarPlataforma(fabricaEntidades.getLadrilloSolido(posicionXPixeles,
							posicionYPixeles, TAMANO_BLOQUE, TAMANO_BLOQUE));
					break;
				case '4':
					nivelGenerado.agregarPlataforma(fabricaEntidades.getTuberiaIzquierda(posicionXPixeles,
							posicionYPixeles, TAMANO_BLOQUE, TAMANO_BLOQUE));
					break;
				case '6':
					nivelGenerado.agregarPlataforma(fabricaEntidades.getTuberiaDerecha(posicionXPixeles,
							posicionYPixeles, TAMANO_BLOQUE, TAMANO_BLOQUE));
					break;
				case '7':
					nivelGenerado.agregarPlataforma(fabricaEntidades.getTechoTuberiaIzquierda(posicionXPixeles,
							posicionYPixeles, TAMANO_BLOQUE, TAMANO_BLOQUE));
					break;
				case '8':
					nivelGenerado.agregarPlataforma(fabricaEntidades.getTechoTuberiaDerecha(posicionXPixeles,
							posicionYPixeles, TAMANO_BLOQUE, TAMANO_BLOQUE));
					break;
				case '5':
					nivelGenerado.agregarPlataforma(fabricaEntidades.getVacio(posicionXPixeles, posicionYPixeles,
							TAMANO_BLOQUE, TAMANO_BLOQUE));
					break;
				case '9':
					nivelGenerado.agregarPlataforma(
							fabricaEntidades.getLlegada(posicionXPixeles, posicionYPixeles - 300, TAMANO_BLOQUE, 336));
					break;
				case ' ':
					break;

				}
			}
		}
		return nivelGenerado;
	}

}
