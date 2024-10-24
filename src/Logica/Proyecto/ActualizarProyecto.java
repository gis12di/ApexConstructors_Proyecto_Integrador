package Logica.Proyectos;

import Logica.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizarProyecto {
    public boolean actualizarProyecto(String codigoProyecto, String nuevoNombre) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean actualizado = false;

        try {
            conn = Conexion.getConnection();
            String sql = "UPDATE proyecto SET nombre = ? WHERE codigo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nuevoNombre);
            stmt.setInt(2, Integer.parseInt(codigoProyecto));

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                actualizado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        } finally {
            // Cerrar recursos
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return actualizado;
    }
}
