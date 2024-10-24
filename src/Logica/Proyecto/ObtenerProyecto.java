package Logica.Proyectos;


import Logica.Conexion;
import Logica.Proyectos.Proyecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObtenerProyecto {
    public List<Proyecto> ObtenerProyectos(){
        List<Proyecto> proyectos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obtén la conexión
            conn = Conexion.getConnection();

            // Prepara la consulta SQL para obtener todos los empleados
            String sql = "SELECT * FROM proyecto";
            stmt = conn.prepareStatement(sql);

            // Ejecuta la consulta
            rs = stmt.executeQuery();

            // Recorre el ResultSet y crea objetos Empleado
            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setCodigo(rs.getInt("codigo"));
                proyecto.setNombre(rs.getString("nombre"));


                // Agrega el empleado a la lista
                proyectos.add(proyecto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener los proyectos.");
        } finally {
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
}
