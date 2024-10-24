import Logica.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizarTorre {
    public void actualizar(Torre torre) {
        Connection con = Conexion.getConnection();
        String sql = "UPDATE torre SET numTorre = ?, numPisos = ?, codProyecto = ? WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, torre.getNumTorre());
            stmt.setString(2, torre.getNumPisos());
            stmt.setString(3, torre.getCodProyecto());
            stmt.setString(4, torre.getId());
            stmt.executeUpdate();
            System.out.println("Torre actualizada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
