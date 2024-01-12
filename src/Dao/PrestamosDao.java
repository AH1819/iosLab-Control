package Dao;

import Model.Database.ConexionBD;
import Model.Database.Prestamos;
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
public class PrestamosDao {

    private static ResultSet Resultado;
    private final ConexionBD conexion = ConexionBD.getInstance();

    ArrayList<Prestamos> prestaciones;

    public ArrayList<Prestamos> GetPrestamos() {
        prestaciones = new ArrayList<>();

        String sql = "SELECT pr.id_prestamo, "
                + "CASE "
                + "WHEN a.matricula IS NOT NULL THEN a.matricula "
                + "WHEN d.numero_plaza IS NOT NULL THEN d.numero_plaza "
                + "ELSE 'N/E' "
                + "END AS id_usuario,"
                + "(p.nombre ||' '||p.app ||' '||p.apm) AS nombre,"
                + "CASE "
                + "WHEN pr.id_mac IS NULL THEN 'N/E' "
                + "ELSE pr.id_mac "
                + "END AS id_mac,"
                + "CASE "
                + "WHEN pr.id_ipad IS NULL THEN 'N/E' "
                + "ELSE pr.id_ipad "
                + "END AS id_ipad,"
                + "pr.fecha_prestamo, "
                + "pr.c_mac,"
                + "pr.c_ipad,"
                + "pr.credencial,"
                + "pr.hora_entra,"
                + "pr.hora_sale,"
                + "CASE "
                + "WHEN pr.hora_sale IS NULL THEN 'En uso' "
                + "ELSE 'Ok' "
                + "END AS status "
                + "FROM prestamos pr "
                + "INNER JOIN persona p "
                + "ON p.id_usuario = pr.id_usuario "
                + "LEFT JOIN alumno a "
                + "ON p.id_usuario = a.id_usuario "
                + "LEFT JOIN docente d "
                + "ON p.id_usuario = d.id_usuario;";
        PreparedStatement comando = null;
        try {
            comando = conexion.conectar().prepareStatement(sql);
            Resultado = comando.executeQuery();

            while (Resultado.next()) {
                Prestamos p = new Prestamos();
                p.setIdentificador(Resultado.getInt("id_prestamo"));
                p.setId_usuario(Resultado.getString("id_usuario"));
                p.setNombre(Resultado.getString("nombre"));
                p.setId_mac(Resultado.getString("id_mac"));
                p.setId_ipad(Resultado.getString("id_ipad"));
                p.setFecha(Resultado.getDate("fecha_prestamo"));
                p.setC_mac(Resultado.getBoolean("c_mac"));
                p.setC_ipad(Resultado.getBoolean("c_ipad"));
                p.setCredencial(Resultado.getBoolean("credencial"));
                p.setHora_entrada(Resultado.getTime("hora_entra"));
                p.setHora_salida(Resultado.getTime("hora_sale"));
                p.setStatus(Resultado.getString("status"));
                prestaciones.add(p);
            }
            conexion.conectar().close();
            comando.close();
        } catch (SQLException ex) {
            return prestaciones;
        }
        return prestaciones;
    }

    public boolean InsertarPrestamo(Prestamos presta) {
        String sql = "INSERT INTO prestamos(id_usuario, id_mac, id_ipad, fecha_prestamo, c_mac, c_ipad,"
                + " credencial, hora_entra, hora_sale) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setInt(1, Integer.parseInt(presta.getId_usuario()));
            comando.setString(2, presta.getId_mac());
            comando.setString(3, presta.getId_ipad());
            comando.setDate(4, presta.getFecha());
            comando.setBoolean(5, presta.isC_mac());
            comando.setBoolean(6, presta.isC_ipad());
            comando.setBoolean(7, presta.isCredencial());
            comando.setTime(8, presta.getHora_entrada());
            comando.setTime(9, presta.getHora_salida());
            int estado = comando.executeUpdate();

            conexion.conectar().close();
            comando.close();

            return estado == 1;
        } catch (SQLException ex) {
            Logger.getLogger(PrestamosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean ActualizarPrestamo(Prestamos presta) {
        String sql = "UPDATE prestamos "
                + "SET id_mac=?, id_ipad=?, fecha_prestamo=?, c_mac=?, c_ipad=?, credencial=?, "
                + "hora_entra=?, hora_sale=? WHERE id_prestamo = ?;";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, presta.getId_mac());
            comando.setString(2, presta.getId_ipad());
            comando.setDate(3, presta.getFecha());
            comando.setBoolean(4, presta.isC_mac());
            comando.setBoolean(5, presta.isC_ipad());
            comando.setBoolean(6, presta.isCredencial());
            comando.setTime(7, presta.getHora_entrada());
            comando.setTime(8, presta.getHora_salida());
            comando.setInt(9, presta.getIdentificador());
            int estado = comando.executeUpdate();

            conexion.conectar().close();
            comando.close();

            return estado == 1;
        } catch (SQLException ex) {
            Logger.getLogger(PrestamosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean DeletePrestamo(int id) {
        String sql = "DELETE FROM prestamos WHERE id_prestamo = ?";
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
