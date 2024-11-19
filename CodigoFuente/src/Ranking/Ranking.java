package Ranking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Juego.Jugador;

public class Ranking {

	private static Ranking instancia;
	private Jugador[] topRanking;
	private static final int size = 5;
	private int cantJugadores;

	private Ranking() {
		topRanking = new Jugador[size];
		cantJugadores = 0;
	}

	public static Ranking getInstancia() {
		if (instancia == null) {
			instancia = new Ranking();
		}
		return instancia;
	}

	public void agregarJugador(Jugador jugador) {
		if (cantJugadores < size) {
			topRanking[cantJugadores] = jugador;
			cantJugadores++;
		} else if (jugador.getPuntaje() > topRanking[size - 1].getPuntaje()) {
			topRanking[size - 1] = jugador;
		}
		ordenar();
	}

	private void ordenar() {
		for (int i = 0; i < cantJugadores - 1; i++) {
			for (int j = i + 1; j < cantJugadores; j++) {
				if (topRanking[i].getPuntaje() < topRanking[j].getPuntaje()) {
					intercambiar(i, j);
				}
			}
		}
	}

	private void intercambiar(int pos1, int pos2) {
		Jugador aux = topRanking[pos1];
		topRanking[pos1] = topRanking[pos2];
		topRanking[pos2] = aux;
	}

	public void mostrarRanking() {
		for (int pos = 0; pos < cantJugadores; pos++) {
			Jugador jugador = topRanking[pos];
		}
	}

	public void cargarRankingDesdeArchivo() {
		try (BufferedReader reader = new BufferedReader(new FileReader("src/Ranking/ranking.txt"))) {
			String linea;
			cantJugadores = 0;

			while ((linea = reader.readLine()) != null && cantJugadores < size) {
				String[] partes = linea.split(" Puntaje: ");
				String nombre = partes[0].substring(8);
				int puntaje = Integer.parseInt(partes[1]);
				topRanking[cantJugadores] = new Jugador(nombre, puntaje);
				cantJugadores++;
			}
		} catch (IOException e) {
			System.err.println("Error al cargar el ranking desde el archivo: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.err.println("Formato de puntaje incorrecto en el archivo: " + e.getMessage());
		}
	}

	public void guardarRankingEnArchivo() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Ranking/ranking.txt"))) {
			for (int pos = 0; pos < cantJugadores; pos++) {
				Jugador jugador = topRanking[pos];
				writer.write("Nombre: " + jugador.getNombre() + " Puntaje: " + jugador.getPuntaje());
				writer.newLine();
			}
		} catch (IOException e) {
			System.err.println("Error al guardar el ranking en el archivo: " + e.getMessage());
		}
	}

	public Jugador getPrimerPuesto() {
		return topRanking[0];
	}

	public Jugador getSegundoPuesto() {
		return topRanking[1];
	}

	public Jugador getTercerPuesto() {
		return topRanking[2];
	}

	public Jugador getCuartoPuesto() {
		return topRanking[3];
	}

	public Jugador getQuintoPuesto() {
		return topRanking[4];
	}
}