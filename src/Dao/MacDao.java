package Dao;

import Model.Database.ConexionBD;
import Model.Database.Macs;
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
public class MacDao {

    private static ResultSet Resultado;
    private final ConexionBD conexion = ConexionBD.getInstance();
    ArrayList<Macs> macs;
    Macs mac;

    public ArrayList<Macs> GetMacs() {
        macs = new ArrayList<>();

        String sql = "SELECT m.id_dispositivo, m.id_mac, m.numero_serie,m.cargador, "
                + "COUNT(n.id_dispositivo) AS notas "
                + "FROM mac AS m "
                + "LEFT JOIN notas AS n "
                + "ON m.id_dispositivo = n.id_dispositivo "
                + "GROUP BY m.id_dispositivo, m.id_mac, m.numero_serie, m.cargador "
                + "ORDER BY CAST(SUBSTRING(m.id_mac FROM 2) AS integer);";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            Resultado = comando.executeQuery();

            while (Resultado.next()) {
                mac = new Macs();
                mac.setId_dispositivo(Resultado.getString("id_dispositivo"));
                mac.setNumero(Resultado.getString("id_mac"));
                mac.setNumero_serie(Resultado.getString("numero_serie"));
                mac.setCargador(Resultado.getString("cargador"));
                mac.setNotas(Resultado.getString("notas"));
                macs.add(mac);
            }

            conexion.conectar().close();
            comando.close();
        } catch (SQLException ex) {
            return macs;
        }
        return macs;
    }

    public ArrayList<Macs> GetMacsAvailable() {
        macs = new ArrayList<>();
        String sql = "SELECT id_mac "
                + "FROM mac "
                + "WHERE id_mac "
                + "NOT IN ( SELECT mac.id_mac "
                + "FROM mac INNER JOIN prestamos "
                + "ON mac.id_mac = prestamos.id_mac "
                + "WHERE prestamos.hora_sale IS NULL) "
                + "ORDER BY CAST(SUBSTRING(id_mac FROM 2) AS integer);";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            Resultado = comando.executeQuery();

            while (Resultado.next()) {
                mac = new Macs();
                mac.setNumero(Resultado.getString("id_mac"));
                macs.add(mac);
            }

            conexion.conectar().close();
            comando.close();
        } catch (SQLException ex) {
            return macs;
        }
        return macs;
    }

    public boolean InsertMac(Macs mc) {
        boolean status = false;
        String sql = "INSERT INTO mac(id_mac, numero_serie, cargador) VALUES (?, ?, ?)";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, mc.getNumero());
            comando.setString(2, mc.getNumero_serie());
            comando.setString(3, mc.getCargador());
            int estado = comando.executeUpdate();
            status = estado == 1;

            conexion.conectar().close();
            comando.close();
        } catch (SQLException ex) {
            status = false;
            Logger.getLogger(MacDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public boolean UpdateMac(Macs mc) {
        boolean status = false;
        String sql = "UPDATE mac SET id_mac=?, numero_serie=?, cargador=? WHERE id_mac=?";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, mc.getNumero());
            comando.setString(2, mc.getNumero_serie());
            comando.setString(3, mc.getCargador());
            comando.setString(4, mc.getNumero_anterior());
            int estado = comando.executeUpdate();
            status = estado == 1;

            conexion.conectar().close();
            comando.close();
        } catch (SQLException ex) {
            status = false;
            Logger.getLogger(MacDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

}
