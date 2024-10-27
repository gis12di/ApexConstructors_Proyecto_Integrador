import javax.swing.SwingUtilities;

public class ApexConstructors {

    public static void main(String[] args) {
        // Ejecutar la interfaz de Login
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
