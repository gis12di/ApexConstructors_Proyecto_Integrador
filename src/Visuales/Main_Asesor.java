import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class Main_Asesor extends JFrame {
    private JPanel panelMenu;
    private JPanel panelContenido;
    private CardLayout cardLayout;

    public Main_Asesor() {
        panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        panelMenu.setPreferredSize(new Dimension(175, getHeight()));
        panelMenu.setBackground(Color.WHITE);
        panelMenu.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));

        JButton btnInmuebles = crearBotonConIcono("Inmuebles", "/Imgs/apartamentos.png");
        JButton btnPagosPendientes = crearBotonConIcono("Pagos Pendientes", "/Imgs/Pendiente.png");
        JButton btnPagosRealizados = crearBotonConIcono("Pagos Realizados", "/Imgs/dolar.png");
        JButton btnPagosVencidos = crearBotonConIcono("Pagos Vencidos", "/Imgs/vencido.png");
        JButton btnRegistrarPago = crearBotonConIcono("Registrar Pago", "/Imgs/registrar_pago.png");
        JButton btnVentas = crearBotonConIcono("Ventas", "/Imgs/ventas.png");

        btnInmuebles.addActionListener(e -> cardLayout.show(panelContenido, "Inmuebles"));
        btnPagosPendientes.addActionListener(e -> cardLayout.show(panelContenido, "PagosPendientes"));
        btnPagosRealizados.addActionListener(e -> cardLayout.show(panelContenido, "PagosRealizados"));
        btnPagosVencidos.addActionListener(e -> cardLayout.show(panelContenido, "PagosVencidos"));
        btnRegistrarPago.addActionListener(e -> JOptionPane.showMessageDialog(this, "Registrar Pago seleccionado"));
        btnVentas.addActionListener(e -> cardLayout.show(panelContenido, "Ventas"));

        panelMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        panelMenu.add(btnInmuebles);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMenu.add(btnVentas);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMenu.add(btnPagosPendientes);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMenu.add(btnPagosRealizados);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMenu.add(btnPagosVencidos);
        panelMenu.add(Box.createVerticalGlue());
        panelMenu.add(btnRegistrarPago);

        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        
        JPanel panelInmuebles = new JPanel();
        panelInmuebles.setBackground(Color.WHITE);
        panelContenido.add(new PanelInmuebles(), "Inmuebles");
        
        JPanel panelPagosPendientes = new JPanel();
        panelPagosPendientes.setBackground(Color.WHITE);
        panelPagosPendientes.add(new JLabel("Contenido de Pagos Pendientes"));

        JPanel panelPagosRealizados = new JPanel();
        panelPagosRealizados.setBackground(Color.WHITE);
        panelPagosRealizados.add(new JLabel("Contenido de Pagos Realizados"));

        JPanel panelPagosVencidos = new JPanel();
        panelPagosVencidos.setBackground(Color.WHITE);
        panelPagosVencidos.add(new JLabel("Contenido de Pagos Vencidos"));
        
        JPanel panelVentas = new JPanel();
        panelVentas.setBackground(Color.WHITE);
        panelContenido.add(new PanelVentas(), "Ventas");
        
        
        panelContenido.add(panelPagosPendientes, "PagosPendientes");
        panelContenido.add(panelPagosRealizados, "PagosRealizados");
        panelContenido.add(panelPagosVencidos, "PagosVencidos");
        

        setLayout(new BorderLayout());
        add(panelMenu, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);

        setTitle("Asesor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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
