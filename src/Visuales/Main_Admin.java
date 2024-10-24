import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Main_Admin extends JFrame {
    private JPanel panelMenu;
    private JPanel panelContenido;
    private CardLayout cardLayout;
    private JTable table;

    public Main_Admin() {
        // Configuración del frame principal
        panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS)); // Apila los componentes verticalmente
        panelMenu.setPreferredSize(new Dimension(175, getHeight())); // Ajusta el ancho del menú
        panelMenu.setBackground(Color.WHITE); // Fondo blanco para el menú
        panelMenu.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));

        // Botones del menú
        JButton btnProyectos = crearBotonConIcono("Proyectos", "/Imgs/proyecto.png");
        JButton btnTorres = crearBotonConIcono("Torres", "/Imgs/torre.png");
        JButton btnInmuebles = crearBotonConIcono("Inmuebles", "/Imgs/apartamento.png");
        JButton btnRegistrarProyecto = crearBotonConIcono("Nuevo Proyecto", "/Imgs/agregarProyecto.png");

        // Añadir botones al menú
        panelMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        panelMenu.add(btnProyectos);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMenu.add(btnTorres);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMenu.add(btnInmuebles);
        panelMenu.add(Box.createVerticalGlue());
        panelMenu.add(btnRegistrarProyecto);

        // Panel de contenido
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);

        // Panel de Proyectos
        JPanel panelProyectos = new JPanel();
        panelProyectos.setBackground(Color.WHITE);
        panelProyectos.add(new JLabel("Contenido de Proyectos"));

        // Otros paneles (Torres, Apartamentos)
        JPanel panelTorres = new JPanel();
        panelTorres.setBackground(Color.WHITE);
        panelTorres.add(new JLabel("Contenido de Torres"));

        JPanel panelInmueblesContent = new JPanel();
        panelInmueblesContent.setBackground(Color.WHITE);
        panelInmueblesContent.add(new JLabel("Contenido de Inmuebles"));

        // Añadir los paneles al contenido
        panelContenido.add(panelProyectos, "Proyectos");
        panelContenido.add(panelTorres, "Torres");
        panelContenido.add(panelInmueblesContent, "Inmuebles");

        // Acciones de los botones
        btnProyectos.addActionListener(e -> cardLayout.show(panelContenido, "Proyectos"));
        btnTorres.addActionListener(e -> cardLayout.show(panelContenido, "Torres"));
        btnInmuebles.addActionListener(e -> cardLayout.show(panelContenido, "Inmuebles"));

        // Añadir el menú y el contenido al frame
        setLayout(new BorderLayout());
        add(panelMenu, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);

        // Mostrar el panel de Proyectos por defecto
        cardLayout.show(panelContenido, "Proyectos");

        // Configuración de la ventana
        setTitle("Administrador");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    // Método para crear un botón con ícono
    private JButton crearBotonConIcono(String texto, String rutaIcono) {
        JButton boton = new JButton(texto);
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaIcono));
        Image imagen = iconoOriginal.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

        boton.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);

        boton.setMaximumSize(new Dimension(180, 40));
        boton.setPreferredSize(new Dimension(180, 40));

        boton.setIcon(iconoRedimensionado);
        boton.setFocusPainted(false);
        boton.setBackground(Color.WHITE);
        boton.setBorder(new EmptyBorder(5, 5, 5, 5));
        boton.setBorderPainted(false);
        boton.setOpaque(true);

        boton.setHorizontalTextPosition(SwingConstants.RIGHT);
        boton.setVerticalTextPosition(SwingConstants.CENTER);
        boton.setIconTextGap(10);

        boton.getModel().addChangeListener(e -> {
            ButtonModel model = boton.getModel();
            if (model.isPressed()) {
                boton.setBackground(Color.LIGHT_GRAY);
            } else {
                boton.setBackground(Color.WHITE);
            }
        });

        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main_Admin());
    }
}

