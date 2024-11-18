package Persistencia; // Define el paquete Persistencia

import Logica.Interfaz.Cruds;

import Logica.Torre.Torre; // Importa la clase Torre
import java.sql.Connection; // Importa la clase Connection para manejar la conexión a la base de datos
import java.sql.PreparedStatement; // Importa la clase PreparedStatement para ejecutar comandos SQL precompilados
import java.sql.ResultSet; // Importa la clase ResultSet para manejar los resultados de las consultas SQL
import java.sql.SQLException; // Importa la clase SQLException para manejar excepciones SQL
import java.util.ArrayList; // Importa la clase ArrayList para manejar listas dinámicas
import java.util.List; // Importa la clase List para manejar colecciones de objetos

public class CrudTorres implements Cruds<Torre> {//La clase CrudTorres proporciona métodos para realizar operaciones CRUD en torres.
    @Override
    public boolean actualizar(Torre torre) {
        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement("UPDATE torre SET numTorre = ?, numPisos = ?, codProyecto = ? WHERE id = ?")) {

            stmt.setString(1, torre.getNumTorre());
            stmt.setString(2, torre.getNumPisos());
            stmt.setString(3, torre.getCodProyecto());
            stmt.setString(4, torre.getId());
            int rowsUpdated = stmt.executeUpdate(); // Verifica cuántas filas se actualizaron
            return rowsUpdated > 0; // Devuelve true si al menos una fila fue actualizada

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Devuelve false si hubo un error
        }
    }


    @Override
    public boolean eliminar(String id) {
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement("DELETE FROM torre WHERE id = ?")) {

            stmt.setString(1, id);
            int rowsDeleted = stmt.executeUpdate(); // Verifica cuántas filas se eliminaron
            return rowsDeleted > 0; // Devuelve true si al menos una fila fue eliminada

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Devuelve false si hubo un error
        }
    }


    @Override
    public boolean guardar(Torre torre) {
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement("INSERT INTO torre (ID, numTorre, numPisos, codProyecto) VALUES (seq_idTorre.NEXTVAL, ?, ?, ?)")) {

            stmt.setString(1, torre.getNumTorre());
            stmt.setString(2, torre.getNumPisos());
            stmt.setString(3, torre.getCodProyecto());
            int rowsInserted = stmt.executeUpdate(); // Verifica cuántas filas se insertaron
            return rowsInserted > 0; // Devuelve true si al menos una fila fue insertada

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Devuelve false si hubo un error
        }
    }


    @Override
    public List<Torre> obtener(String codProyecto) {
        List<Torre> torres = new ArrayList<>();
        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement("SELECT * FROM torre WHERE codProyecto = ?")) {

            stmt.setString(1, codProyecto);
            try (ResultSet rs = stmt.executeQuery()) { // Usa try-with-resources para ResultSet
                while (rs.next()) {
                    Torre torre = new Torre();
                    torre.setId(rs.getString("id"));
                    torre.setNumTorre(rs.getString("numTorre"));
                    torre.setNumPisos(rs.getString("numPisos"));
                    torre.setCodProyecto(rs.getString("codProyecto"));
                    torres.add(torre);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return torres;
    }


    public boolean existeCodProyecto(String codProyecto) {//Verifica si un código de proyecto existe en la base de datos.
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM proyecto WHERE codigo = ?")) {
             
            stmt.setString(1, codProyecto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;// Retorna false si el código del proyecto no existe
    }

}
