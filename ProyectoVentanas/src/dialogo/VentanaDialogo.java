package dialogo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import operaciones.VentanaOperaciones;


public class VentanaDialogo extends JDialog {
	private static final long serialVersionUID = 1L;
	public final JPanel contentPanel = new JPanel();
	public static JTextField textField;
	public JTable table;
	public String horaSalida;
	public String horaLlegada;

	ArrayList<String[]> lista;
	private static VentanaDialogo instance;
	private static int numLineas;

	public VentanaDialogo() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Selecciona un vuelo desde:");
		lblNewLabel.setBounds(57, 10, 173, 33);
		contentPanel.add(lblNewLabel);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(228, 18, 134, 25);
		contentPanel.add(textField);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 78, 341, 133);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Origen", "Hora Salida", "Hora Llegada", "Clase", "Precio"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				realizarAccionOK();
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(cancelButton);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					realizarAccionOK();
				}
			}
		});
		lista = leerArchivoTexto("src/listaVuelos.txt");
		RellenarTabla(lista);
	}

	public void realizarAccionOK() {
	    int filaSeleccionada = table.getSelectedRow();
	    if (filaSeleccionada != -1) {
	        horaSalida = (String) table.getValueAt(filaSeleccionada, 1);
	        horaLlegada = (String) table.getValueAt(filaSeleccionada, 2);
	        VentanaOperaciones.textField.setText(horaSalida);
	        VentanaOperaciones.textField_1.setText(horaLlegada);
	        dispose();
	    } else {
	        JOptionPane.showMessageDialog(VentanaDialogo.this, "Por favor, selecciona un vuelo");
	    }
	}


	public static ArrayList<String[]> leerArchivoTexto(String rutaArchivo) {
		ArrayList<String[]> listaDatos = new ArrayList<>();
		numLineas = 0;
		
		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				numLineas++;
				String[] datos = linea.split(",");
				listaDatos.add(datos);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listaDatos;
	}

	public void RellenarTabla(ArrayList<String[]> lista) {

		int numFilas = numLineas;
		int numColumnas = 5;
		int iFilaListaVuelos = 0;
		
		for (int fila = 0; fila < numFilas; fila++) {
			if (lista.get(fila)[0].equals(VentanaOperaciones.comboBoxOrigen.getSelectedItem().toString())) {
				for (int columnas = 0; columnas < numColumnas; columnas++) {
					table.setValueAt(lista.get(fila)[columnas], iFilaListaVuelos, columnas);
					System.out.println(lista.get(fila)[columnas]);
				}
				iFilaListaVuelos++;
			}
		}
	}

	public static VentanaDialogo getInstance() {
		if (instance == null) {
			instance = new VentanaDialogo();
		}
		return instance;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public String getHoraLlegada() {
		return horaLlegada;
	}

	public static int getNumLineas() {
		return numLineas;
	}

	public static void setNumLineas(int numLineas) {
		VentanaDialogo.numLineas = numLineas;
	}
}
