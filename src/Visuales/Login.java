
import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {
    private JPanel loginPanel;
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;

    public Login() {
        setTitle("Login");
        setSize(650, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(Color.WHITE);
        add(loginPanel);

        // Cargar la imagen y agregarla al panel
        ImageIcon icon = new ImageIcon(getClass().getResource("/Imgs/apexAzulSinFondo.png"));
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(130, 30, 400, 150);
        loginPanel.add(imageLabel);

        JLabel titleLabel = new JLabel("Accede a tu cuenta", SwingConstants.CENTER);
        titleLabel.setBounds(125, 180, 400, 40);
        titleLabel.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30));
        loginPanel.add(titleLabel);

        JLabel userLabel = new JLabel("Usuario");
        userLabel.setBounds(125, 250, 400, 30);
        userLabel.setFont(new java.awt.Font("Segoe UI", 0, 16));
        loginPanel.add(userLabel);

        userField = new JTextField();
        userField.setBounds(125, 280, 400, 30);
        userField.setBorder(BorderFactory.createEmptyBorder());
        loginPanel.add(userField);

        JSeparator userSeparator = new JSeparator();
        userSeparator.setBounds(125, 310, 400, 1);
        loginPanel.add(userSeparator);

        JLabel passLabel = new JLabel("Contraseña");
        passLabel.setBounds(125, 330, 400, 30);
        passLabel.setFont(new java.awt.Font("Segoe UI", 0, 16));
        loginPanel.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(125, 360, 400, 30);
        passField.setBorder(BorderFactory.createEmptyBorder());
        loginPanel.add(passField);

        JSeparator passSeparator = new JSeparator();
        passSeparator.setBounds(125, 390, 400, 1);
        loginPanel.add(passSeparator);

        loginButton = new JButton("Acceder") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        loginButton.setBounds(235, 440, 180, 40);
        loginButton.setFont(new java.awt.Font("Segoe UI", 1, 18));
        loginButton.setBackground(new java.awt.Color(102, 153, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setOpaque(false);
        loginButton.setBorderPainted(false);
        loginButton.addActionListener(e -> {
            Main_Admin mainAdmin = new Main_Admin();
            mainAdmin.setTitle("Administrador");
            mainAdmin.setVisible(true);
            this.dispose(); // Cierra la ventana de login
        });
        loginPanel.add(loginButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
