
import Logica.Inmuebles.Inmueble; // Importa la clase Inmueble
import Persistencia.Conexion; // Importa la clase Conexion para obtener conexiones a la base de datos
import java.sql.Connection; // Importa la clase Connection para manejar la conexión a la base de datos
import java.sql.PreparedStatement; // Importa la clase PreparedStatement para ejecutar comandos SQL precompilados
import java.sql.ResultSet; // Importa la clase ResultSet para manejar los resultados de las consultas SQL
import java.sql.SQLException; // Importa la clase SQLException para manejar excepciones SQL
import java.util.ArrayList; // Importa la clase ArrayList para manejar listas dinámicas
import java.util.List; // Importa la clase List para manejar colecciones de objetos

public class CrudInmuebles {

    // Método para guardar un inmueble en la base de datos
    public boolean guardar(Inmueble inmueble) {
        Connection con = Conexion.getConnection();// Obtiene una conexión a la base de datos
        String checkSql = "SELECT COUNT(*) FROM torre WHERE ID = ?";
        String insertSql = "INSERT INTO Inmueble (id, numInmueble, tipoUnidad, valor, area, idTorre) VALUES (Seq_idInmueble.NextVal, ?, ?, ?, ?, ?)";

        try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
            checkStmt.setString(1, inmueble.getCodTorre());// Establece el código de la torre en la consulta
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {// Verifica si la torre existe
                try (PreparedStatement stmt = con.prepareStatement(insertSql)) {
                    stmt.setString(1, inmueble.getNumInmueble());
                    stmt.setString(2, inmueble.getTipoUnidad());
                    stmt.setDouble(3, inmueble.getValorInmueble());
                    stmt.setDouble(4, inmueble.getArea());
                    stmt.setString(5, inmueble.getCodTorre());
                    stmt.executeUpdate();
                    return true;// Retorna true si la inserción es exitosa
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para actualizar un inmueble
    public boolean actualizar(Inmueble inmueble) {
        Connection con = Conexion.getConnection();
        String sql = "UPDATE Inmueble SET numInmueble = ?, tipoUnidad = ?, valor = ?, area = ?, idTorre = ? WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, inmueble.getNumInmueble());
            stmt.setString(2, inmueble.getTipoUnidad());
            stmt.setDouble(3, inmueble.getValorInmueble());
            stmt.setDouble(4, inmueble.getArea());
            stmt.setString(5, inmueble.getCodTorre());
            stmt.setString(6, inmueble.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para eliminar un inmueble
    public boolean eliminar(String id) {
        Connection con = Conexion.getConnection();
        String sql = "DELETE FROM Inmueble WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para obtener inmuebles por ID de torre
    public List<Inmueble> obtenerPorIdTorre(String idTorre) {
        List<Inmueble> inmuebles = new ArrayList<>();
        Connection con = Conexion.getConnection();
        String sql = "SELECT * FROM Inmueble WHERE idTorre = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, idTorre);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Inmueble inmueble = new Inmueble();
                inmueble.setId(rs.getString("id"));
                inmueble.setNumInmueble(rs.getString("numInmueble"));
                inmueble.setTipoUnidad(rs.getString("tipoUnidad"));
                inmueble.setValorInmueble(rs.getDouble("valor"));
                inmueble.setArea(rs.getDouble("area"));
                inmueble.setCodTorre(rs.getString("idTorre"));
                inmuebles.add(inmueble);// Agrega el inmueble a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inmuebles;// Retorna la lista de inmuebles
    }
}
