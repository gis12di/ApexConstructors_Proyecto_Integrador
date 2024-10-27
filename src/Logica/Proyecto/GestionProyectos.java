
import Logica.Proyecto.Proyecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Persistencia.Conexion;
import Persistencia.CrudProyectos;


public class GestionProyectos {
    private final CrudProyectos crudProyectos;

    public GestionProyectos() {
        this.crudProyectos = new CrudProyectos();
    }

    public List<Proyecto> obtenerProyectos() {
        return crudProyectos.obtenerProyectos();
    }

    public boolean guardarProyecto(String nombreProyecto, JFrame frame) {
        if (nombreProyecto == null || nombreProyecto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El nombre del proyecto no puede estar vacío.");
            return false;
        }

        Connection conn = null;
        try {
            conn = Conexion.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO proyecto (codigo, nombre) VALUES (seq_codProyecto.NEXTVAL, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, nombreProyecto);
                stmt.executeUpdate();

                return true;
            } else {
                System.out.println("Error al obtener la conexión.");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String obtenerCodigoProyecto() {
        String codigoProyecto = null;
        Connection conn = null;
        try {
            conn = Conexion.getConnection();
            if (conn != null) {
                String query = "SELECT seq_codProyecto.CURRVAL AS codigo FROM dual";
                PreparedStatement stmtCodigo = conn.prepareStatement(query);
                ResultSet rs = stmtCodigo.executeQuery();
                if (rs.next()) {
                    codigoProyecto = rs.getString("codigo");
                }
                rs.close();
                stmtCodigo.close();
            } else {
                System.out.println("Error al obtener la conexión.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return codigoProyecto;
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
