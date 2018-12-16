import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mollin.yapi.YeelightDevice;

import javax.swing.JEditorPane;

import java.awt.Color;

public class Panel {

	public JFrame frame;
	public Color colorPrincipal = null;
	public YeelightDevice device = null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel window = new Panel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Panel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 778, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * Panel de Ip bombilla
		 */
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));

		JTextPane txtIpBombilla = new JTextPane();
		txtIpBombilla.setEditable(false);
		txtIpBombilla.setText("IP Bombilla");
		panel_2.add(txtIpBombilla);

		JEditorPane ipAdd = new JEditorPane();
		panel_2.add(ipAdd);
		ipAdd.setText("192.168.100.44");

		JButton btnConectar = new JButton("Conectar");
		btnConectar.setBackground(new Color(255, 0, 0));
		panel_2.add(btnConectar);

		btnConectar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Instantiate your device (with its IP)
					device = new YeelightDevice(ipAdd.getText());
					btnConectar.setBackground(Color.green);

				} catch (Exception e1) {
					System.out.println(ipAdd.getText());
					btnConectar.setBackground(Color.RED);
				}
			}
		});

		/**
		 * Panel de encendido apagado
		 */
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		// Encender
		JButton btnOn = new JButton("ON");
		btnOn.setBackground(new Color(0, 255, 0));
		panel.add(btnOn);
		btnOn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					device.setPower(true);
				} catch (Exception k) {
					btnConectar.setBackground(Color.red);
				}
			}
		});

		// Apagar
		JButton btnOff = new JButton("OFF");
		btnOff.setBackground(new Color(255, 0, 0));
		panel.add(btnOff);

		btnOff.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					device.setPower(false);
				} catch (Exception k) {
					btnConectar.setBackground(Color.red);
				}
			}
		});

		/**
		 * Intensidad brillo
		 */

		JSlider sliderIntensidad = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);

		sliderIntensidad.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						if (!sliderIntensidad.getValueIsAdjusting()) {
						try {
							device.setBrightness(sliderIntensidad.getValue());
							//Intensidad = sliderIntensidad.getValue();
						} catch (Exception k) {
							System.out.println(sliderIntensidad.getValue());
							//Intensidad = sliderIntensidad.getValue();
						}

					}}
				}).start();

			}
		});
		frame.getContentPane().add(sliderIntensidad, BorderLayout.SOUTH);

		
		/**
		 * Panel Colores
		 */

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);

		JColorChooser PaletaColor = new JColorChooser();

		PaletaColor.setForeground(Color.BLACK);

		PaletaColor.setBackground(Color.BLACK);
		panel_1.add(PaletaColor, "1, 1, right, bottom");
		
		JButton btnCambiar = new JButton("Cambiar Color");
		panel_1.add(btnCambiar);
		btnCambiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			Color colorSeleccionado = PaletaColor.getColor();
			try {

				device.setRGB(colorSeleccionado.getRed(), colorSeleccionado.getGreen(),
						colorSeleccionado.getBlue());

			} catch (Exception e) {

				btnConectar.setBackground(Color.red);
			}
			}
		});
	}
}

