import Logica.Proyecto.Proyecto; // Importa la clase Proyecto
import javax.swing.*; // Importa las clases de javax.swing para la interfaz gráfica
import javax.swing.table.DefaultTableModel; // Importa la clase DefaultTableModel para manejar el modelo de la tabla
import java.awt.event.ActionListener; // Importa la clase ActionListener para manejar eventos de acción
import java.util.List; // Importa la clase List para manejar colecciones de objetos

public class AdminController {//La clase AdminController gestiona las acciones de la interfaz de administración.
    private final Main_Admin mainAdmin;// Instancia de la ventana principal de administración
    private final GestionProyectos gestionProyectos;// Instancia de la clase GestionProyectos para manejar las operaciones de proyectos
    private JTable table;// Tabla de proyectos

    public AdminController(Main_Admin mainAdmin) {//Constructor que inicializa las instancias de Main_Admin y GestionProyectos.
        this.mainAdmin = mainAdmin;
        this.gestionProyectos = new GestionProyectos();
    }

    public ActionListener getActionNuevoProyecto() {//Obtiene el ActionListener para crear un nuevo proyecto
        return e -> {
            CrearProyecto crearProyecto = new CrearProyecto(mainAdmin);
            crearProyecto.setTitle("Crear Proyecto");
            crearProyecto.setVisible(true);

            crearProyecto.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    // Aquí se llama a actualizarTablaProyectos después de cerrar la ventana CrearProyecto
                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    actualizarTablaProyectos(tableModel);                  
                }
            });
        };
    }


    public void actualizarTablaProyectos(DefaultTableModel tableModel) {//Actualiza la tabla de proyectos con los datos más recientes
        List<Proyecto> proyectos = gestionProyectos.obtenerProyectos();

        tableModel.setRowCount(0); // Limpiar la tabla
        for (Proyecto proy : proyectos) {
            Object[] rowData = {proy.getCodigo(), proy.getNombre()};
            tableModel.addRow(rowData);
        }
    }

    public void abrirVentanaEdicion(String idProyecto, String nombreProyecto, int row) {//Abre la ventana de edición para un proyecto específico
        EditarProyecto editarProyecto = new EditarProyecto(idProyecto, nombreProyecto, row, table);
        editarProyecto.setVisible(true);
    }

    public void configurarDobleClicTabla(JTable table) {//Configura el comportamiento del doble clic en la tabla de proyectos
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