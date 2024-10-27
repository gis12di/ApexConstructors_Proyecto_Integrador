package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";  // Cambia si es necesario
    private static final String USER = "ejemplocrud";  // Reemplaza con tu usuario de Oracle
    private static final String PASS = "ejemplocrud";  // Reemplaza con tu contraseña de Oracle 

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Registrar el driver JDBC manualmente
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Intentar la conexión
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión exitosa.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en la conexión.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver JDBC no encontrado.");
        }
        return conn;
    }

    public static void main(String[] args) {
        getConnection();
    }
}
