import Logica.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObtenerTorre {
    public List<Torre> obtenerPorCodProyecto(String codProyecto) {
        Connection con = Conexion.getConnection();
        String sql = "SELECT * FROM torre WHERE codProyecto = ?";
        List<Torre> torres = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codProyecto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Torre torre = new Torre();
                torre.setId(rs.getString("id"));
                torre.setNumTorre(rs.getString("numTorre"));
                torre.setNumPisos(rs.getString("numPisos"));
                torre.setCodProyecto(rs.getString("codProyecto"));
                torres.add(torre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return torres;
    }
}
