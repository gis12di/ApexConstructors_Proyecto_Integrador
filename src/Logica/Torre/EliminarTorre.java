import Logica.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarTorre {
    public void eliminar(int id) {
        Connection con = Conexion.getConnection();
        String sql = "DELETE FROM torre WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Torre eliminada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
