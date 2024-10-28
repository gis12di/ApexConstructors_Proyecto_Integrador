package Persistencia;

import Logica.Venta.Venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudVenta {

    public List<Venta> obtenerVentas() {
        List<Venta> ventas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            String sql = "SELECT * FROM venta";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getString("idventa"));
                venta.setPrecioTotal(rs.getDouble("precio_total"));
                venta.setFechaVenta(rs.getDate("fechaventa"));
                venta.setMatricula(rs.getString("matricula"));
                venta.setFechaEscritura(rs.getDate("fechaescritura"));
                venta.setIdInmueble(rs.getString("idinmueble"));
                venta.setNumDocumentoCliente(rs.getString("numdocumento"));
                venta.setCedAsesor(rs.getString("cedasesor"));
                ventas.add(venta);
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
        return ventas;
    }

    public boolean guardarVenta(Venta venta) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            String sql = "INSERT INTO venta (idventa, precio_total, fechaventa, matricula, fechaescritura, idinmueble, numdocumento, cedasesor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, venta.getIdVenta());
            stmt.setDouble(2, venta.getPrecioTotal());
            stmt.setDate(3, new java.sql.Date(venta.getFechaVenta().getTime()));
            stmt.setString(4, venta.getMatricula());
            stmt.setDate(5, new java.sql.Date(venta.getFechaEscritura().getTime()));
            stmt.setString(6, venta.getIdInmueble());
            stmt.setString(7, venta.getNumDocumentoCliente());
            stmt.setString(8, venta.getCedAsesor());
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

    public boolean actualizarVenta(Venta venta) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            String sql = "UPDATE venta SET precio_total = ?, fechaventa = ?, matricula = ?, fechaescritura = ?, idinmueble = ?, numdocumento = ?, cedasesor = ? WHERE idventa = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, venta.getPrecioTotal());
            stmt.setDate(2, new java.sql.Date(venta.getFechaVenta().getTime()));
            stmt.setString(3, venta.getMatricula());
            stmt.setDate(4, new java.sql.Date(venta.getFechaEscritura().getTime()));
            stmt.setString(5, venta.getIdInmueble());
            stmt.setString(6, venta.getNumDocumentoCliente());
            stmt.setString(7, venta.getCedAsesor());
            stmt.setString(8, venta.getIdVenta());
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

    public boolean eliminarVenta(String idVenta) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            String sql = "DELETE FROM venta WHERE idventa = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idVenta);
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
