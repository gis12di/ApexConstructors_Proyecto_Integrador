package Persistencia;

import Logica.Interfaz.Cruds;
import Logica.Venta.Venta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudVenta implements Cruds<Venta> {

    @Override
    public List<Venta> obtener(String criterio) {
        List<Venta> ventas = new ArrayList<>();
        String sql = "{? = call obtenerVentas}"; // Llamada a la función obtenerVentas

        try (Connection conn = Conexion.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.registerOutParameter(1, Types.REF_CURSOR); // Registrar el cursor de salida
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
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
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener ventas: " + e.getMessage());
        }
        return ventas;
    }

    @Override
    public boolean guardar(Venta venta) {
        String sql = "{call insertarVenta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"; // Sin el idventa

        try (Connection conn = Conexion.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setDouble(1, venta.getPrecioTotal());
            cstmt.setDate(2, new java.sql.Date(venta.getFechaVenta().getTime()));
            cstmt.setString(3, venta.getMatricula());
            cstmt.setDate(4, new java.sql.Date(venta.getFechaEscritura().getTime()));
            cstmt.setString(5, venta.getIdInmueble());
            cstmt.setString(6, venta.getNumDocumentoCliente());
            cstmt.setString(7, venta.getCedAsesor());
            cstmt.setString(8, venta.getNumPago());
            cstmt.setDouble(9, venta.getValorInicial());
            cstmt.setDouble(10, venta.getExcedente());

            cstmt.execute();
            return true;

        } catch (SQLTimeoutException e) {
            System.err.println("Tiempo de espera agotado al intentar guardar la venta: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error de SQL al guardar la venta: " + e.getMessage());
        }
        return false;
    }


    @Override
    public boolean actualizar(Venta venta) {
        String sql = "{call actualizarVenta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = Conexion.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, venta.getIdVenta());
            cstmt.setDouble(2, venta.getPrecioTotal());
            cstmt.setDate(3, new java.sql.Date(venta.getFechaVenta().getTime()));
            cstmt.setString(4, venta.getMatricula());
            cstmt.setDate(5, new java.sql.Date(venta.getFechaEscritura().getTime()));
            cstmt.setString(6, venta.getIdInmueble());
            cstmt.setString(7, venta.getNumDocumentoCliente());
            cstmt.setString(8, venta.getCedAsesor());
            cstmt.setString(9, venta.getNumPago());
            cstmt.setDouble(10, venta.getValorInicial());
            cstmt.setDouble(11, venta.getExcedente());

            cstmt.execute();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al actualizar la venta: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminar(String criterio) {
        String sql = "{call eliminarVenta(?)}";

        try (Connection conn = Conexion.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, criterio);
            cstmt.execute();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al eliminar la venta: " + e.getMessage());
        }
        return false;
    }
}
