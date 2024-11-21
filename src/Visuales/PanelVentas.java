import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.UUID;

public class PanelVentas extends JPanel {
    public PanelVentas() {
        setLayout(new BorderLayout());

        JButton btnAgregarVenta = new JButton("Agregar Venta");
        btnAgregarVenta.addActionListener(e -> abrirFormularioVenta());

        add(btnAgregarVenta, BorderLayout.NORTH);
        add(new JLabel("Aquí se mostrarán todas las ventas"), BorderLayout.CENTER);
    }

    private void abrirFormularioVenta() {
        JFrame ventana = new JFrame("Formulario de Venta");
        ventana.setSize(500, 400);
        ventana.setLayout(new GridLayout(10, 2, 10, 10));

        // Campos del formulario
        JLabel lblPrecioTotal = new JLabel("Precio Total:");
        JTextField txtPrecioTotal = new JTextField();
        txtPrecioTotal.setEditable(false); // Solo editable después de seleccionar un inmueble.

        JLabel lblInmuebleSeleccionado = new JLabel("Inmueble Seleccionado:");
        JTextField txtInmuebleSeleccionado = new JTextField();
        txtInmuebleSeleccionado.setEditable(false);

        JButton btnSeleccionarInmueble = new JButton("Seleccionar Inmueble");
        btnSeleccionarInmueble.addActionListener(e -> abrirSelectorInmuebles(txtInmuebleSeleccionado, txtPrecioTotal));

        JLabel lblFechaVenta = new JLabel("Fecha Venta:");
        JTextField txtFechaVenta = new JTextField(LocalDate.now().toString());
        txtFechaVenta.setEditable(false);

        JLabel lblMatricula = new JLabel("Matrícula:");
        JTextField txtMatricula = new JTextField(UUID.randomUUID().toString());
        txtMatricula.setEditable(false);

        JLabel lblFechaEscritura = new JLabel("Fecha Escritura:");
        JTextField txtFechaEscritura = new JTextField(LocalDate.now().plusDays(1).toString());
        txtFechaEscritura.setEditable(false);

        JLabel lblDocumento = new JLabel("Documento Cliente:");
        JTextField txtDocumento = new JTextField();

        JLabel lblCedulaAsesor = new JLabel("Cédula Asesor:");
        JTextField txtCedulaAsesor = new JTextField();

        JLabel lblNumPagos = new JLabel("Número de Pagos:");
        JComboBox<Integer> comboPagos = new JComboBox<>();
        for (int i = 2; i <= 12; i++) comboPagos.addItem(i);

        JLabel lblInicial = new JLabel("Valor Inicial:");
        JTextField txtInicial = new JTextField();

        JLabel lblExcedente = new JLabel("Excedente:");
        JTextField txtExcedente = new JTextField();
        txtExcedente.setEditable(false);

        txtInicial.addActionListener(e -> {
            try {
                double precioTotal = Double.parseDouble(txtPrecioTotal.getText());
                double inicial = Double.parseDouble(txtInicial.getText());
                txtExcedente.setText(String.valueOf(precioTotal - inicial));
            } catch (NumberFormatException ex) {
                txtExcedente.setText("Error");
            }
        });

        // Agregar componentes
        ventana.add(lblPrecioTotal);
        ventana.add(txtPrecioTotal);
        ventana.add(lblInmuebleSeleccionado);
        ventana.add(txtInmuebleSeleccionado);
        ventana.add(new JLabel());
        ventana.add(btnSeleccionarInmueble);
        ventana.add(lblFechaVenta);
        ventana.add(txtFechaVenta);
        ventana.add(lblMatricula);
        ventana.add(txtMatricula);
        ventana.add(lblFechaEscritura);
        ventana.add(txtFechaEscritura);
        ventana.add(lblDocumento);
        ventana.add(txtDocumento);
        ventana.add(lblCedulaAsesor);
        ventana.add(txtCedulaAsesor);
        ventana.add(lblNumPagos);
        ventana.add(comboPagos);
        ventana.add(lblInicial);
        ventana.add(txtInicial);
        ventana.add(lblExcedente);
        ventana.add(txtExcedente);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> ventana.dispose());
        ventana.add(new JLabel());
        ventana.add(btnGuardar);

        ventana.setVisible(true);
    }

    private void abrirSelectorInmuebles(JTextField txtInmuebleSeleccionado, JTextField txtPrecioTotal) {
        JFrame selector = new JFrame("Seleccionar Inmueble");
        selector.setSize(600, 400);
        selector.setLayout(new BorderLayout());

        // Crear el panel de inmuebles
        PanelInmuebles panelInmuebles = new PanelInmuebles();

        // Crear el botón para seleccionar inmueble
        JButton btnSeleccionar = new JButton("Seleccionar Inmueble");

        // Agregar acción al botón
        btnSeleccionar.addActionListener(e -> {
            String seleccionado = panelInmuebles.getInmuebleSeleccionado();
            double precio = panelInmuebles.getPrecioInmuebleSeleccionado();

            if (seleccionado != null && precio > 0) {
                txtInmuebleSeleccionado.setText(seleccionado);
                txtPrecioTotal.setText(String.valueOf(precio));
                selector.dispose();
            } else {
                JOptionPane.showMessageDialog(selector, "Debe seleccionar un inmueble válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Agregar el panel y el botón al JFrame
        selector.add(panelInmuebles, BorderLayout.CENTER);
        selector.add(btnSeleccionar, BorderLayout.SOUTH);

        // Mostrar el JFrame
        selector.setVisible(true);
    }
   

}
