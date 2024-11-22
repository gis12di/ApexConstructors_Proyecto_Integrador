import Logica.Proyecto.GestionProyectos;
import Logica.Proyecto.Proyecto;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CrearProyectoController {
    private GestionProyectos gestionProyectos;
    private Main_Admin adminFrame;

    public CrearProyectoController(Main_Admin adminFrame) {
        this.adminFrame = adminFrame;
        this.gestionProyectos = new GestionProyectos();
    }

    // Método para guardar el proyecto y obtener su código
    public String guardarProyecto(String nombreProyecto, JFrame frame) {
        if (nombreProyecto == null || nombreProyecto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El nombre del proyecto no puede estar vacío.");
            return null;
        }

        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(nombreProyecto);

        // Guardar el proyecto utilizando GestionProyectos
        boolean proyectoGuardado = gestionProyectos.guardarProyecto(proyecto, frame);

        if (proyectoGuardado) {
            // Si el proyecto se guardó correctamente, obtenemos el código
            String codigoProyecto = gestionProyectos.obtenerCodigoProyecto();
            return codigoProyecto;
        } else {
            JOptionPane.showMessageDialog(frame, "Error al guardar el proyecto.");
            return null;
        }
    }
}
