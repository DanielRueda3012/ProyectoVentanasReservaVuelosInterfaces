package anadirVuelo;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaAnyadirVuelo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private static JTextField textFieldPrecio;
	private static JTextField textFieldHoraSalida;
	private static JTextField textFieldMinutosSalida;
	private static JTextField textFieldHoraLlegada;
	private static JTextField textFieldMinutosLlegada;
	private static JComboBox<String> comboBoxClase;
	private static JComboBox<String> comboBoxOrigen;
	private static ArrayList<String[]> listaVuelos;
	private JButton okButton;
	private JLabel labelPrecio;
	private JLabel labelOrigen;
	private JLabel labelSalida;
	private JLabel labelLlegada;
	private JLabel labelClase;
	private JButton cancelButton;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnyadirVuelo window = new VentanaAnyadirVuelo();
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
	public VentanaAnyadirVuelo() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {

		String rutaArchivo = "listaVuelos.txt";

		listaVuelos = leerArchivoTexto(rutaArchivo);

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		labelOrigen = new JLabel("Origin");
		labelOrigen.setFont(new Font("Gadugi", Font.BOLD | Font.ITALIC, 13));
		labelOrigen.setBounds(36, 24, 71, 34);
		frame.getContentPane().add(labelOrigen);

		labelSalida = new JLabel("Departure");
		labelSalida.setFont(new Font("Gadugi", Font.BOLD | Font.ITALIC, 13));
		labelSalida.setBounds(36, 67, 71, 34);
		frame.getContentPane().add(labelSalida);

		labelLlegada = new JLabel("Arrival");
		labelLlegada.setFont(new Font("Gadugi", Font.BOLD | Font.ITALIC, 13));
		labelLlegada.setBounds(36, 112, 71, 34);
		frame.getContentPane().add(labelLlegada);

		labelClase = new JLabel("Class");
		labelClase.setFont(new Font("Gadugi", Font.BOLD | Font.ITALIC, 13));
		labelClase.setBounds(36, 157, 71, 34);
		frame.getContentPane().add(labelClase);

		labelPrecio = new JLabel("Price");
		labelPrecio.setFont(new Font("Gadugi", Font.BOLD | Font.ITALIC, 13));
		labelPrecio.setBounds(36, 202, 71, 34);
		frame.getContentPane().add(labelPrecio);

		textFieldPrecio = new JTextField();
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(117, 209, 140, 20);
		textFieldPrecio.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!textFieldPrecio.getText().equals("") && comboBoxOrigen.getSelectedIndex() != -1
						&& comboBoxClase.getSelectedIndex() != -1 && Integer.parseInt(textFieldHoraSalida.getText()) <= 24
						&& Integer.parseInt(textFieldMinutosSalida.getText()) <= 60 && !textFieldHoraSalida.getText().equals("")
						&& !textFieldMinutosSalida.getText().equals("")) {
					okButton.setEnabled(true);
				}
			}
		});
		frame.getContentPane().add(textFieldPrecio);

		okButton = new JButton("OK");
		okButton.setEnabled(false);
		okButton.setBackground(new Color(128, 255, 128));
		okButton.setForeground(new Color(0, 0, 0));
		okButton.setBounds(267, 227, 63, 23);
		frame.getContentPane().add(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setForeground(new Color(255, 255, 255));
		cancelButton.setBackground(new Color(128, 0, 0));
		cancelButton.setBounds(340, 227, 84, 23);
		botonPulsado(cancelButton);
		frame.getContentPane().add(cancelButton);

		comboBoxOrigen = new JComboBox<String>();
		comboBoxOrigen.setModel(new DefaultComboBoxModel<String>(new String[] { "Barcelona", "Bilbao", "Valencia" }));
		comboBoxOrigen.setBounds(117, 30, 140, 22);
		comboBoxOrigen.setSelectedIndex(-1);
		comboBoxOrigen.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!textFieldPrecio.getText().equals("") && comboBoxOrigen.getSelectedIndex() != -1
						&& comboBoxClase.getSelectedIndex() != -1 && Integer.parseInt(textFieldHoraSalida.getText()) <= 24
						&& Integer.parseInt(textFieldMinutosSalida.getText()) <= 60 && !textFieldHoraSalida.getText().equals("")
						&& !textFieldMinutosSalida.getText().equals("")) {
					okButton.setEnabled(true);
				}
			}
		});
		frame.getContentPane().add(comboBoxOrigen);

		comboBoxClase = new JComboBox<String>();
		comboBoxClase.setModel(new DefaultComboBoxModel<String>(new String[] { "Business", "Tourist" }));
		comboBoxClase.setSelectedIndex(-1);
		comboBoxClase.setBounds(117, 163, 140, 22);
		comboBoxClase.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!textFieldPrecio.getText().equals("") && comboBoxOrigen.getSelectedIndex() != -1
						&& comboBoxClase.getSelectedIndex() != -1 && Integer.parseInt(textFieldHoraSalida.getText()) <= 24
						&& Integer.parseInt(textFieldMinutosSalida.getText()) <= 60 && !textFieldHoraSalida.getText().equals("")
						&& !textFieldMinutosSalida.getText().equals("")) {
					okButton.setEnabled(true);
				}
			}
		});
		frame.getContentPane().add(comboBoxClase);

		textFieldHoraSalida = new JTextField();
		textFieldHoraSalida.setBounds(117, 74, 39, 20);
		textFieldHoraSalida.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!textFieldMinutosSalida.getText().equals("")) {
					calcularLlegada();
					if (!textFieldPrecio.getText().equals("") && comboBoxOrigen.getSelectedIndex() != -1
							&& comboBoxClase.getSelectedIndex() != -1 && Integer.parseInt(textFieldHoraSalida.getText()) <= 24
							&& Integer.parseInt(textFieldMinutosSalida.getText()) <= 60 && !textFieldHoraSalida.getText().equals("")
							&& !textFieldMinutosSalida.getText().equals("")) {
						okButton.setEnabled(true);
					}
				}
			}
		});
		frame.getContentPane().add(textFieldHoraSalida);
		textFieldHoraSalida.setColumns(2);

		textFieldMinutosSalida = new JTextField();
		textFieldMinutosSalida.setColumns(2);
		textFieldMinutosSalida.setBounds(176, 74, 39, 20);
		textFieldMinutosSalida.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calcularLlegada();
				if (!textFieldPrecio.getText().equals("") && comboBoxOrigen.getSelectedIndex() != -1
						&& comboBoxClase.getSelectedIndex() != -1 && !textFieldHoraSalida.getText().equals("")
						&& Integer.parseInt(textFieldHoraSalida.getText()) <= 24
						&& Integer.parseInt(textFieldMinutosSalida.getText()) <= 60 && !textFieldMinutosSalida.getText().equals("")) {
					okButton.setEnabled(true);
				}
			}
		});
		frame.getContentPane().add(textFieldMinutosSalida);

		textFieldHoraLlegada = new JTextField();
		textFieldHoraLlegada.setEditable(false);
		textFieldHoraLlegada.setColumns(10);
		textFieldHoraLlegada.setBounds(117, 119, 39, 20);
		frame.getContentPane().add(textFieldHoraLlegada);

		textFieldMinutosLlegada = new JTextField();
		textFieldMinutosLlegada.setEditable(false);
		textFieldMinutosLlegada.setColumns(10);
		textFieldMinutosLlegada.setBounds(176, 119, 39, 20);
		frame.getContentPane().add(textFieldMinutosLlegada);

		JLabel labelDosPuntos1 = new JLabel(":");
		labelDosPuntos1.setBounds(166, 122, 16, 14);
		frame.getContentPane().add(labelDosPuntos1);

		JLabel labelDosPuntos2 = new JLabel(":");
		labelDosPuntos2.setBounds(166, 77, 16, 14);
		frame.getContentPane().add(labelDosPuntos2);
		botonPulsado(okButton);
		
	}

	private void calcularLlegada() {
		try {
			// Obtener la hora y minutos de salida
			int intHoraSalida = Integer.parseInt(textFieldHoraSalida.getText());
			int intMinutoSalida = Integer.parseInt(textFieldMinutosSalida.getText());
			int intHoraLlegada = 0;
			int intMinutoLlegada = 0;

			if (comboBoxOrigen.getSelectedIndex() == 0) {
				intHoraLlegada = intHoraSalida + 1;
				intMinutoLlegada = intMinutoSalida + 25;
			} else if (comboBoxOrigen.getSelectedIndex() == 1 || comboBoxOrigen.getSelectedIndex() == 2) {
				intHoraLlegada = intHoraSalida + 1;
				intMinutoLlegada = intMinutoSalida + 5;
			}

			if (intMinutoLlegada >= 60) {
				intMinutoLlegada -= 60;
				intHoraLlegada += 1;
			}

			if (intHoraLlegada >= 24) {
				intHoraLlegada -= 24;
			}
			if (intHoraLlegada < 10) {
				textFieldHoraLlegada.setText("0" + String.valueOf(intHoraLlegada));
			} else {
				textFieldHoraLlegada.setText(String.valueOf(intHoraLlegada));
			}

			if (intMinutoLlegada < 10) {
				textFieldMinutosLlegada.setText("0" + String.valueOf(intMinutoLlegada));
			} else {
				textFieldMinutosLlegada.setText(String.valueOf(intMinutoLlegada));
			}

		} catch (NumberFormatException ex) {
			System.out.println("Error al calcular la hora de llegada: " + ex.getMessage());
		}
	}

	private static void anyadirVueloNuevo() {
	    String[] vueloNuevo = new String[5];

	    vueloNuevo[0] = comboBoxOrigen.getSelectedItem().toString();
	    vueloNuevo[1] = textFieldHoraSalida.getText() + ":" + textFieldMinutosSalida.getText();
	    vueloNuevo[2] = textFieldHoraLlegada.getText() + ":" + textFieldMinutosLlegada.getText();
	    vueloNuevo[3] = comboBoxClase.getSelectedItem().toString();
	    vueloNuevo[4] = textFieldPrecio.getText();

	    listaVuelos.add(vueloNuevo);

	    String rutaArchivo = "src/listaVuelos.txt";

	    // Cargar vuelos existentes desde el archivo
	    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String[] vueloExistente = linea.split(",");
	            listaVuelos.add(vueloExistente);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Escribir todos los vuelos (incluyendo el nuevo) al archivo
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
	        for (String[] strings : listaVuelos) {
	            String linea = strings[0] + "," + strings[1] + "," + strings[2] + "," + strings[3] + "," + strings[4];
	            bw.write(linea);
	            bw.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public static ArrayList<String[]> leerArchivoTexto(String rutaArchivo) {
		ArrayList<String[]> listaDatos = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				// Dividir la línea por comas y agregar el array resultante a la lista
				String[] datos = linea.split(",");
				listaDatos.add(datos);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listaDatos;
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	private void botonPulsado(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (b == okButton) {
					anyadirVueloNuevo();
					frame.setVisible(false);
				} else if (b == cancelButton) {
					frame.setVisible(false);
				}
			}
		});
	}
}