import Logica.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  // Importar ResultSet
import java.sql.SQLException;

public class GuardarTorre {
    public void guardar(Torre torre) {
        Connection con = Conexion.getConnection();
        
        // Verificar si el codProyecto existe
        String checkSql = "SELECT COUNT(*) FROM proyecto WHERE codigo = ?";
        // Si existe, insertar la torre
        String insertSql = "INSERT INTO torre (ID, numTorre, numPisos, codProyecto) VALUES (seq_idTorre.NEXTVAL, ?, ?, ?)";

        try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
            checkStmt.setString(1, torre.getCodProyecto());
            // Ejecutamos la consulta y almacenamos el resultado en un ResultSet
            ResultSet rs = checkStmt.executeQuery();
            
            // Revisamos si existe el proyecto
            if (rs.next() && rs.getInt(1) > 0) {
                // El codProyecto existe, procedemos con la inserción
                try (PreparedStatement insertStmt = con.prepareStatement(insertSql)) {
                    insertStmt.setString(1, torre.getNumTorre());
                    insertStmt.setString(2, torre.getNumPisos());
                    insertStmt.setString(3, torre.getCodProyecto());
                    insertStmt.executeUpdate();
                    System.out.println("Torre guardada correctamente.");
                }
            } else {
                // El codProyecto no existe, se maneja el error
                System.out.println("Error: El codProyecto no existe.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
