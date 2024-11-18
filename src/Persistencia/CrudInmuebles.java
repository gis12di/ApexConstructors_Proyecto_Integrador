package Persistencia;

import Logica.Interfaz.Cruds;
import Logica.Inmuebles.Inmueble;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudInmuebles implements Cruds<Inmueble> {

    @Override
    public boolean guardar(Inmueble inmueble) {
        String checkSql = "SELECT COUNT(*) FROM torre WHERE ID = ?";
        String insertSql = "INSERT INTO Inmueble (id, numInmueble, tipoUnidad, valor, area, idTorre) VALUES (Seq_idInmueble.NextVal, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement checkStmt = con.prepareStatement(checkSql)) {

            // Verificar que el ID de la torre existe
            checkStmt.setString(1, inmueble.getCodTorre());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // Insertar el inmueble si la torre existe
                try (PreparedStatement stmt = con.prepareStatement(insertSql)) {
                    stmt.setString(1, inmueble.getNumInmueble());
                    stmt.setString(2, inmueble.getTipoUnidad());
                    stmt.setDouble(3, inmueble.getValorInmueble());
                    stmt.setDouble(4, inmueble.getArea());
                    stmt.setString(5, inmueble.getCodTorre());
                    stmt.executeUpdate();
                    return true;
                }
            } else {
                System.out.println("Error: ID de la torre no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizar(Inmueble inmueble) {
        String sql = "UPDATE Inmueble SET numInmueble = ?, tipoUnidad = ?, valor = ?, area = ?, idTorre = ? WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, inmueble.getNumInmueble());
            stmt.setString(2, inmueble.getTipoUnidad());
            stmt.setDouble(3, inmueble.getValorInmueble());
            stmt.setDouble(4, inmueble.getArea());
            stmt.setString(5, inmueble.getCodTorre());
            stmt.setString(6, inmueble.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM Inmueble WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Inmueble> obtener(String idTorre) {
        List<Inmueble> inmuebles = new ArrayList<>();
        String sql = "SELECT * FROM Inmueble WHERE idTorre = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idTorre);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Inmueble inmueble = new Inmueble();
                inmueble.setId(rs.getString("id"));
                inmueble.setNumInmueble(rs.getString("numInmueble"));
                inmueble.setTipoUnidad(rs.getString("tipoUnidad"));
                inmueble.setValorInmueble(rs.getDouble("valor"));
                inmueble.setArea(rs.getDouble("area"));
                inmueble.setCodTorre(rs.getString("idTorre"));
                inmuebles.add(inmueble);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inmuebles;
    }
}
