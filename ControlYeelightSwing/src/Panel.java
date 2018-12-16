import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
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
		
		JSlider sliderIntensidad = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		
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
		panel_1.setBackground(new Color(0, 0, 0));
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JButton btnColor = new JButton("");
		btnColor.setForeground(Color.BLACK);
		btnColor.setSelectedIcon(new ImageIcon("/home/nacho/Escritorio/bombillaka.png"));
		btnColor.setIcon(new ImageIcon("/home/nacho/Escritorio/bombillaka.png"));
		btnColor.setBackground(Color.BLACK);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(237)
					.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(258, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(67)
					.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(90, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		btnColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color colorSeleccionado = JColorChooser.showDialog(new JColorChooser(), "Elege color", Color.RED);
				
				btnColor.setBackground(colorSeleccionado);
				
				try {

					device.setRGB(colorSeleccionado.getRed(), colorSeleccionado.getGreen(), colorSeleccionado.getBlue());

				} catch (Exception e) {

					btnConectar.setBackground(Color.red);
				}
				
			}
		});


	}

}
