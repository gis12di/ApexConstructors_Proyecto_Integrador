import Logica.Venta.GestionVentas;
import Logica.Venta.Venta;

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
        ventana.setSize(500, 500);
        ventana.setLayout(new GridLayout(11, 2, 10, 10));

        GestionVentas gestionVentas = new GestionVentas();

        // Campos del formulario
        JLabel lblPrecioTotal = new JLabel("Precio Total:");
        JTextField txtPrecioTotal = new JTextField();
        txtPrecioTotal.setEditable(false);

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

        // Actualizar excedente al cambiar el valor inicial
        txtInicial.addCaretListener(e -> actualizarExcedente(txtPrecioTotal, txtInicial, txtExcedente));

        // Botón "Guardar"
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarVenta(ventana, gestionVentas, txtPrecioTotal, txtInmuebleSeleccionado,
                txtFechaVenta, txtMatricula, txtFechaEscritura, txtDocumento, txtCedulaAsesor, comboPagos, txtInicial, txtExcedente));

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

    private void actualizarExcedente(JTextField txtPrecioTotal, JTextField txtInicial, JTextField txtExcedente) {
        try {
            double precioTotal = Double.parseDouble(txtPrecioTotal.getText());
            double inicial = Double.parseDouble(txtInicial.getText());
            txtExcedente.setText(String.valueOf(precioTotal - inicial));
        } catch (NumberFormatException ex) {
            txtExcedente.setText("Error");
        }
    }

    private void guardarVenta(JFrame ventana, GestionVentas gestionVentas, JTextField txtPrecioTotal,
                              JTextField txtInmuebleSeleccionado, JTextField txtFechaVenta, JTextField txtMatricula,
                              JTextField txtFechaEscritura, JTextField txtDocumento, JTextField txtCedulaAsesor,
                              JComboBox<Integer> comboPagos, JTextField txtInicial, JTextField txtExcedente) {
        try {
            // Validar datos obligatorios
            if (txtInmuebleSeleccionado.getText().isEmpty() || txtDocumento.getText().isEmpty() || txtCedulaAsesor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "Todos los campos obligatorios deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear un objeto Venta
            Venta venta = new Venta();
            venta.setIdVenta(txtMatricula.getText());
            venta.setPrecioTotal(Double.parseDouble(txtPrecioTotal.getText()));
            venta.setFechaVenta(java.sql.Date.valueOf(txtFechaVenta.getText()));
            venta.setMatricula(txtMatricula.getText());
            venta.setFechaEscritura(java.sql.Date.valueOf(txtFechaEscritura.getText()));
            venta.setIdInmueble(txtInmuebleSeleccionado.getText());
            venta.setNumDocumentoCliente(txtDocumento.getText());
            venta.setCedAsesor(txtCedulaAsesor.getText());
            venta.setNumPago(comboPagos.getSelectedItem().toString());
            venta.setValorInicial(Double.parseDouble(txtInicial.getText()));
            venta.setExcedente(Double.parseDouble(txtExcedente.getText()));

            // Guardar la venta usando GestionVentas
            boolean guardado = gestionVentas.guardarVenta(venta, ventana);
            if (guardado) {
                JOptionPane.showMessageDialog(ventana, "Venta guardada exitosamente.");
                ventana.dispose();
            } else {
                JOptionPane.showMessageDialog(ventana, "No se pudo guardar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(ventana, "Error en el formato de los números: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ventana, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
