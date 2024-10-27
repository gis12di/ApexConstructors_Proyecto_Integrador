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
        PreparedStatement stmtDeleteTorres = null;
        PreparedStatement stmtDeleteProyecto = null;

        try {
            conn = Conexion.getConnection();
            conn.setAutoCommit(false);  // Iniciar una transacción

            // Eliminar registros dependientes en la tabla `torres` primero
            String deleteTorresSQL = "DELETE FROM torre WHERE codproyecto = ?";
            stmtDeleteTorres = conn.prepareStatement(deleteTorresSQL);
            stmtDeleteTorres.setInt(1, Integer.parseInt(codigoProyecto));
            stmtDeleteTorres.executeUpdate();

            // Ahora eliminar el proyecto
            String deleteProyectoSQL = "DELETE FROM proyecto WHERE codigo = ?";
            stmtDeleteProyecto = conn.prepareStatement(deleteProyectoSQL);
            stmtDeleteProyecto.setInt(1, Integer.parseInt(codigoProyecto));
            int rowsDeleted = stmtDeleteProyecto.executeUpdate();

            conn.commit();  // Confirmar la transacción
            return rowsDeleted > 0;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();  // Revertir cambios en caso de error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;

        } finally {
            // Cerrar los recursos
            try {
                if (stmtDeleteTorres != null) stmtDeleteTorres.close();
                if (stmtDeleteProyecto != null) stmtDeleteProyecto.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
