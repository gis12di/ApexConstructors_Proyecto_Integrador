import Logica.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarInmueble {
    public void eliminar(int id) {
        Connection con = Conexion.getConnection();
        String sql = "DELETE FROM Inmuebles WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Inmueble eliminado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
