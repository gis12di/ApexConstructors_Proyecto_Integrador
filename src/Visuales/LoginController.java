import Logica.Usuarios.Autenticacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {//La clase LoginController controla la lógica del proceso de inicio de sesión
    private JTextField userField;
    private JPasswordField passField;
    private Autenticacion autenticacion;
    private JFrame loginFrame;

    public LoginController(JTextField userField, JPasswordField passField, JFrame loginFrame) {//Constructor que inicializa los campos y la clase de autenticación
        this.userField = userField;
        this.passField = passField;
        this.loginFrame = loginFrame;
        this.autenticacion = new Autenticacion(); // Inicializar la clase de autenticación
    }

    @Override
    public void actionPerformed(ActionEvent e) {//Método invocado cuando se produce una acción.
        verificarCredenciales();
    }

    private void verificarCredenciales() {//Método privado para verificar las credenciales del usuario
        String usuario = userField.getText().trim();
        String contrasena = new String(passField.getPassword()).trim();

        // Verificar si los campos están vacíos
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
