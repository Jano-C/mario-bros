package Vistas;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelPantallaPerder extends JPanel {

	protected JButton botonVolverMenu;
	protected ControladorVistas controladorVistas;
	protected JLabel imagenFondo;

	public PanelPantallaPerder(ControladorVistas controladorVistas) {
		this.controladorVistas = controladorVistas;
		this.setSize(ConstantesVentana.PANEL_PRINCIPAL_ANCHO, ConstantesVentana.PANEL_PRINCIPAL_ALTO);
		this.setLayout(null);

		agregarImagenFondo();
		cargarBoton();
	}

	protected void cargarBoton() {

		botonVolverMenu = new JButton();
		botonVolverMenu.setText("");
		botonVolverMenu.setBounds(400, 450, 330, 45);
		setLayout(null);

		ocultarBoton(botonVolverMenu);
		this.add(botonVolverMenu);

		registrarOyenteBoton();
	}

	protected void agregarImagenFondo() {
		imagenFondo = new JLabel();
		ImageIcon icono_imagen = new ImageIcon(this.getClass().getResource("/Imagenes/fondoPerder.png"));
		Image imagen_escalada = icono_imagen.getImage().getScaledInstance(ConstantesVentana.PANEL_PRINCIPAL_ANCHO,
				ConstantesVentana.PANEL_PRINCIPAL_ALTO, Image.SCALE_SMOOTH);
		Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
		imagenFondo.setIcon(icono_imagen_escalado);
		imagenFondo.setBounds(0, 0, ConstantesVentana.PANEL_PRINCIPAL_ANCHO, ConstantesVentana.PANEL_PRINCIPAL_ALTO);
		add(imagenFondo);
	}

	protected void registrarOyenteBoton() {
		botonVolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorVistas.terminarJuego();
				controladorVistas.mostrarPantallaInicio();
			}
		});
	}

	private void ocultarBoton(JButton boton) {
		boton.setOpaque(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
	}
}
