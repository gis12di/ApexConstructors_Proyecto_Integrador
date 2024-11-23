package Persistencia;

import Logica.Interfaz.Cruds;
import Logica.Torre.Torre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudTorres implements Cruds<Torre> {

    @Override
    public boolean actualizar(Torre torre) {
        if (torre == null || torre.getCodProyecto() == null || torre.getNumPisos() == null) {
            throw new IllegalArgumentException("Datos inválidos para actualizar la torre.");
        }

        String sql = "{call actualizarTorre(?, ?)}"; // Llamada al procedimiento PL/SQL

        try (Connection con = Conexion.getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setString(1, torre.getNumPisos());
            cstmt.setString(2, torre.getCodProyecto());
            cstmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(String codProyecto) {
        if (codProyecto == null || codProyecto.isEmpty()) {
            throw new IllegalArgumentException("El código del proyecto no puede ser nulo o vacío.");
        }

        String sql = "{call eliminarTorre(?)}"; // Llamada al procedimiento PL/SQL

        try (Connection con = Conexion.getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setString(1, codProyecto);
            cstmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean guardar(Torre torre) {
        if (torre == null || torre.getNumTorre() == null || torre.getNumPisos() == null || torre.getCodProyecto() == null) {
            throw new IllegalArgumentException("Datos inválidos para guardar la torre.");
        }

        String sql = "{call insertarTorre(?, ?, ?)}"; // Llamada al procedimiento PL/SQL

        try (Connection con = Conexion.getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setString(1, torre.getNumTorre());
            cstmt.setString(2, torre.getNumPisos());
            cstmt.setString(3, torre.getCodProyecto());
            cstmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Torre> obtener(String codProyecto) {
        if (codProyecto == null || codProyecto.isEmpty()) {
            throw new IllegalArgumentException("El código de proyecto no puede ser nulo o vacío");
        }

        List<Torre> torres = new ArrayList<>();
        String sql = "{? = call obtenerTorres(?)}"; // Llamada correcta a la función PL/SQL

        try (Connection con = Conexion.getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.registerOutParameter(1, Types.REF_CURSOR); // Parámetro de salida
            cstmt.setString(2, codProyecto); // Parámetro de entrada
            cstmt.execute();

            // Procesar el resultado
            try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
                while (rs.next()) {
                    Torre torre = new Torre();
                    torre.setId(rs.getString("id"));
                    torre.setNumTorre(rs.getString("numtorre"));
                    torre.setNumPisos(rs.getString("numpisos"));
                    torre.setCodProyecto(rs.getString("codproyecto"));
                    torres.add(torre);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return torres;
    }


    public boolean existeCodProyecto(String codProyecto) {
        if (codProyecto == null || codProyecto.isEmpty()) {
            throw new IllegalArgumentException("El código del proyecto no puede ser nulo o vacío.");
        }

        String sql = "{call existeCodProyecto(?, ?)}"; // Llamada al nuevo procedimiento PL/SQL

        try (Connection con = Conexion.getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setString(1, codProyecto);
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.execute();

            int count = cstmt.getInt(2);
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
