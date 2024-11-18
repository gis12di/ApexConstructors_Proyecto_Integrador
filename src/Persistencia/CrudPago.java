// CrudPago.java
package Persistencia;

import Logica.Interfaz.Cruds;
import Logica.Pago.Pago;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudPago implements Cruds<Pago> {

    @Override
    public List<Pago> obtener(String criterio) {
        List<Pago> pagos = new ArrayList<>();
        String sql = "SELECT * FROM pago";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getString("idpago"));
                pago.setValorPago(rs.getDouble("valorpago"));
                pago.setFechaPago(rs.getDate("fechapago"));
                pago.setIdInmueble(rs.getString("idinmueble"));
                pago.setCedCliente(rs.getString("cedCliente"));
                pago.setCedAsesor(rs.getString("cedasesor"));
                pago.setIdVenta(rs.getString("idventa"));
                pagos.add(pago);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagos;
    }

    @Override
    public boolean guardar(Pago pago) {
        String sql = "INSERT INTO pago (idpago, valorpago, fechapago, idinmueble, cedCliente, cedasesor, idventa) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pago.getIdPago());
            stmt.setDouble(2, pago.getValorPago());
            stmt.setDate(3, new java.sql.Date(pago.getFechaPago().getTime()));
            stmt.setString(4, pago.getIdInmueble());
            stmt.setString(5, pago.getCedCliente());
            stmt.setString(6, pago.getCedAsesor());
            stmt.setString(7, pago.getIdVenta());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizar(Pago pago) {
        String sql = "UPDATE pago SET estado_pago = ? WHERE idpago = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pago.getEstadoPago());
            stmt.setString(2, pago.getIdPago());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(String idPago) {
        String sql = "DELETE FROM pago WHERE idpago = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPago);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
