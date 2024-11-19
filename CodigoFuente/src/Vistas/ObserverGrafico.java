package Vistas;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Entidades.EntidadLogica;

@SuppressWarnings("serial")
public abstract class ObserverGrafico extends JLabel implements Observer {

	protected EntidadLogica entidadObservada;
	protected int ultimoSprite;
	private ImageIcon icono;

	public ObserverGrafico(EntidadLogica entidadObservada) {
		super();
		this.entidadObservada = entidadObservada;
		actualizarImagen();
	}

	public void actualizar() {
		actualizarPosicionYTamano();
	}

	public void actualizarImagen() {
		if (icono == null || !entidadObservada.getSprite().getRutaImagen().equals(icono.getDescription())) {
			icono = new ImageIcon(this.getClass().getResource(entidadObservada.getSprite().getRutaImagen()));
			icono.setDescription(entidadObservada.getSprite().getRutaImagen());
			Image scaledImage = icono.getImage().getScaledInstance(entidadObservada.getAncho(),
					entidadObservada.getAlto(), Image.SCALE_DEFAULT);
			setIcon(new ImageIcon(scaledImage));
			actualizarPosicionYTamano();
		}
	}

	public void actualizarPosicionYTamano() {
		int x = entidadObservada.getX();
		int y = entidadObservada.getY();
		int ancho = entidadObservada.getAncho();
		int alto = entidadObservada.getAlto();
		setBounds(x, y, ancho, alto);
	}

}