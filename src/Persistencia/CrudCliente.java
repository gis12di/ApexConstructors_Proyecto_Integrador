package Persistencia;

import Logica.Cliente.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudCliente {

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            String sql = "{ ? = CALL obtenerCliente() }";
            stmt = conn.prepareCall(sql);
            stmt.registerOutParameter(1, Types.REF_CURSOR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNumDocumento(rs.getString("numdocumento"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSubsidioMinisterio(rs.getString("subsidio_ministerio"));
                cliente.setSisben(rs.getString("sisben"));
                clientes.add(cliente);
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
        return clientes;
    }

    public boolean guardarCliente(Cliente cliente) {
        Connection conn = null;
        CallableStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            String sql = "{ CALL insertarCliente(?, ?, ?, ?, ?, ?, ?, ?) }";
            stmt = conn.prepareCall(sql);
            stmt.setString(1, cliente.getNumDocumento());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellido());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getTelefono());
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getSubsidioMinisterio());
            stmt.setString(8, cliente.getSisben());
            stmt.execute();
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

    public boolean actualizarCliente(Cliente cliente) {
        Connection conn = null;
        CallableStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            String sql = "{ CALL actualizarCliente(?, ?, ?, ?, ?, ?, ?, ?) }";
            stmt = conn.prepareCall(sql);
            stmt.setString(1, cliente.getNumDocumento());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellido());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getTelefono());
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getSubsidioMinisterio());
            stmt.setString(8, cliente.getSisben());
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

    public boolean eliminarCliente(String numDocumento) {
        Connection conn = null;
        CallableStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            String sql = "{ CALL eliminarCliente(?) }";
            stmt = conn.prepareCall(sql);
            stmt.setString(1, numDocumento);
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
