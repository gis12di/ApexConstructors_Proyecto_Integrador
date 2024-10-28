
import Logica.Proyecto.Proyecto; // Importa la clase Proyecto
import java.sql.Connection; // Importa la clase Connection para manejar la conexión a la base de datos
import java.sql.PreparedStatement; // Importa la clase PreparedStatement para ejecutar comandos SQL precompilados
import java.sql.ResultSet; // Importa la clase ResultSet para manejar los resultados de las consultas SQL
import java.sql.SQLException; // Importa la clase SQLException para manejar excepciones SQL
import java.util.List; // Importa la clase List para manejar colecciones de objetos
import javax.swing.JFrame; // Importa la clase JFrame para manejar ventanas de la interfaz gráfica de usuario
import javax.swing.JOptionPane; // Importa la clase JOptionPane para mostrar cuadros de diálogo
import Persistencia.Conexion; // Importa la clase Conexion para obtener conexiones a la base de datos
import Persistencia.CrudProyectos; // Importa la clase CrudProyectos para realizar operaciones CRUD en los proyectos


public class GestionProyectos {
    private final CrudProyectos crudProyectos;// Instancia de CrudProyectos para manejar operaciones CRUD

    public GestionProyectos() {
        this.crudProyectos = new CrudProyectos();
    }

    public List<Proyecto> obtenerProyectos() {crudProyectos.obtenerProyectos(); // Obtiene la lista de proyectos desde la base de datos
        return crudProyectos.obtenerProyectos();
    }

    public boolean guardarProyecto(String nombreProyecto, JFrame frame) {
        if (nombreProyecto == null || nombreProyecto.isEmpty()) {// Verifica que el nombre del proyecto no esté vacío
            JOptionPane.showMessageDialog(frame, "El nombre del proyecto no puede estar vacío.");
            return false;
        }

        Connection conn = null;// Declara la variable de conexión
        try {
            conn = Conexion.getConnection();// Obtiene una conexión a la base de datos
            if (conn != null) {
                String sql = "INSERT INTO proyecto (codigo, nombre) VALUES (seq_codProyecto.NEXTVAL, ?)";// Inserta un nuevo proyecto en la base de datos
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, nombreProyecto);
                stmt.executeUpdate();

                return true;
            } else {
                System.out.println("Error al obtener la conexión.");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();// Maneja excepciones SQL
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();// Cierra la conexión
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
                // Ejecutar NEXTVAL para inicializar la secuencia en la sesión
                String initSeqQuery = "SELECT seq_codProyecto.NEXTVAL FROM dual";
                PreparedStatement initStmt = conn.prepareStatement(initSeqQuery);
                initStmt.executeQuery(); // Ejecuta y descarta el resultado
                initStmt.close();

                // Ahora podemos obtener CURRVAL
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
        return crudProyectos.eliminarProyecto(codigoProyecto);// Elimina el proyecto de la base de datos
    }
}
