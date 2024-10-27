package Persistencia;

import Logica.Proyecto.Proyecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudProyectos {

    // Método para obtener la lista de proyectos
    public List<Proyecto> obtenerProyectos() {
        List<Proyecto> proyectos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            String sql = "SELECT * FROM proyecto";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Procesar los resultados
            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setCodigo(rs.getInt("codigo"));
                proyecto.setNombre(rs.getString("nombre"));
                proyectos.add(proyecto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar los recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return proyectos;
    }

    // Método para guardar un nuevo proyecto
    public boolean guardarProyecto(String nombreProyecto) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            String sql = "INSERT INTO proyecto (codigo, nombre) VALUES (seq_codProyecto.NEXTVAL, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreProyecto);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            // Cerrar los recursos
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para actualizar un proyecto existente
    public boolean actualizarProyecto(String codigoProyecto, String nuevoNombre) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            String sql = "UPDATE proyecto SET nombre = ? WHERE codigo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nuevoNombre);
            stmt.setInt(2, Integer.parseInt(codigoProyecto));
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            // Cerrar los recursos
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para eliminar un proyecto
    public boolean eliminarProyecto(String codigoProyecto) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            String sql = "DELETE FROM proyecto WHERE codigo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(codigoProyecto));
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            // Cerrar los recursos
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
