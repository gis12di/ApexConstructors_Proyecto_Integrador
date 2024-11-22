import Logica.Inmuebles.GestionInmuebles;
import Logica.Inmuebles.Inmueble;
import Logica.Proyecto.GestionProyectos;
import Logica.Proyecto.Proyecto;

import Logica.Torre.Torre;
import Persistencia.CrudTorres;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PanelInmuebles extends JPanel {
    private JComboBox<Proyecto> comboProyectos;
    private JComboBox<Torre> comboTorres;
    private JTable tableInmuebles;
    private DefaultTableModel tableModel;
    private GestionProyectos gestionProyectos;
    private GestionInmuebles gestionInmuebles;
    private Inmueble inmuebleSeleccionado;


    public PanelInmuebles() {
        setLayout(new BorderLayout());
        gestionProyectos = new GestionProyectos();
        gestionInmuebles = new GestionInmuebles();

        comboProyectos = new JComboBox<>();
        comboTorres = new JComboBox<>();
        comboTorres.setEnabled(false);

        // Configuración de la tabla
        String[] columnNames = {"ID", "Número", "Tipo Unidad", "Valor", "Área"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableInmuebles = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableInmuebles);

        cargarProyectos();

        comboProyectos.addActionListener(e -> {
            Proyecto proyectoSeleccionado = (Proyecto) comboProyectos.getSelectedItem();
            if (proyectoSeleccionado != null && proyectoSeleccionado.getCodigo() != 0) {
                cargarTorres(proyectoSeleccionado.getCodigo());
                comboTorres.setEnabled(true);
            } else {
                comboTorres.setEnabled(false);
                comboTorres.removeAllItems();
                comboTorres.addItem(new Torre("0", "Seleccionar Torre"));
            }
        });

        comboTorres.addActionListener(e -> {
            Torre torreSeleccionada = (Torre) comboTorres.getSelectedItem();
            if (torreSeleccionada != null && !torreSeleccionada.getId().equals("0")) {
                mostrarInmuebles(torreSeleccionada.getId());
            } else {
                tableModel.setRowCount(0); // Limpiar tabla
            }
        });

        // Agregar listener para capturar el inmueble seleccionado
        tableInmuebles.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = tableInmuebles.getSelectedRow();
                if (selectedRow != -1) {
                    String id = (String) tableModel.getValueAt(selectedRow, 0);
                    String numInmueble = (String) tableModel.getValueAt(selectedRow, 1);
                    String tipoUnidad = (String) tableModel.getValueAt(selectedRow, 2);
                    double valorInmueble = (double) tableModel.getValueAt(selectedRow, 3);
                    double area = (double) tableModel.getValueAt(selectedRow, 4);

                    // Actualizar el inmueble seleccionado
                    inmuebleSeleccionado = new Inmueble();
                    inmuebleSeleccionado.setId(id);
                    inmuebleSeleccionado.setNumInmueble(numInmueble);
                    inmuebleSeleccionado.setTipoUnidad(tipoUnidad);
                    inmuebleSeleccionado.setValorInmueble(valorInmueble);
                    inmuebleSeleccionado.setArea(area);
                }
            }
        });


        JPanel panelCombos = new JPanel();
        panelCombos.add(new JLabel("Proyecto:"));
        panelCombos.add(comboProyectos);
        panelCombos.add(new JLabel("Torre:"));
        panelCombos.add(comboTorres);

        add(panelCombos, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }


    private void cargarProyectos() {
        List<Proyecto> proyectos = gestionProyectos.obtenerProyectos("", null);
        comboProyectos.removeAllItems();
        comboProyectos.addItem(new Proyecto(0, "Seleccionar Proyecto"));
        for (Proyecto proyecto : proyectos) {
            comboProyectos.addItem(proyecto);
        }
    }

    public void cargarTorres(int codigoProyecto) {
        List<Torre> torres = new CrudTorres().obtener(String.valueOf(codigoProyecto));
        comboTorres.removeAllItems();
        comboTorres.addItem(new Torre("0", "Seleccionar Torre"));

        if (torres != null && !torres.isEmpty()) {
            for (Torre torre : torres) {
                comboTorres.addItem(torre);
            }
        } else {
            comboTorres.addItem(new Torre("0", "No hay torres disponibles"));
        }
    }

    private void mostrarInmuebles(String torreId) {
        List<Inmueble> inmuebles = gestionInmuebles.obtenerInmueblesPorTorre(torreId);
        tableModel.setRowCount(0); // Limpiar tabla
        if (inmuebles != null && !inmuebles.isEmpty()) {
            for (Inmueble inmueble : inmuebles) {
                tableModel.addRow(new Object[]{
                        inmueble.getId(),
                        inmueble.getNumInmueble(),
                        inmueble.getTipoUnidad(),
                        inmueble.getValorInmueble(),
                        inmueble.getArea()
                });
            }
        }
    }

    public String getInmuebleSeleccionado() {
        if (inmuebleSeleccionado != null) {
            return "ID: " + inmuebleSeleccionado.getId() +
                   ", Número: " + inmuebleSeleccionado.getNumInmueble() +
                   ", Tipo: " + inmuebleSeleccionado.getTipoUnidad();
        }
        return null;
    }


    public double getPrecioInmuebleSeleccionado() {
        if (inmuebleSeleccionado != null) {
            return inmuebleSeleccionado.getValorInmueble();
        }
        return 0;
    }

}
