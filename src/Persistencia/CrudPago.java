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
        Connection conn = null;
        CallableStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            String query = "{call insertarPago(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            stmt = conn.prepareCall(query);

            // Establecer parámetros
            stmt.setDouble(1, pago.getValorPago());
            stmt.setDate(2, new java.sql.Date(pago.getFechaPago().getTime()));
            stmt.setString(3, pago.getIdInmueble());
            stmt.setString(4, pago.getCedCliente());
            stmt.setString(5, pago.getCedAsesor());
            stmt.setString(6, pago.getIdVenta());
            stmt.setString(7, pago.getEstadoPago());
            stmt.setDate(8, new java.sql.Date(pago.getFechaPago().getTime()));
            stmt.setInt(9, pago.getNumPago());
            stmt.setInt(10, pago.getNumeroDePagos());

            // Imprimir en consola los valores que se están enviando
            System.out.println("Insertando pago con los siguientes datos:");
            System.out.println("Valor Pago: " + pago.getValorPago());
            System.out.println("Fecha Pago: " + new java.sql.Date(pago.getFechaPago().getTime()));
            System.out.println("ID Inmueble: " + pago.getIdInmueble());
            System.out.println("Cédula Cliente: " + pago.getCedCliente());
            System.out.println("Cédula Asesor: " + pago.getCedAsesor());
            System.out.println("ID Venta: " + pago.getIdVenta());
            System.out.println("Estado Pago: " + pago.getEstadoPago());
            System.out.println("Fecha Vencimiento: " + new java.sql.Date(pago.getFechaPago().getTime()));
            System.out.println("Número Pago: " + pago.getNumPago());
            System.out.println("Número Total de Pagos: " + pago.getNumeroDePagos());

            // Ejecutar la consulta
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
