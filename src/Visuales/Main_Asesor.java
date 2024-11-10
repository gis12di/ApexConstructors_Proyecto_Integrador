import Logica.Inmuebles.Inmueble;
import Logica.Proyecto.Proyecto;
import Logica.Torre.GestionTorres;
import Logica.Torre.Torre;
import Persistencia.CrudTorres;
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
    private JComboBox<Proyecto> comboProyectos;
    private JComboBox<Torre> comboTorres;
    private JTextArea textAreaInmuebles;

    private GestionProyectos gestionProyectos;
    private GestionTorres gestionTorres;
    private GestionInmuebles gestionInmuebles;

    public Main_Asesor() {
        gestionProyectos = new GestionProyectos();
        gestionTorres = new GestionTorres();
        gestionInmuebles = new GestionInmuebles();

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

        btnInmuebles.addActionListener(e -> cardLayout.show(panelContenido, "Inmuebles"));
        btnPagosPendientes.addActionListener(e -> cardLayout.show(panelContenido, "PagosPendientes"));
        btnPagosRealizados.addActionListener(e -> cardLayout.show(panelContenido, "PagosRealizados"));
        btnPagosVencidos.addActionListener(e -> cardLayout.show(panelContenido, "PagosVencidos"));
        btnRegistrarPago.addActionListener(e -> JOptionPane.showMessageDialog(this, "Registrar Pago seleccionado"));

        panelMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        panelMenu.add(btnInmuebles);
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

        panelContenido.add(panelInmuebles, "Inmuebles");
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

    private JPanel crearPanelInmuebles() {
        JPanel panelInmuebles = new JPanel(new BorderLayout());
        panelInmuebles.setBackground(Color.WHITE);

        comboProyectos = new JComboBox<>();
        comboTorres = new JComboBox<>();
        comboTorres.setEnabled(false);

        textAreaInmuebles = new JTextArea(10, 30);
        textAreaInmuebles.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaInmuebles);

        cargarProyectos();

        comboProyectos.addActionListener(e -> {
            Proyecto proyectoSeleccionado = (Proyecto) comboProyectos.getSelectedItem();
            if (proyectoSeleccionado != null && proyectoSeleccionado.getCodigo() != 0) {
                comboTorres.setEnabled(true); // Habilitar el ComboBox de Torres
                cargarTorres(proyectoSeleccionado.getCodigo());
            } else {
                comboTorres.setEnabled(false); // Deshabilitar si no hay un proyecto válido
                comboTorres.removeAllItems();
                comboTorres.addItem(new Torre("0", "Seleccionar Torre"));
            }
        });




        comboTorres.addActionListener(e -> {
            Torre torreSeleccionada = (Torre) comboTorres.getSelectedItem();
            if (torreSeleccionada != null) {
                mostrarInmuebles(torreSeleccionada.getId());
            }
        });

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
        comboProyectos.addItem(new Proyecto(0, "Seleccionar Proyecto"));
        for (Proyecto proyecto : proyectos) {
            comboProyectos.addItem(proyecto);
        }
    }

    public void cargarTorres(int codigoProyecto) {
        // Instancia de CrudTorres para acceder a los métodos de persistencia
        CrudTorres crudTorres = new CrudTorres();

        // Obtener las torres del proyecto seleccionado
        List<Torre> torres = crudTorres.obtenerPorCodProyecto(String.valueOf(codigoProyecto));

        // Limpiar el comboBox de torres antes de añadir nuevas
        comboTorres.removeAllItems();
        comboTorres.addItem(new Torre("0", "Seleccionar Torre")); // Opción por defecto

        // Añadir cada torre al comboBox
        for (Torre torre : torres) {
            comboTorres.addItem(torre);
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
