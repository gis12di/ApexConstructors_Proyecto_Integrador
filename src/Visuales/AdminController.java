import Logica.Proyecto.Proyecto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminController {
    private final Main_Admin mainAdmin;
    private final GestionProyectos gestionProyectos;
    private JTable table;

    public AdminController(Main_Admin mainAdmin) {
        this.mainAdmin = mainAdmin;
        this.gestionProyectos = new GestionProyectos();
    }

    public ActionListener getActionNuevoProyecto() {
        return e -> {
            CrearProyecto crearProyecto = new CrearProyecto(mainAdmin);
            crearProyecto.setTitle("Crear Proyecto");
            crearProyecto.setVisible(true);

            String codProyectoRecienCreado = "codigoDelNuevoProyecto"; // Código simulado para el proyecto
            crearProyecto.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    CrearTorres crearTorres = new CrearTorres(codProyectoRecienCreado);
                    crearTorres.setVisible(true);
                }
            });
        };
    }

    public void actualizarTablaProyectos(DefaultTableModel tableModel) {
        List<Proyecto> proyectos = gestionProyectos.obtenerProyectos();

        tableModel.setRowCount(0); // Limpiar la tabla
        for (Proyecto proy : proyectos) {
            Object[] rowData = {proy.getCodigo(), proy.getNombre()};
            tableModel.addRow(rowData);
        }
    }

    public void abrirVentanaEdicion(String idProyecto, String nombreProyecto, int row) {
        EditarProyecto editarProyecto = new EditarProyecto(idProyecto, nombreProyecto, row, table);
        editarProyecto.setVisible(true);
    }

    public void configurarDobleClicTabla(JTable table) {
        this.table = table;
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    String codProyecto = table.getValueAt(row, 0).toString();

                    if (SwingUtilities.isRightMouseButton(e)) {
                        String nombreProyecto = table.getValueAt(row, 1).toString();
                        abrirVentanaEdicion(codProyecto, nombreProyecto, row);
                    } else if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                        MostrarTorres mostrarTorres = new MostrarTorres(codProyecto);
                        if (mostrarTorres.existenTorres()) {
                            mostrarTorres.setVisible(true);
                        } else {
                            CrearTorres crearTorres = new CrearTorres(codProyecto);
                            crearTorres.setVisible(true);
                        }
                    }
                }
            }
        });
    }
}