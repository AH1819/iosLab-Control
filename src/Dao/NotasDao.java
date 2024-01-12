package Dao;

import Model.Database.ConexionBD;
import Model.Database.Notas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hitler
 */
public class NotasDao {

    private static ResultSet Resultado;
    private final ConexionBD conexion = ConexionBD.getInstance();
    ArrayList<Notas> nt;
    Notas nota;

    public ArrayList<Notas> GetAll() {
        nt = new ArrayList<>();
        String sql = "SELECT "
                + "nt.id_referencia,"
                + "nt.fecha,"
                + "nt.tipo,"
                + "COALESCE(mc.id_mac, ip.id_ipad, 'N/E') AS id_dispositivo,"
                + "nt.id_prestamo,"
                + "nt.descripcion "
                + "FROM notas nt "
                + "LEFT JOIN dispositivo dp "
                + "ON dp.id_dispositivo = nt.id_dispositivo "
                + "LEFT JOIN mac mc "
                + "ON mc.id_dispositivo = dp.id_dispositivo "
                + "LEFT JOIN ipad ip "
                + "ON ip.id_dispositivo = dp.id_dispositivo "
                + "ORDER BY nt.id_referencia DESC;";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            Resultado = comando.executeQuery();

            while (Resultado.next()) {
                nota = new Notas();
                nota.setNumero(Resultado.getInt("id_referencia"));
                nota.setFecha(Resultado.getString("fecha"));
                nota.setTipo(Resultado.getString("tipo"));
                nota.setEquipo(Resultado.getString("id_dispositivo"));
                nota.setPrestacion(Resultado.getInt("id_prestamo"));
                nota.setDescripcion(Resultado.getString("descripcion"));
                nt.add(nota);
            }
            conexion.conectar().close();
            comando.close();
        } catch (SQLException ex) {
            return nt;
        }
        return nt;
    }

    public ArrayList<Notas> GetAllEQ(String tipo, String id) {
        nt = new ArrayList<>();
        String sql;
        if (id != null) {
            sql = "SELECT "
                    + "n.id_referencia, "
                    + "n.fecha,"
                    + "n.tipo,"
                    + "n.descripcion,"
                    + "COALESCE(m.id_mac, i.id_ipad) AS id_dispositivo "
                    + "FROM "
                    + "notas n "
                    + "LEFT JOIN "
                    + "mac m ON n.id_dispositivo = m.id_dispositivo "
                    + "LEFT JOIN "
                    + "ipad i ON n.id_dispositivo = i.id_dispositivo "
                    + "WHERE n.tipo = ? AND n.id_dispositivo = ?;";
        } else {
            sql = "SELECT "
                    + "n.id_referencia, "
                    + "n.fecha,"
                    + "n.tipo,"
                    + "n.descripcion,"
                    + "COALESCE(m.id_mac, i.id_ipad) AS id_dispositivo "
                    + "FROM "
                    + "notas n "
                    + "LEFT JOIN "
                    + "mac m ON n.id_dispositivo = m.id_dispositivo "
                    + "LEFT JOIN "
                    + "ipad i ON n.id_dispositivo = i.id_dispositivo "
                    + "WHERE n.tipo = ?;";
        }

        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, tipo);
            if (id != null) {
                comando.setInt(2, Integer.parseInt(id));
            }
            Resultado = comando.executeQuery();

            while (Resultado.next()) {
                nota = new Notas();
                nota.setNumero(Resultado.getInt("id_referencia"));
                nota.setFecha(Resultado.getString("fecha"));
                nota.setEquipo(Resultado.getString("id_dispositivo"));
                nota.setDescripcion(Resultado.getString("descripcion"));
                nt.add(nota);
            }
            conexion.conectar().close();
            comando.close();
        } catch (SQLException ex) {
            return nt;
        }
        return nt;
    }

    public ArrayList<Notas> GetAllPR() {
        nt = new ArrayList<>();
        String sql = "SELECT * FROM notas WHERE tipo = 'P' ORDER BY fecha DESC";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            Resultado = comando.executeQuery();

            while (Resultado.next()) {
                nota = new Notas();
                nota.setNumero(Resultado.getInt("id_referencia"));
                nota.setFecha(Resultado.getString("fecha"));
                nota.setEquipo(Resultado.getString("id_prestamo"));
                nota.setDescripcion(Resultado.getString("descripcion"));
                nt.add(nota);
            }
            conexion.conectar().close();
            comando.close();
        } catch (SQLException ex) {
            return nt;
        }
        return nt;
    }

    public boolean InsertNotaEQ(Notas nota) {
        boolean status = false;
        System.out.println(nota.getNumero());
        String sql = "INSERT INTO notas(fecha,tipo,id_dispositivo,descripcion) values(now(),?,?,?)";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, nota.getTipo());
            comando.setInt(2, Integer.parseInt(nota.getEquipo()));
            comando.setString(3, nota.getDescripcion());
            int estado = comando.executeUpdate();
            status = estado == 1;

            conexion.conectar().close();
            comando.close();
        } catch (SQLException ex) {
            Logger.getLogger(NotasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public boolean InsertNotaPR(Notas nota) {
        boolean status = false;
        String sql = "INSERT INTO notas(fecha,tipo,id_prestamo,descripcion) values(now(),?,?,?)";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, nota.getTipo());
            comando.setInt(2, nota.getPrestacion());
            comando.setString(3, nota.getDescripcion());
            int estado = comando.executeUpdate();
            status = estado == 1;

            conexion.conectar().close();
            comando.close();
        } catch (SQLException ex) {
            Logger.getLogger(NotasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public boolean InsertNotaGB(Notas nota) {
        boolean status = false;
        String sql = "INSERT INTO notas(fecha,tipo,descripcion) values(now(),?,?)";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, nota.getTipo());
            comando.setString(2, nota.getDescripcion());
            int estado = comando.executeUpdate();
            status = estado == 1;

            conexion.conectar().close();
            comando.close();
        } catch (SQLException ex) {
            Logger.getLogger(NotasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public boolean UpdateNotas(Notas nota) {
        String sql = "UPDATE notas SET descripcion = ? WHERE id_referencia = ?";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, nota.getDescripcion());
            comando.setInt(2, nota.getNumero());
            int estado = comando.executeUpdate();

            conexion.conectar().close();
            comando.close();

            return estado == 1;
        } catch (SQLException ex) {
            Logger.getLogger(PrestamosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean DeleteNotas(int id) {
        String sql = "DELETE FROM notas WHERE id_referencia = ?";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setInt(1, id);
            int estado = comando.executeUpdate();

            conexion.conectar().close();
            comando.close();

            return estado == 1;
        } catch (SQLException ex) {
            Logger.getLogger(PrestamosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
