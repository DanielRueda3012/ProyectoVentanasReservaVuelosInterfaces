package operaciones;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import anadirVuelo.VentanaAnyadirVuelo;
import dialogo.VentanaDialogo;
import login.VentanaLogin;

public class VentanaOperaciones extends JFrame implements ActionListener, WindowListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPaneVentanaPrincipal;
    private JTextField textFieldNumHuespedes;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private static VentanaOperaciones frame;
    JButton ButtonAceptar;
    JButton ButtonCancelar;
    JButton btnSeleccionar;
    JButton btnAnadirVuelo;
    JCheckBox CheckBoxReservarVuelo;
    JCheckBox CheckBoxHabitacion;
    JRadioButton RadioButtonIndividual;
    JRadioButton RadioButtonDoble;
    JRadioButton RadioButtonMultiple;
    public static JComboBox<String> comboBoxOrigen;
    String strTipoReservaVuelo, strOrigen;
    VentanaDialogo vd;
    JLabel lblHoraSalida, lblHoraLlegada;
    public static JTextField textField;
    public static JTextField textField_1;
    private JTextField textFldLineaMensaje;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new VentanaOperaciones();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaOperaciones() {
        setBounds(100, 100, 800, 500);
        contentPaneVentanaPrincipal = new JPanel();
        contentPaneVentanaPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPaneVentanaPrincipal);
        contentPaneVentanaPrincipal.setLayout(null);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(this);
        inicializarControles();
        habilitarBotonAnadirVuelo();
        establecerIdiomaIngles();
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (CheckBoxReservarVuelo.isSelected() && comboBoxOrigen.getSelectedIndex() != -1) {
                    // Lógica para recuperar y mostrar vuelos
                	System.out.println("prueba");
                	VentanaDialogo frameVD = new VentanaDialogo();
                	
                	VentanaDialogo.textField.setText(VentanaOperaciones.comboBoxOrigen.getSelectedItem().toString());
                	frameVD.setVisible(true);
                	
                } else {
                    // Si no se ha seleccionado origen o reserva de vuelo
                    JOptionPane.showMessageDialog(VentanaOperaciones.this, "Por favor, selecciona origen y marca Reservar Vuelo.");
                }
            }
        });
    }
    

    private void inicializarControles() {
        btnAnadirVuelo = new JButton("Añadir Vuelo");
        btnAnadirVuelo.setForeground(Color.BLACK);
        btnAnadirVuelo.setBackground(Color.PINK);
        btnAnadirVuelo.setFont(new Font("Kristen ITC", Font.PLAIN, 16));
        btnAnadirVuelo.setEnabled(false);
        btnAnadirVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAnadirVuelo();
            }
        });

        btnAnadirVuelo.setBounds(400, 207, 152, 21);
        contentPaneVentanaPrincipal.add(btnAnadirVuelo);

        ButtonAceptar = new JButton("Aceptar");
        ButtonAceptar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 11));
        ButtonAceptar.setEnabled(false);
        ButtonAceptar.setBackground(new Color(159, 255, 207));
        ButtonAceptar.setBounds(193, 207, 85, 21);
        ventanaMensajeFinal(ButtonAceptar);
        contentPaneVentanaPrincipal.add(ButtonAceptar);

        ButtonCancelar = new JButton("Cancelar");
        ButtonCancelar.setFont(new Font("DejaVu Sans Mono", Font.BOLD | Font.ITALIC, 11));
        ButtonCancelar.setBackground(new Color(255, 168, 168));
        ButtonCancelar.setBounds(294, 207, 96, 21);
        ventanaMensajeFinal(ButtonCancelar);
        contentPaneVentanaPrincipal.add(ButtonCancelar);

        CheckBoxReservarVuelo = new JCheckBox("Reservar Vuelo");
        CheckBoxReservarVuelo.setFont(new Font("Lucida Sans Typewriter", Font.BOLD | Font.ITALIC, 11));
        CheckBoxReservarVuelo.setBounds(53, 22, 134, 43);
        CheckBoxReservarVuelo.addActionListener(this);
        contentPaneVentanaPrincipal.add(CheckBoxReservarVuelo);

        CheckBoxHabitacion = new JCheckBox("Reservar Habitación");
        CheckBoxHabitacion.setFont(new Font("Lucida Sans Typewriter", Font.BOLD | Font.ITALIC, 11));
        CheckBoxHabitacion.setBounds(242, 22, 165, 43);
        CheckBoxHabitacion.addActionListener(this);
        contentPaneVentanaPrincipal.add(CheckBoxHabitacion);

        RadioButtonIndividual = new JRadioButton("Individual");
        RadioButtonIndividual.setFont(new Font("Gadugi", Font.BOLD | Font.ITALIC, 11));
        RadioButtonIndividual.setEnabled(false);
        buttonGroup.add(RadioButtonIndividual);
        desactivarCampoTexto(RadioButtonIndividual);
        RadioButtonIndividual.setBounds(252, 65, 94, 21);
        contentPaneVentanaPrincipal.add(RadioButtonIndividual);

        RadioButtonDoble = new JRadioButton("Doble");
        RadioButtonDoble.setFont(new Font("Gadugi", Font.BOLD | Font.ITALIC, 11));
        RadioButtonDoble.setEnabled(false);
        RadioButtonDoble.setSelected(true);
        desactivarCampoTexto(RadioButtonDoble);
        buttonGroup.add(RadioButtonDoble);
        RadioButtonDoble.setBounds(252, 87, 94, 21);
        contentPaneVentanaPrincipal.add(RadioButtonDoble);

        RadioButtonMultiple = new JRadioButton("Multiple");
        RadioButtonMultiple.setFont(new Font("Gadugi", Font.BOLD | Font.ITALIC, 11));
        RadioButtonMultiple.setEnabled(false);
        activarCampoTexto(RadioButtonMultiple);
        buttonGroup.add(RadioButtonMultiple);
        RadioButtonMultiple.setBounds(252, 109, 71, 21);
        contentPaneVentanaPrincipal.add(RadioButtonMultiple);

        textFieldNumHuespedes = new JTextField();
        textFieldNumHuespedes.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (textFieldNumHuespedes == e.getSource()) {
                    int key = e.getKeyChar();
                    System.out.print(key);
                    boolean esNumero = key >= 48 && key <= 57;
                    if (!esNumero) {
                        e.consume();
                    }
                }
            }
        });
        textFieldNumHuespedes.setEnabled(false);
        textFieldNumHuespedes.setBounds(331, 113, 48, 19);
        contentPaneVentanaPrincipal.add(textFieldNumHuespedes);
        textFieldNumHuespedes.setColumns(10);

        comboBoxOrigen = new JComboBox<String>();
        comboBoxOrigen.setEnabled(false);
        comboBoxOrigen.setFont(new Font("Tahoma", Font.PLAIN, 12));
        comboBoxOrigen.setModel(new DefaultComboBoxModel<String>(new String[] { "Barcelona", "Bilbao", "Valencia" }));
        comboBoxOrigen.setSelectedIndex(-1);
        comboBoxOrigen.setBounds(53, 64, 94, 22);
        contentPaneVentanaPrincipal.add(comboBoxOrigen);

        comboBoxOrigen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                textField_1.setText("");
            }
        });

        btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 11));
        btnSeleccionar.setBounds(54, 206, 112, 23);
        contentPaneVentanaPrincipal.add(btnSeleccionar);
        ventanaMensajeFinal(btnSeleccionar);

        textField = new JTextField();
        textField.setEnabled(false);
        textField.setColumns(10);
        textField.setBounds(129, 113, 62, 19);
        contentPaneVentanaPrincipal.add(textField);

        textField_1 = new JTextField();
        textField_1.setEnabled(false);
        textField_1.setColumns(10);
        textField_1.setBounds(129, 151, 62, 19);
        contentPaneVentanaPrincipal.add(textField_1);

        lblHoraSalida = new JLabel("Hora salida");
        lblHoraSalida.setFont(new Font("Gadugi", Font.BOLD | Font.ITALIC, 11));
        lblHoraSalida.setBounds(10, 116, 119, 14);
        contentPaneVentanaPrincipal.add(lblHoraSalida);

        lblHoraLlegada = new JLabel("Hora llegada");
        lblHoraLlegada.setFont(new Font("Gadugi", Font.BOLD | Font.ITALIC, 11));
        lblHoraLlegada.setBounds(10, 154, 114, 14);
        contentPaneVentanaPrincipal.add(lblHoraLlegada);

        textFldLineaMensaje = new JTextField();
        textFldLineaMensaje.setBorder(null);
        textFldLineaMensaje.setEnabled(false);
        textFldLineaMensaje.setColumns(10);
        textFldLineaMensaje.setBounds(84, 233, 323, 14);
        contentPaneVentanaPrincipal.add(textFldLineaMensaje);
    }

    public void habilitarBotonAnadirVuelo() {
        JFormattedTextField usuarioTextField = VentanaLogin.getUsuarioTextField();

        if (usuarioTextField != null && "22.222.222-J".equals(usuarioTextField.getText())) {
            btnAnadirVuelo.setEnabled(true);
        } else {
            btnAnadirVuelo.setEnabled(false);
        }
    }

    private void activarCampoTexto(JRadioButton b) {
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (b.isSelected()) {
                    textFieldNumHuespedes.setEnabled(true);
                }
            }
        });
    }

    private void desactivarCampoTexto(JRadioButton b) {
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (b.isSelected()) {
                    textFieldNumHuespedes.setEnabled(false);
                }
            }
        });
    }

    private void ventanaMensajeFinal(JButton b) {
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (b == ButtonCancelar) {
                    frame.setVisible(false);
                    System.exit(0);
                } else if (CheckBoxReservarVuelo.isSelected() && !comboBoxOrigen.getSelectedItem().toString().equals("")) {
                    StringBuilder mensaje = new StringBuilder("Has reservado ");

                    if (CheckBoxHabitacion.isSelected()) {
                        mensaje.append("una habitación para ").append(textFieldNumHuespedes.getText())
                                .append(" personas.");

                        // Agregar mensaje específico para habitación individual o doble
                        if (RadioButtonIndividual.isSelected()) {
                            mensaje.append(" (individual).");
                        } else if (RadioButtonDoble.isSelected()) {
                            mensaje.append(" (doble).");
                        }
                    }

                    if (CheckBoxReservarVuelo.isSelected()) {
                        if (CheckBoxHabitacion.isSelected()) {
                            mensaje.append(" y ");
                        }

                        mensaje.append("un vuelo desde ").append(comboBoxOrigen.getSelectedItem().toString())
                                .append(" a las ").append(textField.getText()) // Hora de salida
                                .append(".");
                    }

                    if (b != btnSeleccionar) {
                        textFldLineaMensaje.setText(mensaje.toString());
                        JOptionPane.showMessageDialog(VentanaOperaciones.this, mensaje.toString());
                    }

                }
            }
        });
    }

    public void establecerIdiomaIngles() {
        JFormattedTextField usuarioTextField = VentanaLogin.getUsuarioTextField();

        if (usuarioTextField != null && "22.222.222-J".equals(usuarioTextField.getText())) {
            btnAnadirVuelo.setText("Add Flight");
            ButtonAceptar.setText("Accept");
            ButtonCancelar.setText("Cancel");
            CheckBoxReservarVuelo.setText("Book Flight");
            CheckBoxHabitacion.setText("Book Room");

            RadioButtonIndividual.setText("Individual");
            RadioButtonDoble.setText("Double");
            RadioButtonMultiple.setText("Multiple");

            lblHoraSalida.setText("Departure Time");
            lblHoraLlegada.setText("Arrival Time");

            btnSeleccionar.setText("Select");

            // Update the window
            repaint();
            revalidate();
        }
    }

    private void abrirVentanaAnadirVuelo() {
        EventQueue.invokeLater(() -> {
            try {
                VentanaAnyadirVuelo ventanaAnadirVuelo = new VentanaAnyadirVuelo();
                ventanaAnadirVuelo.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public static VentanaOperaciones getInstance() {
        if (frame == null) {
            frame = new VentanaOperaciones();
        }
        return frame;
    }

    public static void actualizarHoras(String horaSalida, String horaLlegada) {
        textField.setText(horaSalida);
        textField_1.setText(horaLlegada);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (CheckBoxReservarVuelo == e.getSource()) {
            if (CheckBoxReservarVuelo.isSelected()) {
                comboBoxOrigen.setEnabled(true);
                ButtonAceptar.setEnabled(true);
                if (!CheckBoxHabitacion.isSelected() && !CheckBoxReservarVuelo.isSelected()) {
                    ButtonAceptar.setEnabled(false);
                }
            } else {
                comboBoxOrigen.setEnabled(false);
                strTipoReservaVuelo = "";
                if (!CheckBoxHabitacion.isSelected()) {
                    ButtonAceptar.setEnabled(false);
                }
            }
        }

        if (comboBoxOrigen == e.getSource()) {
            strOrigen = comboBoxOrigen.getSelectedItem().toString();
        }

        if (CheckBoxHabitacion == e.getSource()) {
            if (CheckBoxHabitacion.isSelected()) {
                RadioButtonDoble.setEnabled(true);
                RadioButtonIndividual.setEnabled(true);
                RadioButtonMultiple.setEnabled(true);
                ButtonAceptar.setEnabled(true);
                if (RadioButtonMultiple.isSelected()) {
                    textFieldNumHuespedes.setEnabled(true);
                }
            } else {
                RadioButtonDoble.setEnabled(false);
                RadioButtonIndividual.setEnabled(false);
                RadioButtonMultiple.setEnabled(false);
                textFieldNumHuespedes.setEnabled(false);
                if (!CheckBoxReservarVuelo.isSelected()) {
                    ButtonAceptar.setEnabled(false);
                }
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        String yes = "YES", no = "NO", cancelar = "CANCEL";
        Object opciones_SI_NO_CANCELAR[] = { yes, no, cancelar };
        int respuesta = JOptionPane.showOptionDialog(this, "Seguro que quieres cerrar la ventana?", getTitle(),
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones_SI_NO_CANCELAR, null);

        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
