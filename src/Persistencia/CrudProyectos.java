import java.sql.*;
import Logica.Proyecto.Proyecto;
import Persistencia.Conexion;
import java.util.ArrayList;
import java.util.List;

public class CrudProyectos {
    
    // Método para obtener todos los proyectos
    public List<Proyecto> obtenerProyectos() {
        List<Proyecto> proyectos = new ArrayList<>();
        String sql = "{ CALL obtenerProyectos(?) }"; // Llama al procedimiento obtenerProyectos

        try (Connection conn = Conexion.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, Types.REF_CURSOR); // Asigna el cursor de salida
            stmt.exbloc
                    ecute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) { // Itera sobre el ResultSet
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

        return proyectos; // Devuelve la lista completa de proyectos
    }

    // Método para guardar un nuevo proyecto
    public boolean guardarProyecto(String nombreProyecto) {
        String sql = "{CALL insertarProyecto(?)}";

        try (Connection conn = Conexion.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, nombreProyecto);
            stmt.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un proyecto existente
    public boolean actualizarProyecto(String codigoProyecto, String nuevoNombre) {
        String sql = "{ CALL actualizarProyecto(?, ?) }";

        try (Connection conn = Conexion.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, codigoProyecto);
            stmt.setString(2, nuevoNombre);
            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un proyecto
    public boolean eliminarProyecto(String codigoProyecto) {
        String sql = "{ CALL eliminarProyecto(?) }";

        try (Connection conn = Conexion.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, codigoProyecto);
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

        try (Connection conn = Conexion.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, Types.VARCHAR); // Cambiado a VARCHAR para devolver como String
            stmt.execute();

            codigoProyecto = stmt.getString(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return codigoProyecto;
    }
}
