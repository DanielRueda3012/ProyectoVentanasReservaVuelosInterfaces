package login;

import operaciones.VentanaOperaciones;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private static JFormattedTextField usuarioTextField;
    private JPasswordField contraseñaPasswordField;
    private JButton aceptarButton;
    private JButton salirButton;

    public VentanaLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Inicio de Sesión");
        setSize(400, 200);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240)); 
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new GridLayout(3, 2, 10, 10));

        // Configuración de la máscara para el campo de usuario (DNI)
        MaskFormatter formatter;
        try {
            formatter = new MaskFormatter("##.###.###-A");
            usuarioTextField = new JFormattedTextField(formatter);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        contraseñaPasswordField = new JPasswordField();
        aceptarButton = new JButton("Aceptar");
        aceptarButton.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 11));
        salirButton = new JButton("Salir");
        salirButton.setFont(new Font("Source Serif Pro", Font.ITALIC, 15));

        configurarLogica();

      
        usuarioTextField.setBackground(new Color(255, 255, 255)); 
        contraseñaPasswordField.setBackground(new Color(255, 255, 255)); 
        aceptarButton.setBackground(Color.MAGENTA);
        aceptarButton.setForeground(new Color(0, 0, 0)); 
        salirButton.setBackground(new Color(255, 0, 51)); 
        salirButton.setForeground(new Color(0, 0, 0));

        JLabel label = new JLabel("Usuario (DNI):");
        label.setFont(new Font("Arial Black", Font.BOLD, 11));
        contentPane.add(label);
        contentPane.add(usuarioTextField);
        JLabel label_1 = new JLabel("Contraseña:");
        label_1.setFont(new Font("Arial Black", Font.BOLD, 11));
        contentPane.add(label_1);
        contentPane.add(contraseñaPasswordField);
        contentPane.add(aceptarButton);
        contentPane.add(salirButton);

        setContentPane(contentPane);
    }

    private void configurarLogica() {
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioTextField.getText();
                char[] contraseña = contraseñaPasswordField.getPassword();
                String contraseñaStr = new String(contraseña);

                if (validarUsuario(usuario, contraseñaStr)) {
                    setVisible(false);
                    VentanaOperaciones ventanaOperaciones = new VentanaOperaciones();
                    ventanaOperaciones.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(VentanaLogin.this, "Usuario o contraseña incorrectos");
                    usuarioTextField.setValue(null);
                    contraseñaPasswordField.setText("");
                }
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private boolean validarUsuario(String usuario, String contraseña) {
        boolean esUsuarioValido = (usuario.equals("11.111.111.-H") && contraseña.equals("11.111.111.-H")) ||
                (usuario.equals("22.222.222-J") && contraseña.equals("22.222.222-J"));

        if (esUsuarioValido && usuario.equals("22.222.222-J")) {
            VentanaOperaciones ventanaOperaciones = VentanaOperaciones.getInstance();
            ventanaOperaciones.habilitarBotonAnadirVuelo();
        }

        return esUsuarioValido;
    }

    public static JFormattedTextField getUsuarioTextField() {
        return usuarioTextField;
    }

    public static void setUsuarioTextField(JFormattedTextField usuarioTextField) {
        VentanaLogin.usuarioTextField = usuarioTextField;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    VentanaLogin frame = new VentanaLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
