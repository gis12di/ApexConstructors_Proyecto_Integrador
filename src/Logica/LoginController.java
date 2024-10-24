
import Logica.Autenticacion;
import Logica.Conexion;
        
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private JTextField userField;
    private JPasswordField passField;
    private Autenticacion autenticacion;
    private JFrame loginFrame;

    public LoginController(JTextField userField, JPasswordField passField, JFrame loginFrame) {
        this.userField = userField;
        this.passField = passField;
        this.loginFrame = loginFrame;
        this.autenticacion = new Autenticacion(); // Inicializar la clase de autenticación
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        verificarCredenciales();
    }

    private void verificarCredenciales() {
        String usuario = userField.getText().trim();
        String contrasena = new String(passField.getPassword()).trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(loginFrame, "Por favor, ingrese usuario y contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (autenticacion.verificarCredenciales(usuario, contrasena)) {
            String rol = autenticacion.obtenerRol(usuario, contrasena);
            if ("asesor".equalsIgnoreCase(rol)) {
                Main_Asesor mainAsesor = new Main_Asesor();
                mainAsesor.setTitle("Asesor");
                mainAsesor.setVisible(true);
                loginFrame.dispose(); // Cerrar la ventana de login
            } else if ("administrador".equalsIgnoreCase(rol)) {
                Main_Admin mainAdmin = new Main_Admin();
                mainAdmin.setTitle("Administrador");
                mainAdmin.setVisible(true);
                loginFrame.dispose(); // Cerrar la ventana de login
            }
        } else {
            JOptionPane.showMessageDialog(loginFrame, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
