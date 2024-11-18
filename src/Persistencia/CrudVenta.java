package Persistencia;

import Logica.Venta.Venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudVenta {

    // Método para obtener todas las ventas
    public List<Venta> obtenerVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM venta";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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
                venta.setNumPago(rs.getString("num_pago"));
                venta.setValorInicial(rs.getDouble("valor_inicial"));
                venta.setExcedente(rs.getDouble("excedente"));
                ventas.add(venta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }

    // Método para guardar una nueva venta
    public boolean guardarVenta(Venta venta) {
        String sql = "INSERT INTO venta (idventa, precio_total, fechaventa, matricula, fechaescritura, idinmueble, numdocumento, cedasesor, num_pago, valor_inicial, excedente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, venta.getIdVenta());
            stmt.setDouble(2, venta.getPrecioTotal());
            stmt.setDate(3, new java.sql.Date(venta.getFechaVenta().getTime()));
            stmt.setString(4, venta.getMatricula());
            stmt.setDate(5, new java.sql.Date(venta.getFechaEscritura().getTime()));
            stmt.setString(6, venta.getIdInmueble());
            stmt.setString(7, venta.getNumDocumentoCliente());
            stmt.setString(8, venta.getCedAsesor());
            stmt.setString(9, venta.getNumPago());
            stmt.setDouble(10, venta.getValorInicial());
            stmt.setDouble(11, venta.getExcedente());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar una venta existente
    public boolean actualizarVenta(Venta venta) {
        String sql = "UPDATE venta SET precio_total = ?, fechaventa = ?, matricula = ?, fechaescritura = ?, idinmueble = ?, numdocumento = ?, cedasesor = ?, num_pago = ?, valor_inicial = ?, excedente = ? WHERE idventa = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, venta.getPrecioTotal());
            stmt.setDate(2, new java.sql.Date(venta.getFechaVenta().getTime()));
            stmt.setString(3, venta.getMatricula());
            stmt.setDate(4, new java.sql.Date(venta.getFechaEscritura().getTime()));
            stmt.setString(5, venta.getIdInmueble());
            stmt.setString(6, venta.getNumDocumentoCliente());
            stmt.setString(7, venta.getCedAsesor());
            stmt.setString(8, venta.getNumPago());
            stmt.setDouble(9, venta.getValorInicial());
            stmt.setDouble(10, venta.getExcedente());
            stmt.setString(11, venta.getIdVenta());
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar una venta por su ID
    public boolean eliminarVenta(String idVenta) {
        String sql = "DELETE FROM venta WHERE idventa = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idVenta);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
