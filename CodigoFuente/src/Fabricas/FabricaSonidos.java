package Fabricas;

import Juego.ReproductorSonido;

public class FabricaSonidos {

	protected ReproductorSonido reproductorSonido;

	public FabricaSonidos() {
		reproductorSonido = new ReproductorSonido();
		this.cargarSonidos();
	}

	public void cargarSonidos() {

		reproductorSonido.cargarSonido("musicafondo", "/Sonidos/MusicaFondo.wav");
		reproductorSonido.cargarSonido("menu", "/Sonidos/Inicio.wav");
		reproductorSonido.cargarSonido("bolafuego", "/Sonidos/BoladeFuego.wav");
		reproductorSonido.cargarSonido("moneda", "/Sonidos/Moneda.wav");
		reproductorSonido.cargarSonido("salto", "/Sonidos/Salto.wav");
		reproductorSonido.cargarSonido("powerup", "/Sonidos/Powerup.wav");
		reproductorSonido.cargarSonido("muerte", "/Sonidos/MuerteMario.wav");
		reproductorSonido.cargarSonido("bump", "/Sonidos/Bump.wav");
		reproductorSonido.cargarSonido("ladrillo", "/Sonidos/LadrilloRoto.wav");
		reproductorSonido.cargarSonido("terminado", "/Sonidos/StageClear.wav");
		reproductorSonido.ajustarVolumenGeneral(-10);
	}

	public void reproducir(String nombre) {
		reproductorSonido.reproducir(nombre);

	}

	public void detener(String nombre) {
		reproductorSonido.detener(nombre);

	}

	public void reproducirEnBucle(String nombre) {
		reproductorSonido.reproducirEnBucle(nombre);

	}

}
