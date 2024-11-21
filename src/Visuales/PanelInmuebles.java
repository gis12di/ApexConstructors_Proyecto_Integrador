import Logica.Inmuebles.Inmueble;
import Logica.Proyecto.Proyecto;
import Logica.Torre.Torre;
import Persistencia.CrudTorres;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelInmuebles extends JPanel {
    private JComboBox<Proyecto> comboProyectos;
    private JComboBox<Torre> comboTorres;
    private JTextArea textAreaInmuebles;
    private GestionProyectos gestionProyectos;
    private GestionInmuebles gestionInmuebles;

    public PanelInmuebles() {
        setLayout(new BorderLayout());
        gestionProyectos = new GestionProyectos();
        gestionInmuebles = new GestionInmuebles();

        comboProyectos = new JComboBox<>();
        comboTorres = new JComboBox<>();
        comboTorres.setEnabled(false);

        textAreaInmuebles = new JTextArea(10, 30);
        textAreaInmuebles.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaInmuebles);

        cargarProyectos();

        // Listener para cargar las torres al seleccionar un proyecto
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

        // Listener para mostrar inmuebles al seleccionar una torre
        comboTorres.addActionListener(e -> {
            Torre torreSeleccionada = (Torre) comboTorres.getSelectedItem();
            if (torreSeleccionada != null && !torreSeleccionada.getId().equals("0")) {
                mostrarInmuebles(torreSeleccionada.getId());
            } else {
                textAreaInmuebles.setText("Seleccione una torre válida.");
            }
        });

        // Panel para los combos
        JPanel panelCombos = new JPanel();
        panelCombos.add(new JLabel("Proyecto:"));
        panelCombos.add(comboProyectos);
        panelCombos.add(new JLabel("Torre:"));
        panelCombos.add(comboTorres);

        // Añadir los paneles al layout principal
        add(panelCombos, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void cargarProyectos() {
        // Obtiene el JFrame padre y pásalo como segundo argumento
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        List<Proyecto> proyectos = gestionProyectos.obtenerProyectos("", parentFrame);
        comboProyectos.removeAllItems();
        comboProyectos.addItem(new Proyecto(0, "Seleccionar Proyecto"));
        for (Proyecto proyecto : proyectos) {
            comboProyectos.addItem(proyecto);
        }
    }

    public void cargarTorres(int codigoProyecto) {
        CrudTorres crudTorres = new CrudTorres();
        List<Torre> torres = crudTorres.obtener(String.valueOf(codigoProyecto));

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
        textAreaInmuebles.setText(""); // Limpiar el área de texto
        if (inmuebles != null && !inmuebles.isEmpty()) {
            for (Inmueble inmueble : inmuebles) {
                textAreaInmuebles.append("Inmueble ID: " + inmueble.getId() + "\n");
                textAreaInmuebles.append("Número: " + inmueble.getNumInmueble() + "\n");
                textAreaInmuebles.append("Tipo de Unidad: " + inmueble.getTipoUnidad() + "\n");
                textAreaInmuebles.append("Valor: $" + inmueble.getValorInmueble() + "\n");
                textAreaInmuebles.append("Área: " + inmueble.getArea() + " m²\n\n");
            }
        } else {
            textAreaInmuebles.setText("No hay inmuebles disponibles para esta torre.");
        }
    }
}
