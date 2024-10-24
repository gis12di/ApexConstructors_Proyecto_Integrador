import Logica.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizarInmueble {
    public void actualizar(Inmueble inmueble) {
        Connection con = Conexion.getConnection();
        String sql = "UPDATE Inmuebles SET numInmueble = ?, tipoUnidad = ?, valorInmueble = ?, area = ?, codTorre = ? WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, inmueble.getNumInmueble());
            stmt.setString(2, inmueble.getTipoUnidad());
            stmt.setDouble(3, inmueble.getValorInmueble());
            stmt.setDouble(4, inmueble.getArea());
            stmt.setInt(5, inmueble.getCodTorre());
            stmt.setInt(6, inmueble.getId());
            stmt.executeUpdate();
            System.out.println("Inmueble actualizado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
