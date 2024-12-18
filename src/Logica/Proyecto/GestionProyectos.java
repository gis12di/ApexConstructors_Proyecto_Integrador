package Logica.Proyecto;

import Logica.FactoryMethod.CreadorCrudProyectos;
import Logica.Interfaz.Cruds;
import Persistencia.CrudProyectos;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GestionProyectos {
    
    private final Cruds<Proyecto> crudProyectos;

    public GestionProyectos() {
        CreadorCrudProyectos creador = new CreadorCrudProyectos();
        this.crudProyectos = creador.crearCrud();
    }

    // Método para obtener proyectos según un criterio
    public List<Proyecto> obtenerProyectos(String criterio, JFrame frame) {
        List<Proyecto> proyectos = crudProyectos.obtener(criterio);

        if (proyectos == null || proyectos.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No se encontraron proyectos con el criterio especificado.");
            return new ArrayList<>();
        }

        return proyectos;
    }

    // Método para guardar un proyecto
    public boolean guardarProyecto(Proyecto proyecto, JFrame frame) {
        if (proyecto == null || proyecto.getNombre() == null || proyecto.getNombre().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El nombre del proyecto no puede estar vacío.");
            return false;
        }

        boolean resultado = crudProyectos.guardar(proyecto);
        if (!resultado) {
            JOptionPane.showMessageDialog(frame, "No se pudo guardar el proyecto. Verifique los datos.");
        }
        return resultado;
    }

    // Método para actualizar un proyecto existente
    public boolean actualizarProyecto(Proyecto proyecto, JFrame frame) {
        if (proyecto == null || proyecto.getCodigo() <= 0 || proyecto.getNombre() == null || proyecto.getNombre().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El proyecto debe tener un código válido y un nombre no vacío.");
            return false;
        }

        boolean resultado = crudProyectos.actualizar(proyecto);
        if (!resultado) {
            JOptionPane.showMessageDialog(frame, "No se pudo actualizar el proyecto. Verifique los datos.");
        }
        return resultado;
    }

    // Método para eliminar un proyecto según su código
    public boolean eliminarProyecto(String codigoProyecto, JFrame frame) {
        if (codigoProyecto == null || codigoProyecto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El código del proyecto no puede estar vacío.");
            return false;
        }

        boolean resultado = crudProyectos.eliminar(codigoProyecto);
        if (!resultado) {
            JOptionPane.showMessageDialog(frame, "No se pudo eliminar el proyecto. Verifique dependencias relacionadas.");
        }
        return resultado;
    }
    
    public String obtenerCodigoProyecto() {
    if (crudProyectos instanceof CrudProyectos) {
        return ((CrudProyectos) crudProyectos).obtenerCodigoProyecto();
    } else {
        throw new UnsupportedOperationException("El método obtenerCodigoProyecto no está disponible.");
    }
}


}
