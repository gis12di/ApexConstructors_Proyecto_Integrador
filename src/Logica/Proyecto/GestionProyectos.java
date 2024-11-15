import Logica.Proyecto.Proyecto;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.List;

public class GestionProyectos {
    private final CrudProyectos crudProyectos;

    public GestionProyectos() {
        this.crudProyectos = new CrudProyectos();
    }

    // Método para obtener todos los proyectos
    public List<Proyecto> obtenerProyectos() {
        List<Proyecto> proyectos = crudProyectos.obtenerProyectos(); // Obtiene la lista completa

        if (proyectos == null || proyectos.isEmpty()) {
            System.out.println("No se encontraron proyectos.");
        }

        return proyectos; // Devuelve la lista completa de proyectos
    }


    public boolean guardarProyecto(String nombreProyecto, JFrame frame) {
        if (nombreProyecto == null || nombreProyecto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El nombre del proyecto no puede estar vacío.");
            return false;
        }
        return crudProyectos.guardarProyecto(nombreProyecto);
    }

    public String obtenerCodigoProyecto() {
        return crudProyectos.obtenerCodigoProyecto();
    }

    public boolean actualizarProyecto(String codigoProyecto, String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del proyecto no puede estar vacío.");
        }
        return crudProyectos.actualizarProyecto(codigoProyecto, nuevoNombre);
    }

    public boolean eliminarProyecto(String codigoProyecto) {
        return crudProyectos.eliminarProyecto(codigoProyecto);
    }
}
