package Persistencia;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CrudUsuarios {

    // Método para obtener el rol del usuario según el nombre de usuario y la contraseña
    public String obtenerRolUsuario(String usuario, String contrasena) {
        String rol = null;
        String query = "SELECT rol FROM usuarios WHERE usuario = ? AND contrasena = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            // Establecer los parámetros de la consulta
            statement.setString(1, usuario);
            statement.setString(2, contrasena);

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Obtener el rol del resultado de la consulta
                rol = resultSet.getString("rol");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rol;
    }

   
}
