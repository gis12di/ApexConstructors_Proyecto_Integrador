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
        String sql = "SELECT * FROM proyecto";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Procesar los resultados
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                Proyecto proyecto = new Proyecto(codigo, nombre);
                proyectos.add(proyecto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proyectos;
    }

    // Método para guardar un nuevo proyecto
    public boolean guardarProyecto(String nombreProyecto) {
        String sql = "INSERT INTO proyecto (codigo, nombre) VALUES (seq_codProyecto.NEXTVAL, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreProyecto);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un proyecto existente
    public boolean actualizarProyecto(String codigoProyecto, String nuevoNombre) {
        String sql = "UPDATE proyecto SET nombre = ? WHERE codigo = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoNombre);
            stmt.setString(2, codigoProyecto);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un proyecto
    public boolean eliminarProyecto(String codigoProyecto) {
        String deleteTorresSQL = "DELETE FROM torre WHERE codproyecto = ?";
        String deleteProyectoSQL = "DELETE FROM proyecto WHERE codigo = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmtDeleteTorres = conn.prepareStatement(deleteTorresSQL);
             PreparedStatement stmtDeleteProyecto = conn.prepareStatement(deleteProyectoSQL)) {

            conn.setAutoCommit(false);  // Iniciar transacción

            // Eliminar registros dependientes en la tabla `torre`
            stmtDeleteTorres.setString(1, codigoProyecto);
            stmtDeleteTorres.executeUpdate();

            // Eliminar el proyecto
            stmtDeleteProyecto.setString(1, codigoProyecto);
            int rowsDeleted = stmtDeleteProyecto.executeUpdate();

            conn.commit();  // Confirmar transacción
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            try (Connection conn = Conexion.getConnection()) {
                if (conn != null) conn.rollback();  // Revertir cambios
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }
}
