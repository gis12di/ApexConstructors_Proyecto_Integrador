// CrudTorres.java
package Persistencia;

import Logica.Torre.Torre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudTorres {
    public void actualizar(Torre torre) {
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement("UPDATE torre SET numTorre = ?, numPisos = ?, codProyecto = ? WHERE id = ?")) {
             
            stmt.setString(1, torre.getNumTorre());
            stmt.setString(2, torre.getNumPisos());
            stmt.setString(3, torre.getCodProyecto());
            stmt.setString(4, torre.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement("DELETE FROM torre WHERE id = ?")) {
             
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardar(Torre torre) {
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement("INSERT INTO torre (ID, numTorre, numPisos, codProyecto) VALUES (seq_idTorre.NEXTVAL, ?, ?, ?)")) {
             
            stmt.setString(1, torre.getNumTorre());
            stmt.setString(2, torre.getNumPisos());
            stmt.setString(3, torre.getCodProyecto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Torre> obtenerPorCodProyecto(String codProyecto) {
        List<Torre> torres = new ArrayList<>();
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM torre WHERE codProyecto = ?")) {
             
            stmt.setString(1, codProyecto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Torre torre = new Torre();
                torre.setId(rs.getString("id"));
                torre.setNumTorre(rs.getString("numTorre"));
                torre.setNumPisos(rs.getString("numPisos"));
                torre.setCodProyecto(rs.getString("codProyecto"));
                torres.add(torre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return torres;
    }

    public boolean existeCodProyecto(String codProyecto) {
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
        return false;
    }
}
