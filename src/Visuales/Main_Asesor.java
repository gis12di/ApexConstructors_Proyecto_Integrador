import Logica.Inmuebles.Inmueble;
import Logica.Proyecto.Proyecto;
import Logica.Torre.Torre;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.border.EmptyBorder;

public class Main_Asesor extends JFrame {
    private JPanel panelMenu;
    private JPanel panelContenido;
    private CardLayout cardLayout;
    private JComboBox<String> comboProyectos;
    private JComboBox<String> comboTorres;
    private JTextArea textAreaInmuebles;

    private GestionProyectos gestionProyectos;
    private GestionTorres gestionTorres;
    private GestionInmuebles gestionInmuebles;

    public Main_Asesor() {
        gestionProyectos = new GestionProyectos();
        gestionTorres = new GestionTorres();
        gestionInmuebles = new GestionInmuebles();

        // Configuración básica del panel de menú
        panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        panelMenu.setPreferredSize(new Dimension(175, getHeight()));
        panelMenu.setBackground(Color.WHITE);
        panelMenu.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));

        // Crear los botones con iconos y añadirlos al menú
        JButton btnInmuebles = crearBotonConIcono("Inmuebles", "/Imgs/apartamentos.png");
        JButton btnPagosPendientes = crearBotonConIcono("Pagos Pendientes", "/Imgs/Pendiente.png");
        JButton btnPagosRealizados = crearBotonConIcono("Pagos Realizados", "/Imgs/dolar.png");
        JButton btnPagosVencidos = crearBotonConIcono("Pagos Vencidos", "/Imgs/vencido.png");
        JButton btnRegistrarPago = crearBotonConIcono("Registrar Pago", "/Imgs/registrar_pago.png");

        // Añadir acciones a los botones para cambiar entre paneles
        btnInmuebles.addActionListener(e -> cardLayout.show(panelContenido, "Inmuebles"));
        btnPagosPendientes.addActionListener(e -> cardLayout.show(panelContenido, "PagosPendientes"));
        btnPagosRealizados.addActionListener(e -> cardLayout.show(panelContenido, "PagosRealizados"));
        btnPagosVencidos.addActionListener(e -> cardLayout.show(panelContenido, "PagosVencidos"));
        btnRegistrarPago.addActionListener(e -> JOptionPane.showMessageDialog(this, "Registrar Pago seleccionado"));

        // Añadir botones al panel del menú con espacios entre ellos
        panelMenu.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio superior
        panelMenu.add(btnInmuebles);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMenu.add(btnPagosPendientes);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMenu.add(btnPagosRealizados);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMenu.add(btnPagosVencidos);
        panelMenu.add(Box.createVerticalGlue()); // Empuja el siguiente botón hacia la parte inferior
        panelMenu.add(btnRegistrarPago); // Botón "Registrar Pago" al final del menú

        // Crear el panel de contenido con CardLayout
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);

        // Crear los paneles de cada sección
        JPanel panelInmuebles = crearPanelInmuebles();
        JPanel panelPagosPendientes = new JPanel();
        panelPagosPendientes.setBackground(Color.WHITE);
        panelPagosPendientes.add(new JLabel("Contenido de Pagos Pendientes"));

        JPanel panelPagosRealizados = new JPanel();
        panelPagosRealizados.setBackground(Color.WHITE);
        panelPagosRealizados.add(new JLabel("Contenido de Pagos Realizados"));

        JPanel panelPagosVencidos = new JPanel();
        panelPagosVencidos.setBackground(Color.WHITE);
        panelPagosVencidos.add(new JLabel("Contenido de Pagos Vencidos"));

        // Añadir paneles al panel de contenido
        panelContenido.add(panelInmuebles, "Inmuebles");
        panelContenido.add(panelPagosPendientes, "PagosPendientes");
        panelContenido.add(panelPagosRealizados, "PagosRealizados");
        panelContenido.add(panelPagosVencidos, "PagosVencidos");

        // Configurar el layout y añadir los paneles principales
        setLayout(new BorderLayout());
        add(panelMenu, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);

        // Configurar el frame
        setTitle("Asesor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel crearPanelInmuebles() {
        JPanel panelInmuebles = new JPanel(new BorderLayout());
        panelInmuebles.setBackground(Color.WHITE);

        comboProyectos = new JComboBox<>();
        comboTorres = new JComboBox<>();
        comboTorres.setEnabled(false);

        textAreaInmuebles = new JTextArea(10, 30);
        textAreaInmuebles.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaInmuebles);

        // Llenar el comboProyectos
        cargarProyectos();

        // Listeners para los combo boxes
        comboProyectos.addActionListener(e -> cargarTorres((String) comboProyectos.getSelectedItem()));
        comboTorres.addActionListener(e -> mostrarInmuebles((String) comboTorres.getSelectedItem()));

        // Añadir los componentes al panel de Inmuebles
        JPanel panelCombos = new JPanel();
        panelCombos.add(new JLabel("Proyecto:"));
        panelCombos.add(comboProyectos);
        panelCombos.add(new JLabel("Torre:"));
        panelCombos.add(comboTorres);

        panelInmuebles.add(panelCombos, BorderLayout.NORTH);
        panelInmuebles.add(scrollPane, BorderLayout.CENTER);

        return panelInmuebles;
    }

    private void cargarProyectos() {
        List<Proyecto> proyectos = gestionProyectos.obtenerProyectos();
        comboProyectos.removeAllItems();
        for (Proyecto proyecto : proyectos) {
            comboProyectos.addItem(proyecto.getNombre());
        }
    }

    private void cargarTorres(String proyectoNombre) {
        System.out.println("Cargando torres para el proyecto: " + proyectoNombre);
        List<Torre> torres = gestionTorres.obtenerTorresPorCodProyecto(proyectoNombre);
        System.out.println("Número de torres encontradas: " + torres.size());

        comboTorres.removeAllItems();
        if (torres.isEmpty()) {
            comboTorres.setEnabled(false);
            JOptionPane.showMessageDialog(this, "El proyecto seleccionado no tiene torres.", "Sin Torres", JOptionPane.INFORMATION_MESSAGE);
        } else {
            comboTorres.setEnabled(true);
            for (Torre torre : torres) {
                System.out.println("Torre encontrada: " + torre.getNumTorre());
                comboTorres.addItem(torre.getNumTorre());
            }
        }
    }



    private void mostrarInmuebles(String torreId) {
        List<Inmueble> inmuebles = gestionInmuebles.obtenerInmueblesPorTorre(torreId);
        textAreaInmuebles.setText("");
        for (Inmueble inmueble : inmuebles) {
            textAreaInmuebles.append("Inmueble ID: " + inmueble.getId() + "\n");
            textAreaInmuebles.append("Número: " + inmueble.getNumInmueble() + "\n");
            textAreaInmuebles.append("Tipo de Unidad: " + inmueble.getTipoUnidad() + "\n");
            textAreaInmuebles.append("Valor: $" + inmueble.getValorInmueble() + "\n");
            textAreaInmuebles.append("Área: " + inmueble.getArea() + " m²\n\n");
        }
    }

    private JButton crearBotonConIcono(String texto, String rutaIcono) {
        JButton boton = new JButton(texto);
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaIcono));
        Image imagen = iconoOriginal.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

        boton.setIcon(iconoRedimensionado);
        boton.setFocusPainted(false);
        boton.setBackground(Color.WHITE);
        boton.setBorder(new EmptyBorder(5, 5, 5, 5));
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setHorizontalTextPosition(SwingConstants.RIGHT);
        boton.setVerticalTextPosition(SwingConstants.CENTER);
        boton.setIconTextGap(10);
        boton.setMaximumSize(new Dimension(180, 40));
        boton.setPreferredSize(new Dimension(180, 40));

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
        new Main_Asesor();
    }
}
