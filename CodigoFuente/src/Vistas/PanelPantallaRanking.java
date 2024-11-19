package Vistas;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Ranking.Ranking;

@SuppressWarnings("serial")
public class PanelPantallaRanking extends JPanel {

	protected JButton botonVolverInicio;
	protected ControladorVistas controladorVistas;
	protected Ranking ranking;
	protected JLabel imagenFondo;
	protected JLabel top1;
	protected JLabel top2;
	protected JLabel top3;
	protected JLabel top4;
	protected JLabel top5;

	public PanelPantallaRanking(ControladorVistas controladorVistas) {

		this.controladorVistas = controladorVistas;
		this.setSize(ConstantesVentana.PANEL_PRINCIPAL_ANCHO, ConstantesVentana.PANEL_PRINCIPAL_ALTO);
		this.setLayout(null);
		ranking = Ranking.getInstancia();
		cargarLabels();
		agregarImagenFondo();
		agregarBotonVolverInicio();

	}

	private void agregarBotonVolverInicio() {
		botonVolverInicio = new JButton();
		configurarBotonVolverInicio();
		registrarOyenteBotonVolverInicio();
		this.add(botonVolverInicio);

	}

	private void registrarOyenteBotonVolverInicio() {
		botonVolverInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorVistas.mostrarPantallaInicio();
			}

		});

	}

	protected void cargarLabels() {
		top1 = new JLabel();
		top2 = new JLabel();
		top3 = new JLabel();
		top4 = new JLabel();
		top5 = new JLabel();

		top1.setBounds(399, 170, 304, 45);
		top2.setBounds(399, 225, 304, 45);
		top3.setBounds(399, 280, 304, 45);
		top4.setBounds(399, 335, 304, 45);
		top5.setBounds(399, 390, 304, 45);

		top1.setText("");
		top2.setText("");
		top3.setText("");
		top4.setText("");
		top5.setText("");

		add(top1);
		add(top2);
		add(top3);
		add(top4);
		add(top5);

	}

	public void actualizarLabels() {
		if (ranking.getPrimerPuesto() != null) {
			top1.setText(ranking.getPrimerPuesto().toString());
		}
		if (ranking.getSegundoPuesto() != null) {
			top2.setText(ranking.getSegundoPuesto().toString());
		}
		if (ranking.getTercerPuesto() != null) {
			top3.setText(ranking.getTercerPuesto().toString());
		}
		if (ranking.getCuartoPuesto() != null) {
			top4.setText(ranking.getCuartoPuesto().toString());
		}
		if (ranking.getQuintoPuesto() != null) {
			top5.setText(ranking.getQuintoPuesto().toString());
		}
	}

	protected void agregarImagenFondo() {
		imagenFondo = new JLabel();
		ImageIcon icono_imagen = new ImageIcon(this.getClass().getResource("/Imagenes/fondoRanking.png"));
		Image imagen_escalada = icono_imagen.getImage().getScaledInstance(ConstantesVentana.PANEL_PRINCIPAL_ANCHO,
				ConstantesVentana.PANEL_PRINCIPAL_ALTO, Image.SCALE_SMOOTH);
		Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
		imagenFondo.setIcon(icono_imagen_escalado);
		imagenFondo.setBounds(0, 0, ConstantesVentana.PANEL_PRINCIPAL_ANCHO, ConstantesVentana.PANEL_PRINCIPAL_ALTO);
		add(imagenFondo);
	}

	private void configurarBotonVolverInicio() {
		botonVolverInicio.setBounds(422, 520, 281, 40);
		ocultarBoton(botonVolverInicio);
	}

	public Ranking getRanking() {
		return ranking;
	}

	private void ocultarBoton(JButton boton) {
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
	}

}
