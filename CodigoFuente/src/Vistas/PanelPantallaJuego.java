package Vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import Entidades.EntidadLogica;
import Entidades.EntidadLogicaJugador;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class PanelPantallaJuego extends JPanel {

	protected JPanel panelMapa;
	protected JPanel panelInformacion;
	protected JScrollPane panelScrollMapa;
	protected EntidadLogicaJugador entidadJugador;
	protected JLabel imagenFondoPanelMapa;
	protected JLabel imagenFondoPanelInformacion;
	protected ControladorVistas controladorVistas;
	protected JLabel labelPuntaje;
	private int posicionPreviaX = -1;
	protected String rutaAFondo;

	public PanelPantallaJuego(ControladorVistas controladorVistas) {

		setPreferredSize(new Dimension(ConstantesVentana.PANEL_JUEGO_ANCHO, ConstantesVentana.PANEL_JUEGO_ALTO));
		setLayout(new BorderLayout());
		agregarPanelInformacion();
		agregarPanelMapaConFondoYScroll();
		this.controladorVistas = controladorVistas;
		rutaAFondo = "";
	}

	public Observer incorporarEntidadJugador(EntidadLogicaJugador entidadJugador) {
		this.entidadJugador = entidadJugador;
		ObserverJugador observerJugador = new ObserverJugador(entidadJugador, this);
		imagenFondoPanelMapa.add(observerJugador);
		actualizarInfoJugador(entidadJugador);
		return observerJugador;
	}

	public Observer incorporarEntidad(EntidadLogica entidadLogica) {
		ObserverEntidad observerEntidad = new ObserverEntidad(entidadLogica, this);
		imagenFondoPanelMapa.add(observerEntidad);
		return observerEntidad;
	}

	public void eliminarEntidad(Observer observer) {
		imagenFondoPanelMapa.remove((ObserverGrafico) observer);
		controladorVistas.refrescar();
	}

	public Observer incorporarSilueta(EntidadLogica entidad_logica) {
		ObserverEntidad observerEntidad = new ObserverEntidad(entidad_logica, this);
		ImageIcon icono = new ImageIcon(this.getClass().getResource("/Imagenes/fondoMapa.png"));
		imagenFondoPanelMapa.setIcon(icono);
		imagenFondoPanelMapa.setBounds(0, 0, imagenFondoPanelMapa.getIcon().getIconWidth(),
				imagenFondoPanelMapa.getIcon().getIconHeight());
		imagenFondoPanelMapa.setPreferredSize(new Dimension(imagenFondoPanelMapa.getIcon().getIconWidth(),
				imagenFondoPanelMapa.getIcon().getIconHeight()));
		return observerEntidad;
	}

	protected void actualizarInfoJugador(EntidadLogicaJugador entidadJugador) {
		actualizarLabelsInformacion(entidadJugador);
		actualizarScrollHaciaJugador(entidadJugador);
	}

	protected void actualizarLabelsInformacion(EntidadLogicaJugador entidadJugador) {

		labelPuntaje.setText("Puntaje: " + String.valueOf(entidadJugador.getPuntaje()) + " Vidas: "
				+ String.valueOf(entidadJugador.getVidas()));
	}

	protected void actualizarScrollHaciaJugador(EntidadLogicaJugador entidadJugador) {

		int posicionActualX = entidadJugador.getX();

		if (posicionActualX != posicionPreviaX) {
			int nuevaPosicionScroll = panelScrollMapa.getHorizontalScrollBar().getValue();

			if (posicionActualX < 256) {
				nuevaPosicionScroll = 0;
			} else {

				if (posicionActualX > posicionPreviaX) {
					nuevaPosicionScroll += entidadJugador.getVelocidad();
				}

				else {
					nuevaPosicionScroll -= entidadJugador.getVelocidad();
				}
			}

			nuevaPosicionScroll = Math.max(nuevaPosicionScroll, 0);
			nuevaPosicionScroll = Math.min(nuevaPosicionScroll, panelScrollMapa.getHorizontalScrollBar().getMaximum());

			panelScrollMapa.getHorizontalScrollBar().setValue(nuevaPosicionScroll);
		}

		posicionPreviaX = posicionActualX;
	}

	protected void agregarPanelMapaConFondoYScroll() {
		imagenFondoPanelMapa = new JLabel();
		imagenFondoPanelMapa.setLayout(null);
		imagenFondoPanelMapa.setBounds(0, 0, ConstantesVentana.PANEL_MAPA_ANCHO, ConstantesVentana.PANEL_MAPA_ALTO);

		ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/Imagenes/fondoMapa.png"));

		imagenFondoPanelMapa.setIcon(iconoImagen);

		panelMapa = new JPanel(null);
		panelMapa
				.setPreferredSize(new Dimension(ConstantesVentana.PANEL_MAPA_ANCHO, ConstantesVentana.PANEL_MAPA_ALTO));
		panelMapa.add(imagenFondoPanelMapa);

		panelScrollMapa = new JScrollPane(panelMapa);
		panelScrollMapa.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelScrollMapa.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		panelScrollMapa.setBounds(0, 0, ConstantesVentana.PANEL_MAPA_ANCHO, ConstantesVentana.PANEL_MAPA_ALTO);

		add(panelScrollMapa, BorderLayout.CENTER);
		panelScrollMapa.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	}

	protected void agregarPanelInformacion() {
		panelInformacion = new JPanel();
		panelInformacion.setBackground(new Color(0, 128, 192));
		panelInformacion.setLayout(null);
		panelInformacion.setPreferredSize(
				new Dimension(ConstantesVentana.PANEL_INFORMACION_ANCHO, ConstantesVentana.PANEL_INFORMACION_ALTO));

		agregarImagenFondoPanelInformacion();
		agregarLabelsEditablesPanelInformacion();
		add(panelInformacion, BorderLayout.NORTH);
	}

	protected void agregarImagenFondoPanelInformacion() {
		imagenFondoPanelInformacion = new JLabel();
		ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/Imagenes/Generales/Puntaje.png"));
		Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVentana.PANEL_INFORMACION_ANCHO,
				ConstantesVentana.PANEL_INFORMACION_ALTO, Image.SCALE_SMOOTH);
		Icon iconoImagenEscalada = new ImageIcon(imagenEscalada);
		imagenFondoPanelInformacion.setIcon(iconoImagenEscalada);
		imagenFondoPanelInformacion.setBounds(0, 0, ConstantesVentana.PANEL_INFORMACION_ANCHO,
				ConstantesVentana.PANEL_INFORMACION_ALTO);
	}

	protected void agregarLabelsEditablesPanelInformacion() {
		labelPuntaje = new JLabel("Puntaje: ");
		labelPuntaje.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		labelPuntaje.setBounds(22, 11, 490, 48);
		decorarLabelsInformacion();
		panelInformacion.add(labelPuntaje);
	}

	protected void decorarLabelsInformacion() {
		// TO-DO
	}

	public void setFondoJuego(String ruta) {
		rutaAFondo = ruta;
		ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource(ruta));
		imagenFondoPanelMapa.setIcon(iconoImagen);
	}

	public String getRutaFondo() {
		return rutaAFondo;
	}
}
