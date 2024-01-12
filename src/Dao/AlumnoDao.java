package Dao;

import Model.Database.Alumno;
import Model.Database.ConexionBD;
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
public class AlumnoDao {

    private static ResultSet Resultado;
    private final ConexionBD conexion = ConexionBD.getInstance();
    ArrayList<Alumno> alumnos;
    Alumno alumno;

    public ArrayList<Alumno> GetAll() {
        alumnos = new ArrayList<>();

        String sql = "select * from alumno";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            System.out.println(comando);
            System.out.println(conexion.conectar().getCatalog());
            Resultado = comando.executeQuery();

            while (Resultado.next()) {
                alumno = new Alumno();
                alumno.setId_persona(Resultado.getInt("id_usuario"));
                alumno.setMatricula(Resultado.getString("matricula"));
                alumno.setNombre(Resultado.getString("nombre"));
                alumno.setApellido_p(Resultado.getString("app"));
                alumno.setApellido_m(Resultado.getString("apm"));
                alumno.setEdad(Resultado.getInt("edad"));
                alumno.setSexo(Resultado.getString("sexo"));
                alumno.setProcedencia(Resultado.getString("procedencia"));
                alumno.setSemestre(Resultado.getString("semestre"));
                alumno.setGrupo(Resultado.getString("grupo"));
                alumno.setCorreo(Resultado.getString("correo"));
                alumno.setStatus(Resultado.getString("status"));
                alumnos.add(alumno);
            }
        } catch (SQLException ex) {
            return alumnos;
        }
        return alumnos;
    }

    public boolean InsertAlumno(Alumno alumno) {
        boolean status = false;
        String sql = "INSERT INTO alumno"
                + "(matricula, nombre, app, apm, edad, sexo, procedencia, semestre, grupo, correo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, alumno.getMatricula());
            comando.setString(2, alumno.getNombre());
            comando.setString(3, alumno.getApellido_p());
            comando.setString(4, alumno.getApellido_m());
            comando.setInt(5, alumno.getEdad());
            comando.setString(6, alumno.getSexo());
            comando.setString(7, alumno.getProcedencia());
            comando.setString(8, alumno.getSemestre());
            comando.setString(9, alumno.getGrupo());
            comando.setString(10, alumno.getCorreo());
            int estado = comando.executeUpdate();

            conexion.conectar().close();
            comando.close();
            status = estado == 1;
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public boolean UpdateAlumno(Alumno alumno) {
        boolean status = false;
        String sql = "UPDATE alumno "
                + "SET "
                + "nombre=?, app=?, apm=?, edad=?, sexo=?, procedencia=?, semestre=?, grupo=?, correo=? "
                + "WHERE matricula=?";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, alumno.getNombre());
            comando.setString(2, alumno.getApellido_p());
            comando.setString(3, alumno.getApellido_m());
            comando.setInt(4, alumno.getEdad());
            comando.setString(5, alumno.getSexo());
            comando.setString(6, alumno.getProcedencia());
            comando.setString(7, alumno.getSemestre());
            comando.setString(8, alumno.getGrupo());
            comando.setString(9, alumno.getCorreo());
            comando.setString(10, alumno.getMatricula());
            int estado = comando.executeUpdate();

            conexion.conectar().close();
            comando.close();
            status = estado == 1;
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public boolean Baja_Alta(String matricula, String status) {

        String sql = "UPDATE alumno SET status = ? WHERE matricula=?";
        PreparedStatement comando = null;

        try {
            comando = conexion.conectar().prepareStatement(sql);
            comando.setString(1, status);
            comando.setString(2, matricula);

            int estado = comando.executeUpdate();

            return estado == 1;
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
