// Main_Admin.java
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Main_Admin extends JFrame {

    private JPanel panelMenu;
    private JPanel panelContenido;
    private CardLayout cardLayout;
    private JTable table;
    private AdminController adminController;

    public Main_Admin() {
        adminController = new AdminController(this); // Crear el controlador

        // Configuración del frame principal
        panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        panelMenu.setPreferredSize(new Dimension(175, getHeight()));
        panelMenu.setBackground(Color.WHITE);
        panelMenu.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));

        // Botones del menú
        JButton btnProyectos = crearBotonConIcono("Proyectos", "/Imgs/proyecto.png");
        JButton btnRegistrarProyecto = crearBotonConIcono("Nuevo Proyecto", "/Imgs/agregarProyecto.png");

        // Acción del botón "Nuevo Proyecto"
        btnRegistrarProyecto.addActionListener(adminController.getActionNuevoProyecto());

        // Añadir botones al menú
        panelMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        panelMenu.add(btnProyectos);
        panelMenu.add(Box.createVerticalGlue());
        panelMenu.add(btnRegistrarProyecto);

        // Panel de contenido con CardLayout
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);

        // Panel de Proyectos
        JPanel panelProyectos = new JPanel();
        panelProyectos.setLayout(new BorderLayout());
        tablaProyecto(panelProyectos);

        // Añadir el panel de proyectos al CardLayout
        panelContenido.add(panelProyectos, "Proyectos");

        // Acciones del botón
        btnProyectos.addActionListener(e -> cardLayout.show(panelContenido, "Proyectos"));

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

    // Implementación del método actualizarTablaProyectos
    public void actualizarTablaProyectos() {
        // Obtener el modelo de la tabla
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0); // Limpiar los datos actuales de la tabla

        // Llamar al controlador para actualizar los datos
        adminController.actualizarTablaProyectos(tableModel);

        // Refrescar la tabla
        table.revalidate();
        table.repaint();
    }

    // Configura la tabla de proyectos
    public void tablaProyecto(JPanel panel) {
        String[] columnNames = {"Codigo", "Nombre"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        // Configura el doble clic en la tabla
        adminController.configurarDobleClicTabla(table);

        // Actualiza los datos de la tabla
        adminController.actualizarTablaProyectos(tableModel);

        panel.removeAll();
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    // Método para crear un botón con ícono
    private JButton crearBotonConIcono(String texto, String rutaIcono) {
        JButton boton = new JButton(texto);
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaIcono));
        Image imagen = iconoOriginal.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

        boton.setLayout(new GridBagLayout());
        boton.setMaximumSize(new Dimension(180, 40));
        boton.setPreferredSize(new Dimension(180, 40));
        boton.setIcon(iconoRedimensionado);
        boton.setFocusPainted(false);
        boton.setBackground(Color.WHITE);
        boton.setBorder(new EmptyBorder(5, 5, 5, 5));
        boton.setOpaque(true);
        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main_Admin::new);
    }
}