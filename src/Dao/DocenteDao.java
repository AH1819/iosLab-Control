package Dao;

import Model.Database.ConexionBD;
import Model.Database.Docente;
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
public class DocenteDao {

    private static ResultSet Resultado;
    private final ConexionBD conexion = ConexionBD.getInstance();
    ArrayList<Docente> docentes;
    Docente docente;

    public ArrayList<Docente> GetAll() {
        docentes = new ArrayList<>();

        String sql = "select * from docente";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            Resultado = comando.executeQuery();

            while (Resultado.next()) {
                docente = new Docente();
                docente.setId_persona(Resultado.getInt("id_usuario"));
                docente.setNumero_plaza(Resultado.getString("numero_plaza"));
                docente.setNombre(Resultado.getString("nombre"));
                docente.setApellido_p(Resultado.getString("app"));
                docente.setApellido_m(Resultado.getString("apm"));
                docente.setEdad(Resultado.getInt("edad"));
                docente.setSexo(Resultado.getString("sexo"));
                docente.setInstitucion(Resultado.getString("institucion"));
                docente.setCorreo(Resultado.getString("correo"));
                docente.setStatus(Resultado.getString("status"));
                docentes.add(docente);
            }
            conexion.conectar().close();
            comando.close();
        } catch (SQLException ex) {
            return docentes;
        }
        return docentes;
    }

    public boolean InsertDocente(Docente docente) {
        boolean status = false;
        String sql = "INSERT INTO docente"
                + "(numero_plaza, nombre, app, apm, edad, sexo, institucion, correo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, docente.getNumero_plaza());
            comando.setString(2, docente.getNombre());
            comando.setString(3, docente.getApellido_p());
            comando.setString(4, docente.getApellido_m());
            comando.setInt(5, docente.getEdad());
            comando.setString(6, docente.getSexo());
            comando.setString(7, docente.getInstitucion());
            comando.setString(8, docente.getCorreo());
            int estado = comando.executeUpdate();

            conexion.conectar().close();
            comando.close();
            status = estado == 1;
        } catch (SQLException ex) {
            status = false;
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public boolean UpdateDocente(Docente docente) {
        boolean status = false;
        String sql = "UPDATE docente "
                + "SET "
                + "nombre=?, app=?, apm=?, edad=?, sexo=?, institucion=?, correo=? "
                + "WHERE numero_plaza=?";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, docente.getNombre());
            comando.setString(2, docente.getApellido_p());
            comando.setString(3, docente.getApellido_m());
            comando.setInt(4, docente.getEdad());
            comando.setString(5, docente.getSexo());
            comando.setString(6, docente.getInstitucion());
            comando.setString(7, docente.getCorreo());
            comando.setString(8, docente.getNumero_plaza());
            int estado = comando.executeUpdate();

            conexion.conectar().close();
            comando.close();
            status = estado == 1;
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public boolean Baja_Alta(String plaza, String status) {

        String sql = "UPDATE docente SET status = ? WHERE numero_plaza=?";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, status);
            comando.setString(2, plaza);

            int estado = comando.executeUpdate();

            return estado == 1;
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
