import Logica.Venta.GestionVentas;
import Logica.Venta.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class PanelVentas extends JPanel {
    private JTable tablaVentas;
    private DefaultTableModel modeloTabla;
    private GestionVentas gestionVentas;

    public PanelVentas() {
        setLayout(new BorderLayout());
        gestionVentas = new GestionVentas();

        // Botón para agregar una venta
        JButton btnAgregarVenta = new JButton("Agregar Venta");
        btnAgregarVenta.addActionListener(e -> abrirFormularioVenta());

        add(btnAgregarVenta, BorderLayout.NORTH);

        // Crear tabla y modelo de datos
        String[] columnas = {"ID Venta", "Precio Total", "Fecha Venta", "Matrícula", "Fecha Escritura", "ID Inmueble", "Documento Cliente", "Cédula Asesor", "Número de Pagos", "Valor Inicial", "Excedente"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaVentas = new JTable(modeloTabla);

        // Cargar los datos de las ventas en la tabla
        cargarVentasEnTabla();

        // Agregar la tabla al panel dentro de un JScrollPane
        add(new JScrollPane(tablaVentas), BorderLayout.CENTER);
    }

    // Método para cargar ventas en la tabla
    private void cargarVentasEnTabla() {
        // Limpiar la tabla antes de cargar los datos
        modeloTabla.setRowCount(0);
        
        // Obtener lista de ventas
        List<Venta> ventas = gestionVentas.obtenerVentas();
        
        // Agregar cada venta a la tabla
        for (Venta venta : ventas) {
            Object[] fila = {
                venta.getIdVenta(),
                venta.getPrecioTotal(),
                venta.getFechaVenta(),
                venta.getMatricula(),
                venta.getFechaEscritura(),
                venta.getIdInmueble(),
                venta.getNumDocumentoCliente(),
                venta.getCedAsesor(),
                venta.getNumPago(),
                venta.getValorInicial(),
                venta.getExcedente()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void abrirFormularioVenta() {
        JFrame ventana = new JFrame("Formulario de Venta");
        ventana.setSize(500, 500);
        ventana.setLayout(new GridLayout(9, 2, 10, 10));

        GestionVentas gestionVentas = new GestionVentas();

        // Campos del formulario
        JLabel lblPrecioTotal = new JLabel("Precio Total:");
        JTextField txtPrecioTotal = new JTextField();
        txtPrecioTotal.setEditable(false); // No editable; se llenará al seleccionar inmueble

        JLabel lblIdInmueble = new JLabel("ID Inmueble:");
        JTextField txtIdInmueble = new JTextField(); // Campo para mostrar ID del inmueble
        txtIdInmueble.setEditable(false);

        JButton btnSeleccionarInmueble = new JButton("Seleccionar Inmueble");
        btnSeleccionarInmueble.addActionListener(e -> abrirSelectorInmuebles(txtIdInmueble, txtPrecioTotal));

        JLabel lblFechaVenta = new JLabel("Fecha Venta:");
        JTextField txtFechaVenta = new JTextField(LocalDate.now().toString());
        txtFechaVenta.setEditable(false);

        JLabel lblMatricula = new JLabel("Matrícula:");
        JTextField txtMatricula = new JTextField("M-" + System.currentTimeMillis());
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
        txtExcedente.setEditable(false); // El excedente no es editable

        // Actualizar excedente al cambiar el valor inicial
        txtInicial.addCaretListener(e -> actualizarExcedente(txtPrecioTotal, txtInicial, txtExcedente));

        // Botón "Guardar"
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
                @Override
                protected Boolean doInBackground() {
                    try {
                        Venta nuevaVenta = new Venta();
                        nuevaVenta.setPrecioTotal(Double.parseDouble(txtPrecioTotal.getText()));
                        nuevaVenta.setFechaVenta(java.sql.Date.valueOf(txtFechaVenta.getText()));
                        nuevaVenta.setMatricula(txtMatricula.getText());
                        nuevaVenta.setFechaEscritura(java.sql.Date.valueOf(txtFechaEscritura.getText()));
                        nuevaVenta.setIdInmueble(txtIdInmueble.getText());
                        nuevaVenta.setNumDocumentoCliente(txtDocumento.getText());
                        nuevaVenta.setCedAsesor(txtCedulaAsesor.getText());
                        nuevaVenta.setNumPago(comboPagos.getSelectedItem().toString());
                        nuevaVenta.setValorInicial(Double.parseDouble(txtInicial.getText()));
                        nuevaVenta.setExcedente(Double.parseDouble(txtExcedente.getText()));

                        return gestionVentas.guardarVenta(nuevaVenta, ventana);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return false;
                    }
                }

                @Override
                protected void done() {
                    try {
                        boolean guardado = get();
                        if (guardado) {
                            JOptionPane.showMessageDialog(ventana, "Venta guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            ventana.dispose();
                        } else {
                            JOptionPane.showMessageDialog(ventana, "Error al guardar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ventana, "Error durante el guardado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            };
            worker.execute();
        });

        // Agregar componentes
        ventana.add(lblPrecioTotal);
        ventana.add(txtPrecioTotal);
        ventana.add(lblIdInmueble);
        ventana.add(txtIdInmueble);
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

    private void abrirSelectorInmuebles(JTextField txtIdInmueble, JTextField txtPrecioTotal) {
        JFrame selector = new JFrame("Seleccionar Inmueble");
        selector.setSize(600, 400);
        selector.setLayout(new BorderLayout());

        PanelInmuebles panelInmuebles = new PanelInmuebles();

        JButton btnSeleccionar = new JButton("Seleccionar Inmueble");
        btnSeleccionar.addActionListener(e -> {
            String idInmueble = panelInmuebles.getIdInmuebleSeleccionado();
            double precio = panelInmuebles.getPrecioInmuebleSeleccionado();

            if (idInmueble != null && precio > 0) {
                txtIdInmueble.setText(idInmueble);
                txtPrecioTotal.setText(String.valueOf(precio));
                selector.dispose();
            } else {
                JOptionPane.showMessageDialog(selector, "Debe seleccionar un inmueble válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        selector.add(panelInmuebles, BorderLayout.CENTER);
        selector.add(btnSeleccionar, BorderLayout.SOUTH);
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
}
