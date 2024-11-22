package Persistencia;

import Logica.Interfaz.Cruds;
import java.sql.*;
import Logica.Proyecto.Proyecto;
import java.util.ArrayList;
import java.util.List;

public class CrudProyectos implements Cruds<Proyecto> {

    // Método para obtener una lista de proyectos basada en un criterio (filtro)
    @Override
    public List<Proyecto> obtener(String criterio) {
        List<Proyecto> proyectos = new ArrayList<>();
        String sql = "{ ? = CALL obtenerProyectos() }"; // Cambiado a función que devuelve SYS_REFCURSOR

        try (Connection conn = Conexion.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            // Registrar el parámetro de salida como SYS_REFCURSOR
            stmt.registerOutParameter(1, Types.REF_CURSOR);

            // Ejecutar la función
            stmt.execute();

            // Obtener el ResultSet del cursor devuelto
            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    int codigo = rs.getInt("codigo");
                    String nombre = rs.getString("nombre");
                    Proyecto proyecto = new Proyecto(codigo, nombre);
                    proyectos.add(proyecto); // Agregar proyecto a la lista
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proyectos;
    }


    // Método para guardar un nuevo proyecto
    @Override
    public boolean guardar(Proyecto proyecto) {
        if (proyecto == null || proyecto.getNombre() == null || proyecto.getNombre().isEmpty()) {
            System.err.println("El nombre del proyecto no puede estar vacío.");
            return false;
        }

        String sql = "{CALL insertarProyecto(?)}";

        try (Connection conn = Conexion.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, proyecto.getNombre()); // Asigna el nombre del proyecto
            stmt.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un proyecto existente
    public boolean actualizar(Proyecto proyecto) {
        if (proyecto == null || proyecto.getCodigo() <= 0 || proyecto.getNombre() == null || proyecto.getNombre().isEmpty()) {
            System.err.println("El proyecto debe tener un código válido y un nombre no vacío.");
            return false;
        }

        String sql = "{ CALL actualizarProyecto(?, ?) }";

        try (Connection conn = Conexion.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, proyecto.getCodigo()); // Asigna el código del proyecto
            stmt.setString(2, proyecto.getNombre()); // Asigna el nuevo nombre
            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un proyecto basado en su código
    @Override
    public boolean eliminar(String codigoProyecto) {
        if (codigoProyecto == null || codigoProyecto.isEmpty()) {
            System.err.println("El código del proyecto no puede estar vacío.");
            return false;
        }

        String sql = "{ CALL eliminarProyecto(?) }";

        try (Connection conn = Conexion.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, codigoProyecto); // Asigna el código del proyecto
            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Método para obtener el código de un nuevo proyecto como String
    public String obtenerCodigoProyecto() {
        String codigoProyecto = null;
        String sql = "{ ? = CALL obtenerCodigoProyecto() }"; // Llamada a la función PL/SQL

        try (Connection conn = Conexion.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {
            // Registrar el parámetro de retorno de la función
            stmt.registerOutParameter(1, Types.NUMERIC); // Ajusta el tipo según sea necesario

            // Ejecutar la función
            stmt.execute();

            // Obtener el resultado
            int codigo = stmt.getInt(1);
            if (!stmt.wasNull()) { // Verificar si el valor no es nulo
                codigoProyecto = String.valueOf(codigo);
                System.out.println("Código de proyecto generado: " + codigoProyecto);
            } else {
                System.err.println("El código obtenido fue NULL.");
            }

        } catch (SQLException ex) {
            System.err.println("Error al ejecutar la función obtenerCodigoProyecto: " + ex.getMessage());
            ex.printStackTrace();
        }

        return codigoProyecto;
    }

}
