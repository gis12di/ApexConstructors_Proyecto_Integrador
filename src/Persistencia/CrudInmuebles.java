package Persistencia;

import Logica.Interfaz.Cruds;
import Logica.Inmuebles.Inmueble;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudInmuebles implements Cruds<Inmueble> {
    
    @Override
    public List<Inmueble> obtener(String idTorre) {
        List<Inmueble> inmuebles = new ArrayList<>();
        String sql = "{? = call obtenerInmuebles(?)}"; // Función PL/SQL
        try (Connection con = Conexion.getConnection();
             CallableStatement stmt = con.prepareCall(sql)) {

            stmt.registerOutParameter(1, Types.REF_CURSOR); // Parámetro de salida
            stmt.setString(2, idTorre); // Parámetro de entrada
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    Inmueble inmueble = new Inmueble();
                    inmueble.setId(rs.getString("id"));
                    inmueble.setNumInmueble(rs.getString("numinmueble"));
                    inmueble.setTipoUnidad(rs.getString("tipounidad"));
                    inmueble.setValorInmueble(rs.getDouble("valor"));
                    inmueble.setArea(rs.getDouble("area"));
                    inmueble.setCodTorre(rs.getString("idtorre"));
                    inmuebles.add(inmueble);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inmuebles;
    }

    @Override
    public boolean guardar(Inmueble inmueble) {
        String sql = "{call insertarInmueble(?, ?, ?, ?, ?)}"; // Procedimiento almacenado
        try (Connection con = Conexion.getConnection();
             CallableStatement stmt = con.prepareCall(sql)) {

            stmt.setString(1, inmueble.getNumInmueble());
            stmt.setString(2, inmueble.getTipoUnidad());
            stmt.setDouble(3, inmueble.getValorInmueble());
            stmt.setDouble(4, inmueble.getArea());
            stmt.setString(5, inmueble.getCodTorre());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizar(Inmueble inmueble) {
        String sql = "{call actualizarInmueble(?, ?, ?, ?)}"; // Procedimiento almacenado
        try (Connection con = Conexion.getConnection();
             CallableStatement stmt = con.prepareCall(sql)) {

            stmt.setString(1, inmueble.getId());
            stmt.setString(2, inmueble.getTipoUnidad());
            stmt.setDouble(3, inmueble.getValorInmueble());
            stmt.setDouble(4, inmueble.getArea());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "{call eliminarInmueble(?)}"; // Procedimiento almacenado
        try (Connection con = Conexion.getConnection();
             CallableStatement stmt = con.prepareCall(sql)) {

            stmt.setString(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
