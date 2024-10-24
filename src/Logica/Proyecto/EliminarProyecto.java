package Logica.Proyectos;

import Logica.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarProyecto {
    public boolean eliminarProyecto(String codigoProyecto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean eliminado = false;

        try {
            conn = Conexion.getConnection();
            String sql = "DELETE FROM proyecto WHERE codigo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(codigoProyecto));

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                eliminado = true;
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

        return eliminado;
    }
}
