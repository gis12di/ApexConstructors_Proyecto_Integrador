import Logica.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GuardarInmueble {
    public void guardar(Inmueble inmueble) {
        Connection con = Conexion.getConnection();
        String sql = "INSERT INTO Inmuebles (numInmueble, tipoUnidad, valorInmueble, area, codTorre) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, inmueble.getNumInmueble());
            stmt.setString(2, inmueble.getTipoUnidad());
            stmt.setDouble(3, inmueble.getValorInmueble());
            stmt.setDouble(4, inmueble.getArea());
            stmt.setInt(5, inmueble.getCodTorre());
            stmt.executeUpdate();
            System.out.println("Inmueble guardado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
