import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mollin.yapi.YeelightDevice;
import com.mollin.yapi.exception.YeelightResultErrorException;
import com.mollin.yapi.exception.YeelightSocketException;

import javax.swing.JEditorPane;
import javax.swing.JSeparator;
import java.awt.Color;

public class Panel {

	public JFrame frame;

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
		frame.setBounds(100, 100, 582, 344);
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
					
				} catch (YeelightSocketException e1) {
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
		
		
		// Intensidad de brillo
		
		JSlider sliderIntensidad = new JSlider();
		
		sliderIntensidad.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					device.setBrightness(sliderIntensidad.getValue());
				} catch (Exception k) {
					btnConectar.setBackground(Color.red);
				}
				
			}
		});
		frame.getContentPane().add(sliderIntensidad, BorderLayout.SOUTH);
		
		
		/**
		 * Panel Colores
		 */
		
		ActionListener cambiarColor = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btnEvaluar = (JButton) e.getSource();
				
				try {

					device.setRGB(btnEvaluar.getBackground().getRed(),btnEvaluar.getBackground().getGreen(),btnEvaluar.getBackground().getBlue());

				} catch (Exception k) {

					btnConectar.setBackground(Color.red);
				}
				
			}
		};
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(255, 255, 255));
		panel_1.add(separator);
		
		JButton btnNegro = new JButton("");
		btnNegro.setBackground(Color.BLACK);
		panel_1.add(btnNegro);
		btnNegro.addActionListener(cambiarColor);
		
		JButton btnRojo = new JButton("");
		btnRojo.setBackground(Color.RED);
		panel_1.add(btnRojo);
		btnRojo.addActionListener(cambiarColor);
		
		JButton btnVerde = new JButton("");
		btnVerde.setBackground(Color.GREEN);
		panel_1.add(btnVerde);
		btnVerde.addActionListener(cambiarColor);
		
		JButton btnAzul = new JButton("");
		btnAzul.setBackground(new Color(30, 144, 255));
		panel_1.add(btnAzul);
		btnAzul.addActionListener(cambiarColor);
		
		JSeparator separator_1 = new JSeparator();
		panel_1.add(separator_1);
		
		JButton btnAmarillo = new JButton("");
		btnAmarillo.setBackground(Color.YELLOW);
		panel_1.add(btnAmarillo);
		btnAmarillo.addActionListener(cambiarColor);
		
		JButton btnBlanco = new JButton("");
		btnBlanco.setBackground(Color.WHITE);
		panel_1.add(btnBlanco);
		btnBlanco.addActionListener(cambiarColor);
		
		JButton btnRosa = new JButton("");
		btnRosa.setBackground(new Color(255, 20, 147));
		panel_1.add(btnRosa);
		btnRosa.addActionListener(cambiarColor);
		
		JButton btnLila = new JButton("");
		btnLila.setBackground(new Color(148, 0, 211));
		panel_1.add(btnLila);
		btnLila.addActionListener(cambiarColor);

	}

}
