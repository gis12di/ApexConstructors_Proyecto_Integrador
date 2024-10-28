package Persistencia;

import Logica.Pago.Pago;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudPago {

    public List<Pago> obtenerPagos() {
        List<Pago> pagos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            String sql = "SELECT * FROM pago";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

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
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pagos;
    }

    public boolean guardarPago(Pago pago) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            String sql = "INSERT INTO pago (idpago, valorpago, fechapago, idinmueble, cedCliente, cedasesor, idventa) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
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
            return false;

        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean actualizarPago(Pago pago) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            String sql = "UPDATE pago SET valorpago = ?, fechapago = ?, idinmueble = ?, cedCliente = ?, cedasesor = ?, idventa = ? WHERE idpago = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, pago.getValorPago());
            stmt.setDate(2, new java.sql.Date(pago.getFechaPago().getTime()));
            stmt.setString(3, pago.getIdInmueble());
            stmt.setString(4, pago.getCedCliente());
            stmt.setString(5, pago.getCedAsesor());
            stmt.setString(6, pago.getIdVenta());
            stmt.setString(7, pago.getIdPago());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean eliminarPago(String idPago) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            String sql = "DELETE FROM pago WHERE idpago = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idPago);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
