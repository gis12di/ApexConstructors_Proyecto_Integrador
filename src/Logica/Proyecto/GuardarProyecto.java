package Logica.Proyectos;

import Logica.Conexion;
import Logica.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GuardarProyecto {

    public GuardarProyecto() {
        // Constructor vacío
    }

    public boolean guardarProyecto(String nombreProyecto) {
        if (nombreProyecto == null || nombreProyecto.isEmpty()) {
            throw new IllegalArgumentException("El nombre del proyecto no puede estar vacío.");
        }

        Connection conn = null;
        try {
            // Obtener la conexión desde la clase Conexion
            conn = Conexion.getConnection();

            if (conn != null) {
                // Consulta SQL que inserta el ID generado por la secuencia y el nombre
                String sql = "INSERT INTO proyecto (codigo, nombre) VALUES (seq_codProyecto.NEXTVAL, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, nombreProyecto);  // Solo se pasa el nombre como parámetro
                stmt.executeUpdate();
                return true;
            } else {
                System.out.println("Error al obtener la conexión.");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            // Cerrar la conexión si fue abierta
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}