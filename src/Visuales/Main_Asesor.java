
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Main_Asesor extends javax.swing.JFrame {
    private JPanel panelMenu;
    private JPanel panelContenido;
    private CardLayout cardLayout;

    public Main_Asesor() {
        // Crear el panel del menú (barra lateral izquierda) y darle un fondo blanco
        panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS)); // Apila los componentes verticalmente
        panelMenu.setPreferredSize(new Dimension(175, getHeight())); // Ajusta el ancho del menú
        panelMenu.setBackground(Color.WHITE); // Fondo blanco para el menú
        panelMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        
        // Botones con íconos
        JButton btnInmuebles = crearBotonConIcono("Inmuebles", "/Imgs/apartamentos.png");
        JButton btnPagosPendientes = crearBotonConIcono("Pagos Pendientes", "/Imgs/Pendiente.png");
        JButton btnPagosRealizados = crearBotonConIcono("Pagos Realizados", "/Imgs/dolar.png");
        JButton btnPagosVencidos = crearBotonConIcono("Pagos Vencidos", "/Imgs/vencido.png");
        JButton btnRegistrarPago = crearBotonConIcono("Registrar Pago", "/Imgs/registrar_pago.png");
        btnRegistrarPago.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        
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
        JPanel panelInmuebles = new JPanel();
        panelInmuebles.setBackground(Color.WHITE);
        panelInmuebles.add(new JLabel("Contenido de Pagos Inmuebles"));

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

        // Configurar acciones de los botones
        btnInmuebles.addActionListener(e -> cardLayout.show(panelContenido, "Inmuebles"));
        btnPagosPendientes.addActionListener(e -> cardLayout.show(panelContenido, "PagosPendientes"));
        btnPagosRealizados.addActionListener(e -> cardLayout.show(panelContenido, "PagosRealizados"));
        btnPagosVencidos.addActionListener(e -> cardLayout.show(panelContenido, "PagosVencidos"));

        // Añadir el menú y el contenido al frame principal
        setLayout(new BorderLayout());
        add(panelMenu, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);

        // Configurar el frame
        setTitle("Asesor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    

    // Método para crear un botón personalizado con icono y texto
    private JButton crearBotonConIcono(String texto, String rutaIcono) {
        JButton boton = new JButton(texto);

        // Cargar el ícono y redimensionarlo si es necesario
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaIcono));
        Image imagen = iconoOriginal.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(24, 24, Image.SCALE_SMOOTH); // Tamaño del ícono
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

        // Establecer el layout del botón como GridBagLayout para posicionar el texto a la derecha
        boton.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;  // Alinea el contenido hacia la derecha
        gbc.insets = new Insets(5, 5, 5, 5);  // Padding de 5 píxeles

        // Establecer tamaño de los botones
        boton.setMaximumSize(new Dimension(180, 40));
        boton.setPreferredSize(new Dimension(180, 40)); // Tamaño de cada botón

        boton.setIcon(iconoRedimensionado); // Asignar el ícono
        boton.setFocusPainted(false);
        boton.setBackground(Color.WHITE);
        boton.setBorder(new EmptyBorder(5, 5, 5, 5)); // Padding de 5 píxeles dentro del botón
        boton.setBorderPainted(false);
        boton.setOpaque(true);

        // Configurar la posición del texto e ícono
        boton.setHorizontalTextPosition(SwingConstants.RIGHT); // Texto a la derecha del ícono
        boton.setVerticalTextPosition(SwingConstants.CENTER); // Ícono y texto centrados verticalmente
        boton.setIconTextGap(10); // Espaciado entre el ícono y el texto

        // Cambiar color cuando es presionado
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
