package Juego;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ReproductorSonido {

	private Map<String, Clip> clips = new HashMap<>();
	private float volumenGeneral = -10.0f; // Volumen inicial en decibelios (puedes ajustar según lo que quieras)

	public void cargarSonido(String nombre, String rutaArchivo) {
		try {
			InputStream audioSrc = getClass().getResourceAsStream(rutaArchivo);
			if (audioSrc == null) {
				throw new IOException("No se pudo encontrar el archivo: " + rutaArchivo);
			}

			BufferedInputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);

			// Aplicar el volumen general al nuevo clip
			ajustarVolumenClip(clip, volumenGeneral);

			// Almacenar el clip en el mapa
			clips.put(nombre, clip);
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void reproducir(String nombre) {
		Clip clip = clips.get(nombre);
		if (clip != null) {
			if (clip.isRunning()) {
				clip.stop();
			}
			clip.setFramePosition(0);
			clip.start();
		}
	}

	public void reproducirEnBucle(String nombre) {
		Clip clip = clips.get(nombre);
		if (clip != null) {
			clip.setFramePosition(0);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	public void detener(String nombre) {
		Clip clip = clips.get(nombre);
		if (clip != null && clip.isRunning()) {
			clip.stop();
		}
	}

	public void ajustarVolumenGeneral(float nivelDecibelios) {
		volumenGeneral = nivelDecibelios;
		for (Clip clip : clips.values()) {
			ajustarVolumenClip(clip, volumenGeneral);
		}
	}

	private void ajustarVolumenClip(Clip clip, float nivelDecibelios) {
		if (clip != null && clip.isOpen()) {
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(nivelDecibelios);
		}
	}
}
