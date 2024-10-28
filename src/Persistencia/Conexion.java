package Persistencia;// Define el paquete Persistencia

import java.sql.Connection; // Importa la clase Connection para manejar la conexión a la base de datos
import java.sql.DriverManager; // Importa la clase DriverManager para obtener conexiones a la base de datos
import java.sql.SQLException; // Importa la clase SQLException para manejar excepciones SQL

public class Conexion {// La clase Conexion proporciona métodos para establecer una conexión con la base de datos Oracle.
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
        return conn;// Devuelve la conexión o null si falló
    }

    public static void main(String[] args) {
        getConnection();//// Prueba la conexión a la base de datos
    }
}
