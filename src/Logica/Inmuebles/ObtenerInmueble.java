import Logica.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObtenerInmueble {
    public Inmueble obtenerPorId(int id) {
        Connection con = Conexion.getConnection();
        String sql = "SELECT * FROM Inmuebles WHERE id = ?";
        Inmueble inmueble = null;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                inmueble = new Inmueble();
                inmueble.setId(rs.getInt("id"));
                inmueble.setNumInmueble(rs.getInt("numInmueble"));
                inmueble.setTipoUnidad(rs.getString("tipoUnidad"));
                inmueble.setValorInmueble(rs.getDouble("valorInmueble"));
                inmueble.setArea(rs.getDouble("area"));
                inmueble.setCodTorre(rs.getInt("codTorre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inmueble;
    }
}
