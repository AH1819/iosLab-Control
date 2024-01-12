package Dao;

import Model.Database.ConexionBD;
import Model.Database.Ipads;
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
public class IpadDao {

    private static ResultSet Resultado;
    private final ConexionBD conexion = ConexionBD.getInstance();
    ArrayList<Ipads> ipads;
    Ipads ipad;

    public ArrayList<Ipads> GetIpads() {
        ipads = new ArrayList<>();

        String sql = "SELECT i.id_dispositivo, i.id_ipad, i.numero_serie,i.applepencil,i.cargador,"
                + "COUNT(n.id_dispositivo) AS notas "
                + "FROM ipad AS i "
                + "LEFT JOIN notas AS n ON i.id_dispositivo = n.id_dispositivo "
                + "GROUP BY i.id_dispositivo, i.id_ipad,i.numero_serie, i.applepencil, i.cargador "
                + "ORDER BY CAST(SUBSTRING(i.id_ipad FROM 2) AS integer);";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            Resultado = comando.executeQuery();

            while (Resultado.next()) {
                ipad = new Ipads();
                ipad.setId_dispositivo(Resultado.getString("id_dispositivo"));
                ipad.setNumero(Resultado.getString("id_ipad"));
                ipad.setNumero_serie(Resultado.getString("numero_serie"));
                ipad.setApplepencil(Resultado.getString("applepencil"));
                ipad.setCargador(Resultado.getString("cargador"));
                ipad.setNotas(Resultado.getString("notas"));
                ipads.add(ipad);
            }
            conexion.conectar().close();
            comando.close();
        } catch (SQLException ex) {
            return ipads;
        }
        return ipads;
    }
    
    public ArrayList<Ipads> GetIpadsAvailable() {
        ipads = new ArrayList<>();

        String sql = "SELECT id_ipad "
                + "FROM ipad "
                + "WHERE id_ipad NOT IN "
                + "( SELECT ipad.id_ipad FROM ipad "
                + "INNER JOIN prestamos ON ipad.id_ipad = prestamos.id_ipad WHERE prestamos.hora_sale IS NULL) "
                + "ORDER BY CAST(SUBSTRING(id_ipad FROM 2) AS integer);";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            Resultado = comando.executeQuery();

            while (Resultado.next()) {
                ipad = new Ipads();
                ipad.setNumero(Resultado.getString("id_ipad"));
                ipads.add(ipad);
            }
            conexion.conectar().close();
            comando.close();
        } catch (SQLException ex) {
            return ipads;
        }
        return ipads;
    }

    public boolean InsertIpad(Ipads ipd) {
        boolean status = false;
        String sql = "INSERT INTO ipad(id_ipad, numero_serie, applepencil, cargador) VALUES (?, ?, ?, ?)";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, ipd.getNumero());
            comando.setString(2, ipd.getNumero_serie());
            comando.setString(3, ipd.getApplepencil());
            comando.setString(4, ipd.getCargador());
            int estado = comando.executeUpdate();
            status = estado == 1;

            conexion.conectar().close();
            comando.close();
        } catch (SQLException ex) {
            status = false;
            Logger.getLogger(IpadDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public boolean UpdateIpad(Ipads ip) {
        boolean status = false;
        String sql = "UPDATE ipad SET id_ipad=?, numero_serie=?, cargador=?, applepencil=? WHERE id_ipad=?";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, ip.getNumero());
            comando.setString(2, ip.getNumero_serie());
            comando.setString(3, ip.getCargador());
            comando.setString(4, ip.getApplepencil());
            comando.setString(5, ip.getNumero_anterior());
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
