
import Logica.Interfaz.Cruds;
import java.sql.*;
import Logica.Proyecto.Proyecto;
import Persistencia.Conexion;
import java.util.ArrayList;
import java.util.List;

public class CrudProyectos implements Cruds<Proyecto> {

    // Método para obtener una lista de proyectos basada en un criterio (filtro)
    @Override
    public List<Proyecto> obtener(String criterio) {
        List<Proyecto> proyectos = new ArrayList<>();
        String sql = "{ CALL obtenerProyectos(?) }"; // Procedimiento almacenado

        try (Connection conn = Conexion.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, criterio); // Asigna el criterio (puede ser un filtro, como el nombre del proyecto)
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    int codigo = rs.getInt("codigo");
                    String nombre = rs.getString("nombre");
                    Proyecto proyecto = new Proyecto(codigo, nombre);
                    proyectos.add(proyecto); // Agrega cada proyecto a la lista
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proyectos; // Devuelve la lista filtrada de proyectos
    }

    // Método para guardar un nuevo proyecto
    @Override
    public boolean guardar(Proyecto proyecto) {
        if (proyecto == null || proyecto.getNombre() == null || proyecto.getNombre().isEmpty()) {
            System.err.println("El nombre del proyecto no puede estar vacío.");
            return false;
        }

        String sql = "{CALL insertarProyecto(?)}";

        try (Connection conn = Conexion.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, proyecto.getNombre()); // Asigna el nombre del proyecto
            stmt.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un proyecto existente
    @Override
    public boolean actualizar(Proyecto proyecto) {
        if (proyecto == null || proyecto.getCodigo() <= 0 || proyecto.getNombre() == null || proyecto.getNombre().isEmpty()) {
            System.err.println("El proyecto debe tener un código válido y un nombre no vacío.");
            return false;
        }

        String sql = "{ CALL actualizarProyecto(?, ?) }";

        try (Connection conn = Conexion.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, proyecto.getCodigo()); // Asigna el código del proyecto
            stmt.setString(2, proyecto.getNombre()); // Asigna el nuevo nombre
            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un proyecto basado en su código
    @Override
    public boolean eliminar(String codigoProyecto) {
        if (codigoProyecto == null || codigoProyecto.isEmpty()) {
            System.err.println("El código del proyecto no puede estar vacío.");
            return false;
        }

        String sql = "{ CALL eliminarProyecto(?) }";

        try (Connection conn = Conexion.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, codigoProyecto); // Asigna el código del proyecto
            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Método para obtener el código de un nuevo proyecto como String
    public String obtenerCodigoProyecto() {
        String codigoProyecto = null;
        String sql = "{ ? = CALL obtenerCodigoProyecto() }";

        try (Connection conn = Conexion.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, Types.VARCHAR); // Cambiado a VARCHAR para devolver como String
            stmt.execute();

            codigoProyecto = stmt.getString(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return codigoProyecto;
    }
}
