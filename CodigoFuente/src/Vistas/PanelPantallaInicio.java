package Vistas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import Juego.Juego;

@SuppressWarnings("serial")
public class PanelPantallaInicio extends JPanel {

	protected JButton botonIniciarJuegoModoOriginal;
	protected JButton botonIniciarJuegoModoSecundario;
	protected JButton botonVerRanking;
	protected JLabel imagenFondo;
	protected JTextField textFieldNombre;
	protected ControladorVistas controladorVistas;
	protected Juego juego;

	public PanelPantallaInicio(ControladorVistas controladorVistas) {
		this.controladorVistas = controladorVistas;
		this.setSize(ConstantesVentana.PANEL_PRINCIPAL_ANCHO, ConstantesVentana.PANEL_PRINCIPAL_ALTO);
		this.setLayout(null);

		agregarTextFieldNombre();
		agregarBotonIniciarJuegoModoOriginal();
		agregarBotonIniciarJuegoModoSecundario();
		agregarBotonVerRanking();
		agregarImagenFondo();
	}

	protected void agregarImagenFondo() {
		imagenFondo = new JLabel();
		ImageIcon icono_imagen = new ImageIcon(this.getClass().getResource("/Imagenes/fondoInicio.png"));
		Image imagen_escalada = icono_imagen.getImage().getScaledInstance(ConstantesVentana.PANEL_PRINCIPAL_ANCHO,
				ConstantesVentana.PANEL_PRINCIPAL_ALTO, Image.SCALE_SMOOTH);
		Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
		imagenFondo.setIcon(icono_imagen_escalado);
		imagenFondo.setBounds(0, 0, ConstantesVentana.PANEL_PRINCIPAL_ANCHO, ConstantesVentana.PANEL_PRINCIPAL_ALTO);
		add(imagenFondo);
	}

	private void agregarTextFieldNombre() {
		textFieldNombre = new JTextField("Ingrese su nombre:");
		textFieldNombre.setBounds(650, 25, 200, 50);
		textFieldNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNombre.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldNombre.setForeground(Color.BLACK);
		textFieldNombre.setBackground(Color.LIGHT_GRAY);
		textFieldNombre.setEditable(false);
		registrarOyenteTextFieldNombre();
		this.add(textFieldNombre);
	}

	private void agregarBotonVerRanking() {
		botonVerRanking = new JButton();
		configurarBotonVerRanking();
		registrarOyenteBotonVerRanking();
		this.add(botonVerRanking);
	}

	private void agregarBotonIniciarJuegoModoOriginal() {
		botonIniciarJuegoModoOriginal = new JButton();
		configurarBotonIniciarJuegoModoOriginal();
		registrarOyenteBotonIniciarJuegoModoOriginal();
		this.add(botonIniciarJuegoModoOriginal);
	}

	private void agregarBotonIniciarJuegoModoSecundario() {
		botonIniciarJuegoModoSecundario = new JButton();
		configurarBotonIniciarJuegoModoSecundario();
		registrarOyenteBotonIniciarJuegoModoSecundario();
		this.add(botonIniciarJuegoModoSecundario);
	}

	private void registrarOyenteBotonIniciarJuegoModoOriginal() {
		botonIniciarJuegoModoOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorVistas.mostrarPantallaJuegoModoOriginal();
				comprobarIngresoNombre();
				enviarNombreJugador();
			}
		});
	}

	private void registrarOyenteBotonIniciarJuegoModoSecundario() {
		botonIniciarJuegoModoSecundario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorVistas.mostrarPantallaJuegoModoSecundario();
				comprobarIngresoNombre();
				enviarNombreJugador();
			}
		});
	}

	private void registrarOyenteTextFieldNombre() {
		textFieldNombre.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				textFieldNombre.setEditable(true);
				textFieldNombre.setText("");
			}
		});
	}

	private void registrarOyenteBotonVerRanking() {
		botonVerRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorVistas.mostrarPantallaRanking();
			}
		});
	}

	private void configurarBotonIniciarJuegoModoOriginal() {
		botonIniciarJuegoModoOriginal.setBounds(332, 399, 454, 38);
		ocultarBoton(botonIniciarJuegoModoOriginal);
	}

	private void configurarBotonIniciarJuegoModoSecundario() {
		botonIniciarJuegoModoSecundario.setBounds(300, 439, 526, 38);
		ocultarBoton(botonIniciarJuegoModoSecundario);
	}

	private void configurarBotonVerRanking() {
		botonVerRanking.setBounds(464, 478, 200, 50);
		ocultarBoton(botonVerRanking);
	}

	private void ocultarBoton(JButton boton) {
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
	}

	public void enviarNombreJugador() {
		String nombre = textFieldNombre.getText();
		controladorVistas.guardarNombre(nombre);

	}

	private void comprobarIngresoNombre() {
		if (textFieldNombre.getText().equals("Ingrese su nombre:") || textFieldNombre.getText().equals("")) {
			textFieldNombre.setText("Anonimo");
		}
	}
}
