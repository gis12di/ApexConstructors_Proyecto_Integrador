package Persistencia;

// CrudTorres.java
import Logica.Torre.Torre;
import Persistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudTorres {
    public void actualizar(Torre torre) {
        Connection con = Conexion.getConnection();
        String sql = "UPDATE torre SET numTorre = ?, numPisos = ?, codProyecto = ? WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
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
        Connection con = Conexion.getConnection();
        String sql = "DELETE FROM torre WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardar(Torre torre) {
        Connection con = Conexion.getConnection();
        String insertSql = "INSERT INTO torre (ID, numTorre, numPisos, codProyecto) VALUES (seq_idTorre.NEXTVAL, ?, ?, ?)";

        try (PreparedStatement insertStmt = con.prepareStatement(insertSql)) {
            insertStmt.setString(1, torre.getNumTorre());
            insertStmt.setString(2, torre.getNumPisos());
            insertStmt.setString(3, torre.getCodProyecto());
            insertStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Torre> obtenerPorCodProyecto(String codProyecto) {
        Connection con = Conexion.getConnection();
        String sql = "SELECT * FROM torre WHERE codProyecto = ?";
        List<Torre> torres = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
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
        Connection con = Conexion.getConnection();
        String sql = "SELECT COUNT(*) FROM proyecto WHERE codigo = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
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
